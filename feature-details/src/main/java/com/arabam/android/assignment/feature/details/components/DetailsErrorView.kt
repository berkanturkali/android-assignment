package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.core.common.R.string

@Composable
fun DetailsErrorView(
    message: String,
    onTryAgainButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.h6,
                color = colorResource(id = color.primary_color)
            )

            Button(
                onClick = onTryAgainButtonClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = color.primary_color),
                    contentColor = colorResource(id = color.on_primary)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = string.try_again),
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(4.dp)
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailsErrorViewPrev() {
    MaterialTheme {
        DetailsErrorView(
            message = stringResource(id = string.something_went_wrong),
            onTryAgainButtonClick = { })
    }
}