package com.kmpdemo.domain.entities.home

data class HomeAddressItem(
    val title: String?,
    val lat: Long?,
    val long: Long?,
    val pincode: Int?,
    val city: String?,
    val state: String,
    val address: String?
)