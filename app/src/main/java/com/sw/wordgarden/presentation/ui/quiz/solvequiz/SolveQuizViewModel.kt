package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.quiz.sq.SubmitSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.SubmitWqUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.mapper.ModelMapper.toSqSolveQuizEntity
import com.sw.wordgarden.presentation.mapper.ModelMapper.toWqSubmissionEntity
import com.sw.wordgarden.presentation.model.QuizModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolveQuizViewModel @Inject constructor(
    private val submitWqUseCase: SubmitWqUseCase,
    private val submitSqUseCase: SubmitSqUseCase,
) : ViewModel() {

    private val _submitEvent = MutableSharedFlow<DefaultEvent>()
    val submitEvent: SharedFlow<DefaultEvent> = _submitEvent.asSharedFlow()

    fun submitAnswers(quizModel: QuizModel) {
        viewModelScope.launch {
            runCatching {
                if (quizModel.sqId != null) {
                    submitWqUseCase.invoke(quizModel.toWqSubmissionEntity())
                } else {
                    submitSqUseCase.invoke(quizModel.toSqSolveQuizEntity())
                }
            }.onFailure {
                _submitEvent.emit(DefaultEvent.Failure(R.string.solve_quiz_msg_fail_submit))
            }.onSuccess {
                _submitEvent.emit(DefaultEvent.Success)
            }
        }
    }
}