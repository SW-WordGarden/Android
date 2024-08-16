package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuestionAnswerEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.QuestionResultEntity
import java.sql.Timestamp
import javax.inject.Inject

class CheckQuizResultUseCaseImpl @Inject constructor() : CheckQuizResultUseCase {
    override fun invoke(quiz: SelfQuizEntity, enteredAnswers: List<QuestionAnswerEntity>): SelfQuizEntity {

        val checkResult = mutableListOf<Result>()
        val quizList = quiz.quiz

        if (quizList != null) {
            val quizSize = quizList.size

            for (i in 0 until quizSize) {
                val isCorrect = quizList[i].question == enteredAnswers[i].question &&
                        quizList[i].answer == enteredAnswers[i].answer

                checkResult.add(
                    Result(
                        userAnswer = enteredAnswers[i].answer,
                        correct = isCorrect,
                        time = Timestamp(System.currentTimeMillis()),
                        questionNumber = i
                    )
                )
            }
        }

        return quiz.copy(
            quizResult = checkResult.map { it.toQuizResultEntity() }
        )
    }

    private fun Result.toQuizResultEntity() = QuestionResultEntity(
        userAnswer = userAnswer,
        correct = correct,
        time = time,
        questionNumber = questionNumber
    )

    private data class Result(
        var userAnswer: String?,
        var correct: Boolean?,
        var time: Timestamp?,
        var questionNumber: Int?,
    )
}