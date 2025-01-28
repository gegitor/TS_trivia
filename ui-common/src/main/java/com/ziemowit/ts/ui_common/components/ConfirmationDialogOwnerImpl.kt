package com.ziemowit.ts.ui_common.components

import androidx.compose.runtime.mutableStateOf

class ConfirmationDialogOwnerImpl : ConfirmationDialogOwner {
    override val showConfirmationDialog = mutableStateOf(false)

    override fun onBackClicked() {
        showConfirmationDialog.value = true
    }

    override fun onConfirmBack() {
        showConfirmationDialog.value = false
    }

    override fun onDismissDialog() {
        showConfirmationDialog.value = false
    }
}
