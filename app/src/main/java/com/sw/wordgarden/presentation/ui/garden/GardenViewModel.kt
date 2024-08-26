package com.sw.wordgarden.presentation.ui.garden

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
    private val _gardenEvent = MutableSharedFlow<DefaultEvent>()
    var gardenEvent : SharedFlow<DefaultEvent> = _gardenEvent.asSharedFlow()

    private val _resourceData = MutableStateFlow<UserResponseModel?>(null)
    var resourceData : StateFlow<UserResponseModel?> = _resourceData.asStateFlow()

    private val _growData = MutableStateFlow<TreeModel?>(null)
    var growData : StateFlow<TreeModel?> = _growData.asStateFlow()

    init {
        getGrowInfo()
    }

    fun getGrowInfo() = viewModelScope.launch {
        runCatching {
            val growInfo = getGrowInfoUseCase()?.toTreeModel()
            _growData.update {
                growInfo
            }
        }.onSuccess {
            _gardenEvent.emit(DefaultEvent.Success)
        }.onFailure {
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