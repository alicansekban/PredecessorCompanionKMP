package ui.home;

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


class HomeViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    private val _homeViewState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState.Loading)
    val homeViewState = _homeViewState.asStateFlow()

    suspend fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                networkRepository.getHeroes().collect{response ->
                    when(response.status){
                        ApiStatus.LOADING->{
                            _homeState.update { it.copy(isLoading = true) }
                        }
                        ApiStatus.SUCCESS->{
                            _homeState.update { it.copy(isLoading = false, errorMessage = "", response.data ?: emptyList()) }
                        }
                        ApiStatus.ERROR->{
                            _homeState.update { it.copy(isLoading = false,errorMessage = response.message) }
                        }
                    }
                    _homeViewState.value = _homeState.value.toUiState()
                }
            } catch (e: Exception) {
                _homeState.update { it.copy(isLoading = false,errorMessage ="Failed to fetch data") }
            }
        }
    }
    sealed class HomeScreenState {
        data object Loading: HomeScreenState()
        data class Error(val errorMessage: String): HomeScreenState()
        data class Success(val responseData: List<HeroesResponse>): HomeScreenState()
    }
    private data class HomeState(
        val isLoading:Boolean = false,
        val errorMessage: String?=null,
        val responseData: List<HeroesResponse> = emptyList()
    ) {
        fun toUiState(): HomeScreenState {
            return if (isLoading) {
                HomeScreenState.Loading
            } else if(errorMessage?.isNotEmpty()==true) {
                HomeScreenState.Error(errorMessage)
            } else {
                HomeScreenState.Success(responseData)
            }
        }
    }
}