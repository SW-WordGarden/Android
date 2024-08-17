package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuestionAnswerEntity
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.entity.QuizResultEntity
import java.sql.Timestamp
import javax.inject.Inject

class CheckQuizResultUseCaseImpl @Inject constructor() : CheckQuizResultUseCase {
    override fun invoke(quiz: QuizListEntity, enteredAnswers: List<QuestionAnswerEntity>): QuizListEntity {

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

    private fun Result.toQuizResultEntity() = QuizResultEntity(
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