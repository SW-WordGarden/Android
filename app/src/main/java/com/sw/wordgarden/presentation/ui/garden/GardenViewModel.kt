package com.sw.wordgarden.presentation.ui.garden

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.garden.BuyWateringCansUseCase
import com.sw.wordgarden.domain.usecase.garden.GetGrowInfoUseCase
import com.sw.wordgarden.domain.usecase.garden.GetUserResourceUseCase
import com.sw.wordgarden.domain.usecase.garden.UseWateringCansUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.mapper.ModelMapper.toTreeModel
import com.sw.wordgarden.presentation.mapper.ModelMapper.toUserResponseModel
import com.sw.wordgarden.presentation.model.TreeModel
import com.sw.wordgarden.presentation.model.UserResponseModel
import com.sw.wordgarden.presentation.shared.IsLoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GardenViewModel @Inject constructor(
    private val getGrowInfoUseCase: GetGrowInfoUseCase,
    private val getUserResourceUseCase: GetUserResourceUseCase,
    private val buyWateringCansUseCase: BuyWateringCansUseCase,
    private val useWateringCansUseCase: UseWateringCansUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _gardenEvent = MutableSharedFlow<DefaultEvent>()
    var gardenEvent : SharedFlow<DefaultEvent> = _gardenEvent.asSharedFlow()

    private val _growthEvent = MutableSharedFlow<Int>()
    var growthEvent : SharedFlow<Int> = _growthEvent.asSharedFlow()

    private val _resourceData = MutableStateFlow<UserResponseModel?>(null)
    var resourceData : StateFlow<UserResponseModel?> = _resourceData.asStateFlow()

    private val _growData = MutableStateFlow<TreeModel?>(null)
    var growData : StateFlow<TreeModel?> = _growData.asStateFlow()

    private val _flowerName = MutableStateFlow<Int?>(null)
    var flowerName : StateFlow<Int?> = _flowerName.asStateFlow()

    private val _flowerImg = MutableStateFlow<List<Int>?>(null)
    var flowerImg : StateFlow<List<Int>?> = _flowerImg.asStateFlow()

    init {
        getGrowInfo()
        getUserResource()
    }

    fun getGrowInfo() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        runCatching {
            val growInfo = getGrowInfoUseCase()?.toTreeModel()
            _growData.update {
                growInfo
            }
        }.onSuccess {
            _gardenEvent.emit(DefaultEvent.Success)
            _uiState.update { it.copy(isLoading = false) }
        }.onFailure {
            _gardenEvent.emit(DefaultEvent.Failure(R.string.home_fail_flower))
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun getBookFlowerData(page:Int) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        runCatching {
            val growInfo = getGrowInfoUseCase()?.toTreeModel()
            if (growInfo != null) {
                var num = growInfo.plantNum!!
                var stage = growInfo.growthStage!!

                if(page <= num){
                    _flowerImg.emit(GetFlowerImg.getFlowerImgList(num, stage))
                    _flowerName.emit(GetFlowerImg.getFlowerName(num))
                }
                else if(page > num){
                    _flowerImg.emit(listOf())
                    _flowerName.emit(R.string.book_unknown)
                }
            }
        }.onSuccess {
            _uiState.update { it.copy(isLoading = false) }
            _gardenEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _uiState.update { it.copy(isLoading = false) }
            _gardenEvent.emit(DefaultEvent.Failure(R.string.home_fail_flower))
        }
    }

    fun getUserResource() = viewModelScope.launch {
        runCatching {
            val resourceInfo = getUserResourceUseCase()?.toUserResponseModel()
            _resourceData.update {
                resourceInfo
            }
        }.onSuccess {
            _gardenEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _gardenEvent.emit(DefaultEvent.Failure(R.string.shop_resource))
        }
    }
    fun buyWateringCans() = viewModelScope.launch {
        runCatching {
            buyWateringCansUseCase()
        }.onSuccess {
            _gardenEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _gardenEvent.emit(DefaultEvent.Failure(R.string.garden_buy_watering_cans))
        }
    }
    fun useWateringCans() = viewModelScope.launch {
        runCatching {
            useWateringCansUseCase()
        }.onSuccess {
            _gardenEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _gardenEvent.emit(DefaultEvent.Failure(R.string.garden_use_watering_cans))
        }
    }

}