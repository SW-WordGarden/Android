package com.sw.wordgarden.presentation.util

object Constants {
    //server text
    const val QUIZ_TYPE_WQ = "WQ"
    const val QUESTION_TYPE_OX = "ox"
    const val QUESTION_TYPE_CHOICE = "four"
    const val QUESTION_TYPE_WRITE = "write"
    const val QUESTION_O = "O"
    const val QUESTION_X = "X"
    const val QUESTION_OX_TITLE = "다음 단어와 뜻이 올바르게 연결되었다면 O, 아니라면 X를 선택하세요.\n"
    const val QUESTION_WRITE_TITLE = "다음 뜻에 해당하는 단어를 작성하세요.\n"

    //server error message
    const val USER_NOT_FOUND = "User not found"
    const val DUPLICATION_QUIZ_TITLE = "duplication"

    //args
    const val ARGS_FROM_QUIZ = "argsFromQuiz"

    //clip
    const val CLIP_LABEL = "wordgarden_code"

    //quiz
    const val QUIZ_AMOUNT = 10
    const val DAILY_LIMIT = 3

    //user
    const val NICKNAME_LIMIT = 10

    //test account
    const val TESTER_UID = "tester"
    const val TESTER_NICKNAME = "tester0809"
    const val TESTER_PROVIDER = "WORDGARDEN"

    //file
    const val USER_TERM = "doc_user_term"
    const val PRIVACY_RULE = "doc_privacy_rule"
}