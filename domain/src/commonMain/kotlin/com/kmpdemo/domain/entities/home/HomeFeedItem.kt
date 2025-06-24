package com.kmpdemo.domain.entities.home

sealed class HomeFeedItem {
    data class Address(val title: String, val address: HomeAddressItem) : HomeFeedItem()
    data class BannerRow(val title: String, val banners: List<HomeBannerItem>) : HomeFeedItem()
    data class ServicesGrid(val title: String, val services: List<HomeServiceItem>) : HomeFeedItem()
}