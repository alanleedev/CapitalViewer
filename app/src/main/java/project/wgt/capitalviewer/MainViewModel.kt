package project.wgt.capitalviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import project.wgt.capitalviewer.di.Dependencies
import project.wgt.capitalviewer.repository.CapitalRepository

class MainViewModel(
    val repository: CapitalRepository,
) : ViewModel() {

    init {
        println("init")
    }

    fun test() {
        println("test")
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