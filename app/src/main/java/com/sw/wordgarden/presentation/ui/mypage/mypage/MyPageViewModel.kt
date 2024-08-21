package com.sw.wordgarden.presentation.ui.mypage.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqTitlesUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
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
class MyPageViewModel @Inject constructor(
    private val getUserSqTitlesUseCase: GetUserSqTitlesUseCase,
) : ViewModel() {

    private val _getMySelfQuizTitleListEvent = MutableSharedFlow<DefaultEvent>()
    val getMySelfQuizTitleListEvent: SharedFlow<DefaultEvent> = _getMySelfQuizTitleListEvent.asSharedFlow()

    private val _getSelfQuizTitleList = MutableStateFlow<List<QuizSummaryEntity>>(emptyList())
    val getMySelfQuizTitleList: StateFlow<List<QuizSummaryEntity>> = _getSelfQuizTitleList.asStateFlow()

    init {
        getQuizTitleList()
    }

    private fun getQuizTitleList() =
        viewModelScope.launch {
            runCatching {
                val response = getUserSqTitlesUseCase()

                if (response != null) {
                    _getSelfQuizTitleList.update { response }
                } else {
                    _getSelfQuizTitleList.update { emptyList() }
                }

            }.onFailure {
                _getMySelfQuizTitleListEvent.emit(DefaultEvent.Failure(R.string.mypage_my_self_quiz_msg_fail_load_quiz_title_list))
            }.onSuccess {
                _getMySelfQuizTitleListEvent.emit(DefaultEvent.Success)
            }
        }
}