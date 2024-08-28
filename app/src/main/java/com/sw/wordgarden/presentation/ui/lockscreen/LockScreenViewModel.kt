package com.sw.wordgarden.presentation.ui.lockscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.quiz.GetOneQuizUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.mapper.ModelMapper.toOneQuizModel
import com.sw.wordgarden.presentation.mapper.ModelMapper.toTreeModel
import com.sw.wordgarden.presentation.model.OneQuizModel
import com.sw.wordgarden.presentation.model.TreeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class LockScreenViewModel @Inject constructor(
    private val getOneQuizUseCase: GetOneQuizUseCase,
) : ViewModel(){
    private val _lockEvent = MutableSharedFlow<DefaultEvent>()
    var lockEvent : SharedFlow<DefaultEvent> = _lockEvent.asSharedFlow()
    private val _quizData = MutableStateFlow<OneQuizModel?>(null)
    var quizData : StateFlow<OneQuizModel?> = _quizData.asStateFlow()

    init {
        getOneQuizData()
    }
    private fun getOneQuizData() = viewModelScope.launch {
        runCatching {
            val quiz = getOneQuizUseCase()?.toOneQuizModel()
            _quizData.emit(quiz)
        }.onSuccess {
            _lockEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _lockEvent.emit(DefaultEvent.Failure(R.string.home_fail_flower))
        }
    }
}