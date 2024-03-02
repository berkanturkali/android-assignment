package com.arabam.android.assignment.feature.details.model.command

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.arabam.android.assignment.core.model.Command

data class OpenMessageScreenCommand(
    val context: Context,
    val phoneNumber: String,
) : Command {
    override fun execute() {
        val number = "sms:$phoneNumber"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(number)
        context.startActivity(intent)
    }
}