package com.arabam.android.assignment.commons.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.arabam.android.assignment.UIController

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {

    private lateinit var binding: DB

    private lateinit var viewModel: VM

    private lateinit var controller: UIController

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, vm: VM)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setUIController()
    }
    private fun setUIController() {
        controller = requireActivity() as UIController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVM(binding, viewModel)
        init()
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

    fun showProgress(show:Boolean){
        controller.displayProgress(show)
    }

    abstract fun init()

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}