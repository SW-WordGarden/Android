package com.sw.wordgarden.presentation.mapper

import com.sw.wordgarden.data.dto.quiz.OneQuizDto
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.entity.quiz.OneQuizEntity
import com.sw.wordgarden.domain.entity.quiz.SqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.entity.quiz.SqresultEntity
import com.sw.wordgarden.domain.entity.quiz.WqAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.domain.entity.user.UserResponseEntity
import com.sw.wordgarden.presentation.model.OneQuizModel
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.util.Constants.QUIZ_AMOUNT
import com.sw.wordgarden.presentation.model.TreeModel
import com.sw.wordgarden.presentation.model.UserResponseModel
import com.sw.wordgarden.presentation.model.WordModel

object ModelMapper {

    //quiz - wq
    @JvmName("forWq")
    fun List<WqResponseEntity>.toQuizModel(): QuizModel {
        if (this.isEmpty()) return QuizModel(qTitle = null, sqId = null, qaList = null)

        val firstEntity = this.first()
        val qTitle = firstEntity.wqTitle

        val qaList = this.map { entity ->
            QAModel(
                questionId = entity.wqId,
                question = entity.wqQuestion,
                userAnswer = entity.userAnswer,
                correctAnswer = entity.correctAnswer,
                correct = entity.correctAnswer == entity.userAnswer,
                questionType = entity.questionType,
                options = entity.options,
                word = entity.word
            )
        }

        return QuizModel(qTitle = qTitle, sqId = null, qaList = qaList)
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


    //quiz - sq
    fun SqEntity.toQuizModel() = QuizModel(
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

        if (sqresults.isNullOrEmpty()) { //결과 없는 데이터 처리
            for (i in 0 until length!!) {
                list.add(
                    QAModel(
                        questionId = questionsAndAnswers[i].sqQnum.toString(),
                        question = questionsAndAnswers[i].question,
                        userAnswer = null,
                        correctAnswer = questionsAndAnswers[i].answer,
                        correct = null,
                        questionType = null, //sq는 questionType이 없음
                        options = null, //sq는 options가 없음
                        word = null //sq는 word가 없음
                    )
                )
            }
        } else { //결과 있는 데이터 처리
            for (i in 0 until length!!) {
                list.add(
                    QAModel(
                        questionId = questionsAndAnswers[i].sqQnum.toString(),
                        question = questionsAndAnswers[i].question,
                        userAnswer = sqresults[i].userAnswer,
                        correctAnswer = questionsAndAnswers[i].answer,
                        correct = sqresults[i].correct,
                        questionType = null, //sq는 questionType이 없음
                        options = null, //sq는 options가 없음
                        word = null //sq는 word가 없음
                    )
                )
            }
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
                correct = null,
                questionType = null, //sq는 questionType이 없음
                options = null, //sq는 options가 없음
                word = null //sq는 word가 없음
            )
            list.add(entity)
        }

        return list
    }

    @JvmName("forSq")
    fun List<SqQuestionAnswerEntity>.toQuizModel(): QuizModel {
        if (this.isEmpty()) return QuizModel(qTitle = null, sqId = null, qaList = null)

        val qTitle = ""
        val qaList = this.map { entity ->
            QAModel(
                questionId = entity.sqQnum.toString(),
                question = entity.question,
                userAnswer = null,
                correctAnswer = entity.answer,
                correct = null,
                questionType = null, //sq는 questionType이 없음
                options = null, //sq는 options가 없음
                word = null //sq는 word가 없음
            )
        }

        return QuizModel(qTitle = qTitle, sqId = null, qaList = qaList)
    }

    fun createEmptySqresultEntity(): List<SqresultEntity> {
        val emptyQuizList: List<SqresultEntity> =
            List(QUIZ_AMOUNT) { SqresultEntity(null, null, null, null) }

        return emptyQuizList
    }

    //home
    fun TreeEntity.toTreeModel() = TreeModel(
        name = name,
        growthValue = growthValue ,
        growthStage = growthStage,
        date = date
    )

    fun WordEntity.toWordModel() = WordModel(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        category = category
    )
    fun UserResponseEntity.toUserResponseModel() = UserResponseModel(
        coins = coins,
        wateringCans = wateringCans,
        plantCount = plantCount
    )
    fun OneQuizEntity.toOneQuizModel() = OneQuizModel(
        id = id,
        question = question,
        title = title,
        correctAnswer = correctAnswer
    )
}