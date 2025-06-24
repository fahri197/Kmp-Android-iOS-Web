package com.kmpdemo.presentation.utils.extensions

fun String.capitalizeWords(): String =
    this.lowercase()
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar(Char::titlecaseChar) }