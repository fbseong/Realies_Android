package com.selfpro.realies.util

data class AlertScreenData (
    val title: String,
    val message: String,
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {},
    val confirmText: String = "",
    val dismissText: String = ""
)