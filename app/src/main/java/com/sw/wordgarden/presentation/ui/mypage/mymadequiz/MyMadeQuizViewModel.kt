package com.sw.wordgarden.presentation.ui.mypage.mymadequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserByTitleUseCase
import com.sw.wordgarden.domain.usecase.GetQuizListMadeByUserUseCase
import com.sw.wordgarden.presentation.mapper.ModelMapper.toModel
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizListModel
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
class MyMadeQuizViewModel @Inject constructor(
    private val getQuizListMadeByUserUseCase: GetQuizListMadeByUserUseCase,
    private val getQuizListMadeByUserByTitleUseCase: GetQuizListMadeByUserByTitleUseCase,
) : ViewModel() {

    private val _getMyMadeQuizTitleListEvent = MutableSharedFlow<DefaultEvent>()
    val getMyMadeQuizTitleListEvent: SharedFlow<DefaultEvent> = _getMyMadeQuizTitleListEvent.asSharedFlow()

    private val _getMyMadeQuizTitleList = MutableStateFlow<List<String>>(emptyList())
    val getMyMadeQuizTitleList: StateFlow<List<String>> = _getMyMadeQuizTitleList.asStateFlow()

    private val _getMyMadeQuizByTitleEvent = MutableSharedFlow<DefaultEvent>()
    val getMyMadeQuizByTitleEvent: SharedFlow<DefaultEvent> = _getMyMadeQuizByTitleEvent.asSharedFlow()

    private val _getMyMadeQuizByTitle = MutableStateFlow<QuizListModel?>(null)
    val getMyMadeQuizByTitle: StateFlow<QuizListModel?> = _getMyMadeQuizByTitle.asStateFlow()

    init {
        getQuizTitleList()
    }

    private fun getQuizTitleList() =
        viewModelScope.launch {
            runCatching {
                val quizTitleList = getQuizListMadeByUserUseCase()

                if (quizTitleList != null) {
                    _getMyMadeQuizTitleList.update { quizTitleList }
                } else {
                    _getMyMadeQuizTitleList.update { emptyList() }
                }

            }.onFailure {
                _getMyMadeQuizTitleListEvent.emit(DefaultEvent.Failure(R.string.mypage_my_made_quiz_msg_fail_load_quiz_title_list))
            }.onSuccess {
                _getMyMadeQuizTitleListEvent.emit(DefaultEvent.Success)
            }
        }

    fun getQuizList(title: String) {
        viewModelScope.launch {
            runCatching {
                val quizList = getQuizListMadeByUserByTitleUseCase(title)

                if (quizList != null) {
                    val quizListModel = quizList.toModel()

                    _getMyMadeQuizByTitle.update { quizListModel }
                }

            }.onFailure {
                _getMyMadeQuizByTitleEvent.emit(DefaultEvent.Failure(R.string.mypage_my_made_quiz_msg_fail_load_quiz_list))
            }.onSuccess {
                _getMyMadeQuizByTitleEvent.emit(DefaultEvent.Success)
            }
        }
    }
}