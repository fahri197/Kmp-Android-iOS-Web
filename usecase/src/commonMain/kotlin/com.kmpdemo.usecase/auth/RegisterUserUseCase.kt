package com.kmpdemo.usecase.auth

import com.kmpdemo.domain.entities.auth.RegisterUserRequest
import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.usecase.base.SuspendingUseCase

interface RegisterUserUseCase : SuspendingUseCase<RegisterUserRequest, Result<MatchResponse>>