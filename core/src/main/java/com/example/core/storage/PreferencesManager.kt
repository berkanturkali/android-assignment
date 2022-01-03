package com.example.core.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.arabam.android.assignment.commons.utils.Constants.IS_GRID_MODE
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_DIRECTION
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_MAX_YEAR
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_MIN_YEAR
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_TYPE
import com.example.core.storage.PreferencesManager.PreferencesKeys.GRID_MODE
import com.example.core.storage.PreferencesManager.PreferencesKeys.MAX_YEAR
import com.example.core.storage.PreferencesManager.PreferencesKeys.MIN_YEAR
import com.example.core.storage.PreferencesManager.PreferencesKeys.SORT_DIRECTION
import com.example.core.storage.PreferencesManager.PreferencesKeys.SORT_TYPE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

sealed class HomeScreenPreferences {
    data class ViewPreferences(val isRecyclerViewInGridMode: Boolean) : HomeScreenPreferences()
    data class FilterPreferences(
        val sortDirection: Int?,
        val sortType: Int?,
        val minYear: Int?,
        val maxYear: Int?,
    ) : HomeScreenPreferences()
}

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    private val dataStore = context.dataStore

    val viewPreferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.i(exception.localizedMessage)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .distinctUntilChangedBy {
            it[GRID_MODE]
        }
        .map { preferences ->
            val isGridMode = preferences[GRID_MODE]
            HomeScreenPreferences.ViewPreferences(isGridMode ?: false)
        }

    val filterPreferences = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Timber.i(exception.localizedMessage)
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.distinctUntilChanged { old, new ->
        (old[SORT_DIRECTION] == new[SORT_DIRECTION])
                && (old[SORT_TYPE] == new[SORT_TYPE])
                && (old[MIN_YEAR] == new[MIN_YEAR])
                && (old[MAX_YEAR] == new[MAX_YEAR])
    }
        .map { preferences ->
        val direction =
            if (preferences[SORT_DIRECTION] == -1) null else preferences[SORT_DIRECTION]
        val type = if (preferences[SORT_TYPE] == -1) null else preferences[SORT_TYPE]
        val min = if (preferences[MIN_YEAR] == -1) null else preferences[MIN_YEAR]
        val max = if (preferences[MAX_YEAR] == -1) null else preferences[MAX_YEAR]
        HomeScreenPreferences.FilterPreferences(direction, type, min, max)
    }

    suspend fun updateMinMaxYear(minYear: Int?, maxYear: Int?) {
        dataStore.edit { preferences ->
            preferences[MIN_YEAR] = minYear ?: -1
            preferences[MAX_YEAR] = maxYear ?: -1
        }
    }

    suspend fun updateSortPreferences(direction: Int?, type: Int?) {
        dataStore.edit { preferences ->
            preferences[SORT_DIRECTION] = direction ?: -1
            preferences[SORT_TYPE] = type ?: -1
        }
    }

    suspend fun updateViewPreferences(viewPreferences: HomeScreenPreferences) {
        val vPreferences = viewPreferences as HomeScreenPreferences.ViewPreferences
        dataStore.edit { preferences ->
            preferences[GRID_MODE] = vPreferences.isRecyclerViewInGridMode
        }
    }

    private object PreferencesKeys {
        val GRID_MODE = booleanPreferencesKey(IS_GRID_MODE)
        val SORT_DIRECTION = intPreferencesKey(SELECTED_DIRECTION)
        val SORT_TYPE = intPreferencesKey(SELECTED_TYPE)
        val MIN_YEAR = intPreferencesKey(SELECTED_MIN_YEAR)
        val MAX_YEAR = intPreferencesKey(SELECTED_MAX_YEAR)
    }
}