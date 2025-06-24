package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.auth.MobileRegisteredResponse
import com.kmpdemo.usecase.base.SuspendingUseCase

interface MobileRegisteredUseCase : SuspendingUseCase<String, Result<MobileRegisteredResponse>>