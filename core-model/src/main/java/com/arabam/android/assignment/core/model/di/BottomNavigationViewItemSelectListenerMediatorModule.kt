package com.arabam.android.assignment.core.model.di

import com.arabam.android.assignment.core.model.BottomNavigationViewItemReselectListenerMediator
import com.arabam.android.assignment.core.model.BottomNavigationViewItemReselectListenerMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface BottomNavigationViewItemSelectListenerMediatorModule {

    @get:[Binds Singleton]
    val BottomNavigationViewItemReselectListenerMediatorImpl.bottomNavigationViewMediator: BottomNavigationViewItemReselectListenerMediator
}