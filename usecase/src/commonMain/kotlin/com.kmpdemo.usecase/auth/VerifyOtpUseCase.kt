package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.usecase.base.SuspendingUseCase

interface VerifyOtpUseCase : SuspendingUseCase<String, Result<MatchResponse>>