package com.kmpdemo.usecase.home

import com.kmpdemo.domain.entities.home.HomeFeedResponse
import com.kmpdemo.usecase.base.SuspendingUseCase

interface HomeFeedUseCase : SuspendingUseCase<HomeFeedUseCase.Input, Result<HomeFeedResponse>> {
    data class Input(
        val page: Int,
        val offset: Int
    )
}