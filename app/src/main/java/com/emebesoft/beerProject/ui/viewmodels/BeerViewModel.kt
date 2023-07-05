package com.emebesoft.beerProject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emebesoft.beerProject.data.model.toDomain
import com.emebesoft.beerProject.data.repository.BeerRepositoryImpl
import com.emebesoft.beerProject.ui.states.BeerListUiState
import com.emebesoft.beerProject.ui.states.SearchBeerByIdUiState
import com.emebesoft.beerProject.ui.states.SearchBeerUiState
import com.emebesoft.beerProject.utils.Result
import com.emebesoft.beerProject.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(private val beerRepositoryImpl: BeerRepositoryImpl): ViewModel() {

    private val _searchBeerListFlow = MutableStateFlow<SearchBeerUiState>(SearchBeerUiState.Success(
        emptyList()
    ))
    val searchBeerListFlow: StateFlow<SearchBeerUiState> = _searchBeerListFlow.asStateFlow()

    private val _searchBeerByIdFlow = MutableStateFlow<SearchBeerByIdUiState>(SearchBeerByIdUiState.Loading)
    val searchBeerByIdFlow: StateFlow<SearchBeerByIdUiState> = _searchBeerByIdFlow.asStateFlow()

    fun searchBeers(beerQuery: String){
        viewModelScope.launch {
            if (beerQuery.isEmpty()) {
                _searchBeerListFlow.value = SearchBeerUiState.Success(emptyList())
            } else {
                beerRepositoryImpl.searchBeer(beerQuery).asResult().collect { result ->
                    _searchBeerListFlow.update {
                        when (result) {
                            is Result.Loading -> {
                                Log.i("STATE", "LOADING")
                                SearchBeerUiState.Loading
                            }

                            is Result.Success -> {
                                Log.i("STATE", "SUCCESS")
                                SearchBeerUiState.Success(result.data.map { beerItem -> beerItem.toDomain() })
                            }

                            is Result.Error -> {
                                Log.i("STATE", result.exception!!.message!!)
                                SearchBeerUiState.Error(result.exception)
                            }
                        }
                    }
                }
            }
        }
    }

    fun searchBeerById(beerId: String){
        viewModelScope.launch {
            beerRepositoryImpl.searchBeerById(beerId).asResult().collect { result  ->
                _searchBeerByIdFlow.update {
                    when (result) {
                        is Result.Loading -> {
                            Log.i("STATE", "LOADING")
                            SearchBeerByIdUiState.Loading
                        }

                        is Result.Success -> {
                            Log.i("STATE", "SUCCESS")
                            SearchBeerByIdUiState.Success(result.data.map { beerItem -> beerItem.toDomain() })
                        }

                        is Result.Error -> {
                            Log.i("STATE", result.exception!!.message!!)
                            SearchBeerByIdUiState.Error(result.exception)
                        }
                    }
                }
            }
        }
    }
}