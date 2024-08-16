package com.sw.wordgarden.presentation.ui.mypage.myselfquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserByQuizIdUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserUseCase
import com.sw.wordgarden.presentation.mapper.ModelMapper.toModel
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizSummaryModel
import com.sw.wordgarden.presentation.model.SelfQuizModel
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
class MySelfQuizViewModel @Inject constructor(
    private val getQuizListMadeByUserUseCase: GetQuizListMadeByUserUseCase,
    private val getQuizListMadeByUserByQuizIdUseCase: GetQuizListMadeByUserByQuizIdUseCase,
) : ViewModel() {

    private val _getMySelfQuizTitleListEvent = MutableSharedFlow<DefaultEvent>()
    val getMySelfQuizTitleListEvent: SharedFlow<DefaultEvent> = _getMySelfQuizTitleListEvent.asSharedFlow()

    private val _getSelfQuizTitleList = MutableStateFlow<List<QuizSummaryModel>>(emptyList())
    val getMySelfQuizTitleList: StateFlow<List<QuizSummaryModel>> = _getSelfQuizTitleList.asStateFlow()

    private val _getMySelfQuizByQuizIdEvent = MutableSharedFlow<DefaultEvent>()
    val getMySelfQuizByQuizIdEvent: SharedFlow<DefaultEvent> = _getMySelfQuizByQuizIdEvent.asSharedFlow()

    private val _getMySelfQuizByQuizId = MutableStateFlow<SelfQuizModel?>(null)
    val getMySelfQuizByQuizId: StateFlow<SelfQuizModel?> = _getMySelfQuizByQuizId.asStateFlow()

    init {
        getQuizTitleList()
    }

    private fun getQuizTitleList() =
        viewModelScope.launch {
            runCatching {
                val response = getQuizListMadeByUserUseCase()

                if (response != null) {
                    val quizSummaryList = response.map { it.toModel() }
                    _getSelfQuizTitleList.update { quizSummaryList }
                } else {
                    _getSelfQuizTitleList.update { emptyList() }
                }

            }.onFailure {
                _getMySelfQuizTitleListEvent.emit(DefaultEvent.Failure(R.string.mypage_my_made_quiz_msg_fail_load_quiz_title_list))
            }.onSuccess {
                _getMySelfQuizTitleListEvent.emit(DefaultEvent.Success)
            }
        }

    fun getQuizList(quizId: String) {
        viewModelScope.launch {
            runCatching {
                val quizList = getQuizListMadeByUserByQuizIdUseCase(quizId)

                if (quizList != null) {
                    val quizListModel = quizList.toModel()

                    _getMySelfQuizByQuizId.update { quizListModel }
                }

            }.onFailure {
                _getMySelfQuizByQuizIdEvent.emit(DefaultEvent.Failure(R.string.mypage_my_made_quiz_msg_fail_load_quiz_list))
            }.onSuccess {
                _getMySelfQuizByQuizIdEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun clearQuizByQuizId() {
        _getMySelfQuizByQuizId.value = null
    }
}