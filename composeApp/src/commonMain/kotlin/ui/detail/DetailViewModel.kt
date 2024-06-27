package ui.detail;

import androidx.lifecycle.ViewModel
import data.HeroesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.ApiStatus
import network.NetworkRepository


class DetailViewModel(
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    private val _homeViewState: MutableStateFlow<DetailScreenState> =
        MutableStateFlow(DetailScreenState.Loading)
    val homeViewState = _homeViewState.asStateFlow()



     fun getHeroDetail(heroName : String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                networkRepository.getHeroDetail(heroName).collect { response ->
                    when (response.status) {
                        ApiStatus.LOADING -> {
                            _detailState.update { it.copy(isLoading = true) }
                        }

                        ApiStatus.SUCCESS -> {
                            _detailState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "",
                                    response.data ?: HeroesResponse()
                                )
                            }
                        }

                        ApiStatus.ERROR -> {
                            _detailState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = response.message
                                )
                            }
                        }
                    }
                    _homeViewState.value = _detailState.value.toUiState()
                }
            } catch (e: Exception) {
                _detailState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch data"
                    )
                }
            }
        }
    }

    sealed class DetailScreenState {
        data object Loading : DetailScreenState()
        data class Error(val errorMessage: String) : DetailScreenState()
        data class Success(val responseData: HeroesResponse) : DetailScreenState()
    }

    private data class DetailState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val responseData: HeroesResponse = HeroesResponse()
    ) {
        fun toUiState(): DetailScreenState {
            return if (isLoading) {
                DetailScreenState.Loading
            } else if (errorMessage?.isNotEmpty() == true) {
                DetailScreenState.Error(errorMessage)
            } else {
                DetailScreenState.Success(responseData)
            }
        }
    }
}