package com.honeycomb.omdbapp.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.item.entity.ItemEntity
import com.honeycomb.omdbapp.domain.item.usecase.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val itemUseCase: ItemUseCase) : ViewModel(){

    private val state = MutableStateFlow<DetailState>(DetailState.Init)
    val mState: StateFlow<DetailState> get() = state

    private val products = MutableStateFlow<ItemEntity?>(null)
    val mUsers: StateFlow<ItemEntity?> get() = products

    private fun setLoading(){
        state.value = DetailState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = DetailState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = DetailState.ShowToast(message)
    }

    fun fetchItem(searchText : String){
        viewModelScope.launch {
            itemUseCase.execute(searchText)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect {
                        result ->
                    hideLoading()
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

sealed class DetailState {
    object Init : DetailState()
    data class IsLoading(val isLoading: Boolean) : DetailState()
    data class ShowToast(val message : String) : DetailState()
}