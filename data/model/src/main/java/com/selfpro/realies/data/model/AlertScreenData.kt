package com.selfpro.realies.data.model

data class AlertScreenData (
    val title: String,
    val message: String,
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {},
    val confirmText: String = "",
    val dismissText: String = ""
)