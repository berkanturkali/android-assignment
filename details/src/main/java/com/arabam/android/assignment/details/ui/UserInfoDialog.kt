package com.arabam.android.assignment.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arabam.android.assignment.details.databinding.DialogUserInfoBinding
import com.arabam.android.assignment.details.viewmodel.UserInfoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogUserInfoBinding

    private val viewModel by viewModels<UserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            phone = viewModel.getPhone()
            name = viewModel.getName()
            profileIv.setImageResource(viewModel.getImage())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}