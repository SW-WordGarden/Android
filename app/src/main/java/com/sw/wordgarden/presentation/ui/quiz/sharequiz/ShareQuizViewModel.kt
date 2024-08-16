package com.sw.wordgarden.presentation.ui.quiz.sharequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.GetFriendListUseCase
import com.sw.wordgarden.domain.usecase.ShareQuizUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.FriendModel
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
class ShareQuizViewModel @Inject constructor(
    private val getFriendListUseCase: GetFriendListUseCase,
    private val shareQuizUseCase: ShareQuizUseCase
) : ViewModel() {

    private val _getFriendEvent = MutableSharedFlow<DefaultEvent>()
    val getFriendEvent: SharedFlow<DefaultEvent> = _getFriendEvent.asSharedFlow()

    private val _getFriendList = MutableStateFlow<List<FriendModel>>(emptyList())
    val getFriendList: StateFlow<List<FriendModel>> = _getFriendList.asStateFlow()

    private val _shareEvent = MutableSharedFlow<DefaultEvent>()
    val shareEvent: SharedFlow<DefaultEvent> = _shareEvent.asSharedFlow()

    init {
        getFriends()
    }

    private fun getFriends() =
        viewModelScope.launch {
            runCatching {
                val friendList = getFriendListUseCase()?.map {
                    FriendModel(
                        uid = it.uid ?: "",
                        nickname = it.nickname ?: "",
                        thumbnail = it.thumbnail ?: ""
                    )
                }

                if (friendList != null) {
                    _getFriendList.update { friendList }
                } else {
                    _getFriendList.update { emptyList() }
                }

            }.onFailure {
                _getFriendEvent.emit(DefaultEvent.Failure(R.string.share_quiz_msg_fail_load_friends))
            }.onSuccess {
                _getFriendEvent.emit(DefaultEvent.Success)
            }
        }

    fun shareQuiz(quizId: String, friendUid: String) {
        viewModelScope.launch {
            runCatching {
                shareQuizUseCase.invoke(quizId, friendUid)
            }.onFailure {
                _shareEvent.emit(DefaultEvent.Failure(R.string.share_quiz_msg_fail_to_share))
            }.onSuccess {
                _shareEvent.emit(DefaultEvent.Success)
            }
        }
    }
}