package com.arabam.android.assignment.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.core.model.BottomNavigationViewItemReselectListener
import com.arabam.android.assignment.core.model.BottomNavigationViewItemReselectListenerMediator
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding> : Fragment(),
    BottomNavigationViewItemReselectListener {

    private lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: DB)

    @Inject
    lateinit var bottomNavigationViewMediator: BottomNavigationViewItemReselectListenerMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationViewMediator.registerListener(this)
        bind(binding)
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

    override fun onItemReselected(item: MenuItem) {
        if (binding.root.parent is ViewGroup) {
            val recyclerView = findRecyclerViewChild(binding.root.parent as ViewGroup)
            recyclerView?.layoutManager?.scrollToPosition(0)
        }
    }

    private fun findRecyclerViewChild(viewGroup: ViewGroup): RecyclerView? {
        viewGroup.children.forEach {
            if (it is RecyclerView) {
                return it
            } else if (it is ViewGroup) {
                val childRecyclerView = findRecyclerViewChild(it)
                if (childRecyclerView != null) {
                    return childRecyclerView
                }
            }
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
