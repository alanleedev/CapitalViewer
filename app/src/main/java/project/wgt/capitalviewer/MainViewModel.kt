package project.wgt.capitalviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.wgt.capitalviewer.di.Dependencies
import project.wgt.capitalviewer.model.CountryItem
import project.wgt.capitalviewer.repository.CapitalRepository
import kotlin.coroutines.cancellation.CancellationException

sealed class CountriesUiState {
    data object Loading: CountriesUiState()
    data class Success(val countries: List<CountryItem>): CountriesUiState()
    data class Error(val errorMessage: String): CountriesUiState()
}

class MainViewModel(
    private val repository: CapitalRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountriesUiState>(CountriesUiState.Loading)
    val uiState: StateFlow<CountriesUiState> = _uiState.asStateFlow()

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                _uiState.update { CountriesUiState.Loading }
                val countries = repository.getCountries()
                _uiState.update { CountriesUiState.Success(countries) }
            } catch(cancellationException: CancellationException) {
                throw cancellationException
            } catch(exception: Exception) {
                _uiState.update {
                    CountriesUiState.Error(exception.message ?: "Unknown Error")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return MainViewModel(
                    Dependencies.repository,
                ) as T
            }
        }
    }
}