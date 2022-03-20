package com.honeycomb.omdbapp.presentation.display

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeycomb.omdbapp.R
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.search.entity.SearchEntity
import com.honeycomb.omdbapp.domain.search.usecase.SearchUseCase
import com.honeycomb.omdbapp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel(){

    var searchValue = Constant.firstSearchValue

    private val state = MutableStateFlow<DisplayState>(DisplayState.Init)
    val mState: StateFlow<DisplayState> get() = state

    private val products = MutableStateFlow<SearchEntity?>(null)
    val mUsers: StateFlow<SearchEntity?> get() = products

    private fun isError(value : Boolean){
        state.value = DisplayState.IsError(value)
    }

    private fun showToast(message: String){
        state.value = DisplayState.ShowToast(message)
    }

    fun fetchAllSports(userName : String){
        viewModelScope.launch {
            searchUseCase.execute(userName)
                .onStart {
                    isError(false)
                }
                .catch { exception ->
                    isError(true)
                    showToast(exception.message.toString())
                }
                .collect { result ->
                    when(result){
                        is BaseResult.Success -> {
                            products.value = result.data
                        }
                        is BaseResult.Error -> {
                            showToast(result.toString())
                        }
                    }
                }
        }
    }
}

sealed class DisplayState {
    object Init : DisplayState()
    data class IsError(val isError: Boolean) : DisplayState()
    data class ShowToast(val message : String) : DisplayState()
}