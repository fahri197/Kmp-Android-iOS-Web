package com.kmpdemo.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmpdemo.local.pref.KeyValueStorage
import com.kmpdemo.presentation.utils.PrefKeys
import com.kmpdemo.usecase.home.HomeFeedUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeFeedUseCase: HomeFeedUseCase,
    private val preferences: KeyValueStorage
) : ViewModel() {

    private var address: String
        get() = preferences.getString(PrefKeys.ADDRESS, "")
        set(value) = preferences.putString(PrefKeys.ADDRESS, value)

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    private val _homeUiEffect = MutableSharedFlow<HomeUiEffect>()
    val homeUiEffect: SharedFlow<HomeUiEffect> = _homeUiEffect.asSharedFlow()

    private var currentPage = 1

    fun onEventChange(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.LoadHomeScreen -> loadHome()
            is HomeUiEvent.LoadMoreServices -> loadMoreServices()
        }
    }

    private fun loadHome() {
        viewModelScope.launch {
            _homeUiState.update { it.copy(isLoading = true) }
            val address = preferences.getString("address", "") ?: ""
            homeFeedUseCase.execute(HomeFeedUseCase.Input(page = 1, offset = 10)).onSuccess { response ->
                val result = response.data
//                _homeUiState.update {
//                    it.copy(
//                        address = address,
//                        banners = result.banners,
//                        services = result.services,
//                        isLoading = false
//                    )
//                }
            }.onFailure {

            }
        }
    }

    private fun loadMoreServices() {
        viewModelScope.launch {
            currentPage++
//            val result = homeFeedUseCase.getFeed(page = currentPage)
//            _homeUiState.update {
//                it.copy(
//                    services = it.services + result.services
//                )
//            }
        }
    }

    private fun loadInitialData() {

        viewModelScope.launch {
//            getHomeDataUseCase(currentPage, "forYou", PAGE_SIZE).also { result ->
//                when (result) {
//                    is Result.Success -> {
//                        CustomBandwidthMeter(5000).start {
//                            internetQuality = it.convertToNetworkQuality()
//                        }
//                        sendAction(
//                            HomeLoadSuccess(
//                                result.value?.videos ?: emptyList(),
//                                isInitialLoad = true,
//                                internetQuality
//                            )
//                        )
//                        hasMore = (result.value?.totalPages ?: 0) > currentPage
//                    }
//                    is Result.Failure -> sendAction(HomeLoadFailure)
//                }
//                isLoading = false
//            }
        }
    }


}
