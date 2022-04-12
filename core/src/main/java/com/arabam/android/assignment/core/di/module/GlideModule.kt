package com.arabam.android.assignment.core.di.module

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GlideModule {

    companion object {
        @Provides
        fun provideCircularProgressDrawable(@ApplicationContext context: Context): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5F
            circularProgressDrawable.centerRadius = 30F
            circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(
                    context,
                    R.color.primary_color
                )
            )
            circularProgressDrawable.start()
            return circularProgressDrawable
        }
    }
}
