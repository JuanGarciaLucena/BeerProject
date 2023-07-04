package com.emebesoft.beerProject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emebesoft.beerProject.data.model.toDomain
import com.emebesoft.beerProject.data.repository.BeerRepositoryImpl
import com.emebesoft.beerProject.ui.states.BeerListUiState
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

    private val _beerListFlow = MutableStateFlow<BeerListUiState>(BeerListUiState.Loading)
    val beerListFlow: StateFlow<BeerListUiState> = _beerListFlow.asStateFlow()

    init {
        fetchBeers()
    }

    private fun fetchBeers(){
        viewModelScope.launch {
            beerRepositoryImpl.fetchBeers().asResult().collect { result ->
                _beerListFlow.update {
                    when(result) {
                        is Result.Loading ->{
                            Log.i("STATE", "LOADING")
                            BeerListUiState.Loading
                        }
                        is Result.Success -> {
                            Log.i("STATE", "SUCCESS")
                            BeerListUiState.Success(result.data.map { beerItem -> beerItem.toDomain()})
                        }
                        is Result.Error -> {
                            Log.i("STATE", result.exception!!.message!!)
                            BeerListUiState.Error(result.exception)
                        }
                    }
                }
            }
        }
    }
}