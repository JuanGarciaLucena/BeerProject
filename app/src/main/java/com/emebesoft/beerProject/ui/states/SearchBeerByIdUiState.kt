package com.emebesoft.beerProject.ui.states

import com.emebesoft.beerProject.domain.model.Beer

sealed interface SearchBeerByIdUiState {

    object Loading : SearchBeerByIdUiState

    data class Success(val data: List<Beer>) : SearchBeerByIdUiState

    data class Error(val Throwable: Throwable? = null) : SearchBeerByIdUiState
}