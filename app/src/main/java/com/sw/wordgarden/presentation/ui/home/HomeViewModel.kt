package com.sw.wordgarden.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.garden.GetGrowInfoUseCase
import com.sw.wordgarden.domain.usecase.word.GetRandomWordUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.mapper.ModelMapper.toTreeModel
import com.sw.wordgarden.presentation.mapper.ModelMapper.toWordModel
import com.sw.wordgarden.presentation.model.TreeModel
import com.sw.wordgarden.presentation.model.WordModel
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
class HomeViewModel @Inject constructor(
    private val getGrowInfoUseCase: GetGrowInfoUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
) : ViewModel(){
    private val _homeEvent = MutableSharedFlow<DefaultEvent>()
    var homeEvent : SharedFlow<DefaultEvent> = _homeEvent.asSharedFlow()

    private val _flowerData = MutableStateFlow<TreeModel?>(null)
    var flowerData : StateFlow<TreeModel?> = _flowerData.asStateFlow()

    private val _wordData = MutableStateFlow<WordModel?>(null)
    var wordData:StateFlow<WordModel?> = _wordData.asStateFlow()

    fun getFlowerData() = viewModelScope.launch {
        runCatching {
            val flower = getGrowInfoUseCase()?.toTreeModel()

            _flowerData.update {
                flower
            }
        }.onSuccess {
            _homeEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _homeEvent.emit(DefaultEvent.Failure(R.string.home_fail_flower))
        }
    }

    fun getWordData() = viewModelScope.launch {
        runCatching {
            val word = getRandomWordUseCase()?.toWordModel()
            _wordData.update {
                word
            }
        }.onSuccess {
            _homeEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _homeEvent.emit(DefaultEvent.Failure(R.string.home_fail_word))
        }
    }

}