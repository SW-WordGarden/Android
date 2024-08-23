package com.sw.wordgarden.presentation.ui.mypage.weeklyquizcheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity
import com.sw.wordgarden.domain.usecase.quiz.wq.GetWrongWqsUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.shared.IsLoadingUiState
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
class WeeklyQuizCheckViewModel @Inject constructor(
    private val getWrongWqsUseCase: GetWrongWqsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getWrongWqsEvent = MutableSharedFlow<DefaultEvent>()
    val getWrongWqsEvent: SharedFlow<DefaultEvent> = _getWrongWqsEvent.asSharedFlow()

    private val _getWrongWqs = MutableStateFlow<List<WqWrongAnswerEntity>>(emptyList())
    val getWrongWqs: StateFlow<List<WqWrongAnswerEntity>> = _getWrongWqs.asStateFlow()

    init {
        getWrongWqsData()
    }

    private fun getWrongWqsData() =
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val response = getWrongWqsUseCase()

                if (response != null) {
                    _getWrongWqs.update { response }
                } else {
                    _getWrongWqs.update { emptyList() }
                }

            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getWrongWqsEvent.emit(DefaultEvent.Failure(R.string.mypage_my_solved_quiz_msg_fail_load_wrong_quiz))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getWrongWqsEvent.emit(DefaultEvent.Success)
            }
        }
}