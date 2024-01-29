package com.arabam.android.assignment.core.model

import android.view.MenuItem
import javax.inject.Inject

class BottomNavigationViewItemReselectListenerMediatorImpl @Inject constructor() :
    BottomNavigationViewItemReselectListenerMediator {

    private lateinit var listener: BottomNavigationViewItemReselectListener
    override fun registerListener(listener: BottomNavigationViewItemReselectListener) {
        this.listener = listener
    }

    override fun checkListenerRegistered(): Boolean {
        return ::listener.isInitialized
    }

    override fun onItemReselected(item: MenuItem) {
        if (checkListenerRegistered()) {
            listener.onItemReselected(item)
        }
    }
}