package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.feature.listing.R

@Composable
fun ApplyButton(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onButtonClick,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = color.primary_color),
            contentColor = colorResource(id = color.on_primary)
        )

    ) {
        Text(text = stringResource(id = R.string.apply), style = MaterialTheme.typography.button)
    }
}

@Preview
@Composable
fun ApplyButtonPrev() {
    MaterialTheme {
        ApplyButton({})
    }
}