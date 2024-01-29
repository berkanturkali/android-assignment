package com.arabam.android.assignment.core.model

import android.view.MenuItem

interface BottomNavigationViewItemReselectListenerMediator {

    fun registerListener(listener: BottomNavigationViewItemReselectListener)

    fun checkListenerRegistered(): Boolean

    fun onItemReselected(item: MenuItem)
}