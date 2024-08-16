package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.QuestionEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.QuestionResultEntity
import com.sw.wordgarden.domain.usecase.InsertQuizListUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.ErrorMessage
import com.sw.wordgarden.presentation.model.QuestionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MakeQuizViewModel @Inject constructor(
    private val insertQuizListUseCase: InsertQuizListUseCase
) : ViewModel() {

    private val _insertQuizEvent = MutableSharedFlow<DefaultEvent>()
    val insertQuizEvent: SharedFlow<DefaultEvent> = _insertQuizEvent.asSharedFlow()

    fun insertQuiz(questionModelList: List<QuestionModel>) {
        val selfQuizEntity =
            SelfQuizEntity("", "", convertQuizModel(questionModelList), createEmptyResult())

        viewModelScope.launch {
            runCatching {
                insertQuizListUseCase.invoke(selfQuizEntity)
            }.onFailure { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorResponse = throwable.response()?.errorBody()?.string()
                        if (throwable.code() == 403 && errorResponse == ErrorMessage.DUPLICATION_QUIZ_TITLE.name) {
                            _insertQuizEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_duplication_quiz_title))
                        } else {
                            _insertQuizEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_fail_make_quiz))
                        }
                    }
                }
            }.onSuccess {
                _insertQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }

    private fun convertQuizModel(questionModelList: List<QuestionModel>): List<QuestionEntity> {
        val questionEntityList: MutableList<QuestionEntity> = mutableListOf()

        questionModelList.forEach { selfQuizModel ->
            val questionEntity = QuestionEntity(
                question = selfQuizModel.question,
                answer = selfQuizModel.answer,
                questionNumber = 0
            )

            questionEntityList.add(questionEntity)
        }

        return questionEntityList
    }

    private fun createEmptyResult(): List<QuestionResultEntity> {
        val emptyQuizList: List<QuestionResultEntity> =
            List(10) { QuestionResultEntity(null, null, null, null) }

        return emptyQuizList
    }
}