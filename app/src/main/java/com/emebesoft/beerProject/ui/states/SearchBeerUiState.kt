package com.emebesoft.beerProject.ui.states

import com.emebesoft.beerProject.domain.model.Beer

sealed interface SearchBeerUiState{

    object Loading : SearchBeerUiState

    data class Success(val data: List<Beer>) : SearchBeerUiState

    data class Error(val Throwable: Throwable? = null): SearchBeerUiState
}