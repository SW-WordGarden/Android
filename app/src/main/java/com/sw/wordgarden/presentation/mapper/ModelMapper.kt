package com.sw.wordgarden.presentation.mapper

import com.sw.wordgarden.domain.entity.quiz.SqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.entity.quiz.SqresultEntity
import com.sw.wordgarden.domain.entity.quiz.WqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.model.QuizModel

object ModelMapper {

    //quiz - wq
    fun List<WqResponseEntity>.toModel(): QuizModel {
        if (this.isEmpty()) return QuizModel(qTitle = null, sqId = null, qaList = null)

        val firstEntity = this.first()
        val qTitle = firstEntity.wqTitle

        val qaList =  this.map { entity ->
            QAModel(
                questionId = entity.wqId,
                question = entity.wqQuestion,
                userAnswer = entity.userAnswer,
                correctAnswer = entity.correctAnswer,
                correct = entity.correctAnswer == entity.userAnswer
            )
        }

        return QuizModel(qTitle = qTitle, sqId = null, qaList = qaList)
    }

    //quiz - sq
    fun SqEntity.toModel() = QuizModel(
        qTitle = quizTitle,
        sqId = sqId,
        qaList = qaListEntityToModel(questionsAndAnswers, sqresults)
    )

    private fun qaListEntityToModel(
        questionsAndAnswers: List<SqQuestionAnswerEntity>?,
        sqresults: List<SqresultEntity>?
    ): List<QAModel> {
        val length = questionsAndAnswers?.size
        val list: MutableList<QAModel> = mutableListOf()

        for (i in 0 until length!!) {
            list.add(
                QAModel(
                    questionId = "", //TODO: 어떤 값 가져와야 하는지 확인
                    question = questionsAndAnswers[i].question,
                    userAnswer = sqresults?.get(i)?.userAnswer,
                    correctAnswer = questionsAndAnswers[i].answer,
                    correct = sqresults?.get(i)?.correct
                )
            )
        }

        return list
    }

    fun QuizModel.toSqSolveQuizEntity(): SqSolveQuizEntity {
        val entity = SqSolveQuizEntity(
            uid = null,
            quizTitle = this.qTitle,
            answers = this.qaList?.toSqAnswerEntity()
        )

        return entity
    }

    private fun List<QAModel>.toSqAnswerEntity(): List<SqAnswerEntity> {
        val list: MutableList<SqAnswerEntity> = mutableListOf()

        this.forEach { model ->
            val entity = SqAnswerEntity(
                questionId = model.questionId?.toLong(),
                userAnswer = model.userAnswer,
            )
            list.add(entity)
        }

        return list
    }

    fun QuizModel.toWqSubmissionEntity(): WqSubmissionEntity {
        val entity = WqSubmissionEntity(
            uid = null,
            answers = this.qaList?.toWqAnswerEntity()
        )

        return entity
    }

    private fun List<QAModel>.toWqAnswerEntity(): List<WqAnswerEntity> {
        val list: MutableList<WqAnswerEntity> = mutableListOf()

        this.forEach { model ->
            val entity = WqAnswerEntity(
                wqId = model.questionId,
                uWqA = model.userAnswer,
            )
            list.add(entity)
        }
        return list
    }

    fun List<QAModel>.toSqQuestionAnswerEntity(): List<SqQuestionAnswerEntity> {
        val list: MutableList<SqQuestionAnswerEntity> = mutableListOf()

        this.forEach { model ->
            val entity = SqQuestionAnswerEntity(
                question = model.question,
                answer = model.correctAnswer,
                sqQnum = 0
            )
            list.add(entity)
        }

        return list
    }

    fun List<SqQuestionAnswerEntity>.toListQAModel(): List<QAModel> {
        val list: MutableList<QAModel> = mutableListOf()

        this.forEach { model ->
            val entity = QAModel(
                questionId = null,
                question = model.question,
                userAnswer = null,
                correctAnswer = model.answer,
                correct = null
            )
            list.add(entity)
        }

        return list
    }

    fun createEmptySqresultEntity(): List<SqresultEntity> {
        val emptyQuizList: List<SqresultEntity> =
            List(10) { SqresultEntity(null, null, null, null) }

        return emptyQuizList
    }
}