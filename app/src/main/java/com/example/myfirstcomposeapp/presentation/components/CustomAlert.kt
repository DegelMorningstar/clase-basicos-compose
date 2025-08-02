package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.myfirstcomposeapp.R

@Composable
fun CustomAlert(
    showAlert: Boolean,
    onCloseAlert: () -> Unit
) {
    if (showAlert) {
        AlertDialog(
            icon = {
                Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "Example Icon")
            },
            title = {
                Text(text = "Aviso imporante")
            },
            text = {
                Text(text = "Has agregado más de 5 usuarios, por favor revisa la lista.")
            },
            onDismissRequest = onCloseAlert,
            confirmButton = {
                TextButton(
                    onClick = onCloseAlert
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onCloseAlert
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}