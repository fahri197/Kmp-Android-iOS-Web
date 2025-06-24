package com.kmpdemo.usecase.match

import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.usecase.base.UseCase

import kotlinx.coroutines.flow.Flow

interface MatchRemoteUseCase : UseCase<String, Flow<Result<MatchResponse>>>