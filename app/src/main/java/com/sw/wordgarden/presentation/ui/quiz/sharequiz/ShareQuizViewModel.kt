package com.sw.wordgarden.presentation.ui.quiz.sharequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.usecase.alarm.MakeSharingAlarmUseCase
import com.sw.wordgarden.domain.usecase.user.GetFriendsUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.QuizKey
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
    private val getFriendsUseCase: GetFriendsUseCase,
    private val makeSharingAlarmQuizUseCase: MakeSharingAlarmUseCase
) : ViewModel() {

    private val _getFriendsEvent = MutableSharedFlow<DefaultEvent>()
    val getFriendsEvent: SharedFlow<DefaultEvent> = _getFriendsEvent.asSharedFlow()

    private val _getFriends = MutableStateFlow<FriendListEntity?>(null)
    val getFriends: StateFlow<FriendListEntity?> = _getFriends.asStateFlow()

    private val _shareEvent = MutableSharedFlow<DefaultEvent>()
    val shareEvent: SharedFlow<DefaultEvent> = _shareEvent.asSharedFlow()

    init {
        getFriends()
    }

    private fun getFriends() =
        viewModelScope.launch {
            runCatching {
                val friendList = getFriendsUseCase()
                if (friendList != null) {
                    _getFriends.update { friendList }
                } else {
                    _getFriends.update { null }
                }

            }.onFailure {
                _getFriendsEvent.emit(DefaultEvent.Failure(R.string.share_quiz_msg_fail_load_friends))
            }.onSuccess {
                _getFriendsEvent.emit(DefaultEvent.Success)
            }
        }

    fun makeSharingQuizAlarm(quizKey: QuizKey, friendUid: String) {
        viewModelScope.launch {
            runCatching {
                if (quizKey.isWq == true) {
                    makeSharingAlarmQuizUseCase.invoke(friendUid, quizKey.qTitle ?: "", )
                } else {
                    makeSharingAlarmQuizUseCase.invoke(friendUid, quizKey.sqId ?: "", )
                }
            }.onFailure {
                _shareEvent.emit(DefaultEvent.Failure(R.string.share_quiz_msg_fail_to_share))
            }.onSuccess {
                _shareEvent.emit(DefaultEvent.Success)
            }
        }
    }
}