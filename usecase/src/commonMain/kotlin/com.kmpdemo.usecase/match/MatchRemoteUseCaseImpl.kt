package com.kmpdemo.usecase.match

import com.kmpdemo.domain.repositories.MatchesRepository
import kotlinx.coroutines.flow.flow

internal class MatchRemoteUseCaseImpl(
    private val matchRepository: MatchesRepository,
) : MatchRemoteUseCase {

    override fun execute(input: String) = flow {
        emit(matchRepository.getLatestMatch())
    }

}