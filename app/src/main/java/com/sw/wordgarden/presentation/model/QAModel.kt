package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QAModel(
    var questionId: String?,
    var question: String?,
    var userAnswer: String?,
    var correctAnswer: String?,
    val correct: Boolean?,
    val questionType: String?,
    val options: List<String>?,
    val word: String?,
) : Parcelable {
    companion object {
        fun emptyQAModel(): QAModel {
            return QAModel(
                questionId = "",
                question = "",
                userAnswer = "",
                correctAnswer = "",
                correct = false,
                questionType = "",
                options = emptyList(),
                word = ""
            )
        }
    }
}
