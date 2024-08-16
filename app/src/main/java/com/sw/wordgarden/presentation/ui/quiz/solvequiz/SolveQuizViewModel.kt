package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.QuestionAnswerEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.usecase.CheckQuizResultUseCase
import com.sw.wordgarden.domain.usecase.SendQuizAnswerUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuestionAnswerModel
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
class SolveQuizViewModel @Inject constructor(
    private val sendQuizAnswerUseCase: SendQuizAnswerUseCase,
    private val checkQuizResultUseCase: CheckQuizResultUseCase
) : ViewModel() {

    private val _checkQuizResult = MutableStateFlow<SelfQuizEntity?>(null)
    val checkQuizResult: StateFlow<SelfQuizEntity?> = _checkQuizResult.asStateFlow()

    private val _sendQuizEvent = MutableSharedFlow<DefaultEvent>()
    val sendQuizEvent: SharedFlow<DefaultEvent> = _sendQuizEvent.asSharedFlow()

    fun checkQuizAnswer(quiz: SelfQuizEntity, enteredAnswers: List<QuestionAnswerModel>) {
        val enteredAnswersEntity = questionAnswerModelToEntity(enteredAnswers)
        val result = checkQuizResultUseCase.invoke(quiz, enteredAnswersEntity)

        if (result != null) {
            _checkQuizResult.update { result }
            sendQuizAnswer(quiz)
        }
    }

    private fun sendQuizAnswer(quiz: SelfQuizEntity) {
        viewModelScope.launch {
            runCatching {
                sendQuizAnswerUseCase.invoke(quiz)
            }.onFailure {
                _sendQuizEvent.emit(DefaultEvent.Failure(R.string.solve_quiz_msg_fail_submit))
            }.onSuccess {
                _sendQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }

    private fun questionAnswerModelToEntity(answer: List<QuestionAnswerModel>): List<QuestionAnswerEntity> =
        answer.map {
            QuestionAnswerEntity(
                question = it.question,
                answer = it.answer
            )
        }
}