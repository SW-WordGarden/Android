package com.sw.wordgarden.presentation.ui.mypage.mysolvedquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.usecase.quiz.common.GetSolvedQuizTitlesUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
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
class MySolvedQuizViewModel @Inject constructor(
    private val getSolvedTitlesUseCase: GetSolvedQuizTitlesUseCase,
) : ViewModel() {

    private val _getQuizTitlesEvent = MutableSharedFlow<DefaultEvent>()
    val getQuizTitlesEvent: SharedFlow<DefaultEvent> = _getQuizTitlesEvent.asSharedFlow()

    private val _getQuizTitles = MutableStateFlow<List<QuizSummaryEntity>>(emptyList())
    val getQuizTitles: StateFlow<List<QuizSummaryEntity>> = _getQuizTitles.asStateFlow()

    init {
        getSolvedQuizTitles()
    }

    private fun getSolvedQuizTitles() {
        viewModelScope.launch {
            runCatching {
                getSolvedTitlesUseCase.invoke()
            }.onFailure {
                _getQuizTitlesEvent.emit(DefaultEvent.Failure(R.string.mypage_my_solved_quiz_msg_fail_load_quiz_title_list))
            }.onSuccess { limit ->
                _getQuizTitles.update { limit }
                _getQuizTitlesEvent.emit(DefaultEvent.Success)
            }
        }
    }
}