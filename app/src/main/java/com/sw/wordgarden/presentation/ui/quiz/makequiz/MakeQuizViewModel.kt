package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.QuizEntity
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.entity.QuizResultEntity
import com.sw.wordgarden.domain.usecase.InsertQuizListUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.SelfQuizModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakeQuizViewModel @Inject constructor(
    private val insertQuizListUseCase: InsertQuizListUseCase
) : ViewModel() {

    private val _insertQuizEvent = MutableSharedFlow<DefaultEvent>()
    val insertQuizEvent: SharedFlow<DefaultEvent> = _insertQuizEvent.asSharedFlow()

    fun insertQuiz(title: String, selfQuizModelList: List<SelfQuizModel>) {
        val quizListEntity =
            QuizListEntity(title, convertQuizModel(selfQuizModelList), createEmptyResult())

        viewModelScope.launch {
            runCatching {
                insertQuizListUseCase.invoke(quizListEntity)
            }.onFailure {
                _insertQuizEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_fail_make_quiz))
            }.onSuccess {
                _insertQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }

    private fun convertQuizModel(selfQuizModelList: List<SelfQuizModel>): List<QuizEntity> {
        val quizEntityList: MutableList<QuizEntity> = mutableListOf()

        selfQuizModelList.forEach { selfQuizModel ->
            val quizEntity = QuizEntity(
                question = selfQuizModel.question,
                answer = selfQuizModel.answer,
                answerNumber = 0
            )

            quizEntityList.add(quizEntity)
        }

        return quizEntityList
    }

    private fun createEmptyResult(): List<QuizResultEntity> {
        val emptyQuizList: List<QuizResultEntity> =
            List(10) { QuizResultEntity(null, null, null, null) }

        return emptyQuizList
    }
}