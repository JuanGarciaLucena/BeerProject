package com.emebesoft.beerProject.ui.states

import com.emebesoft.beerProject.domain.model.Beer

sealed interface BeerListUiState {

    object Loading : BeerListUiState

    data class Success(val data: List<Beer>) : BeerListUiState

    data class Error(val Throwable: Throwable? = null): BeerListUiState
}