package com.ziemowit.ts.ui_common.components

import androidx.compose.runtime.MutableState

interface ConfirmationDialogOwner {
    val showConfirmationDialog: MutableState<Boolean>

    fun onBackClicked()

    fun onConfirmBack()

    fun onDismissDialog()
}
