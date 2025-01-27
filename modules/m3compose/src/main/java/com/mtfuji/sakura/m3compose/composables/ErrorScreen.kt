package com.mtfuji.sakura.m3compose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.m3compose.R
import com.mtfuji.sakura.m3compose.theme.ShoppingCartTheme
import com.mtfuji.sakura.m3compose.theme.dimens

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(MaterialTheme.dimens.unit4),
                    text = stringResource(R.string.error_title),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    modifier = Modifier.padding(MaterialTheme.dimens.unit4),
                    text = errorMessage,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(MaterialTheme.dimens.unit4)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                onClick = onRetry
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.error_retry),
                    color = MaterialTheme.colorScheme.onError
                )
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    ShoppingCartTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ErrorScreen(
                errorMessage = "Something went wrong!",
                onRetry = { }
            )
        }
    }
}