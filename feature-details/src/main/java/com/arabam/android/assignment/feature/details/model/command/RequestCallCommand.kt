package com.arabam.android.assignment.feature.details.model.command

import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import com.arabam.android.assignment.core.model.Command

data class RequestCallCommand(
    val resultLauncher: ActivityResultLauncher<String>,
) : Command {
    override fun execute() {
        resultLauncher.launch(Manifest.permission.CALL_PHONE)
    }
}