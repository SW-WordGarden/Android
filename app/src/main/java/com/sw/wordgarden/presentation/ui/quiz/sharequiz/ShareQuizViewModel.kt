package com.sw.wordgarden.presentation.ui.quiz.sharequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.usecase.user.GetFriendsUseCase
import com.sw.wordgarden.domain.usecase.quiz.common.ShareQuizUseCase
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
    private val shareQuizUseCase: ShareQuizUseCase
) : ViewModel() {

    private val _getFriendEvent = MutableSharedFlow<DefaultEvent>()
    val getFriendEvent: SharedFlow<DefaultEvent> = _getFriendEvent.asSharedFlow()

    private val _getFriendList = MutableStateFlow<List<UserEntity>>(emptyList())
    val getFriendList: StateFlow<List<UserEntity>> = _getFriendList.asStateFlow()

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

    fun shareQuiz(quizKey: QuizKey, friendUid: String) {
        viewModelScope.launch {
            runCatching {
                if (quizKey.isWq == true) {
                    shareQuizUseCase.invoke(quizKey.sqId ?: "", friendUid)
                } else {
                    shareQuizUseCase.invoke(quizKey.sqId ?: "", friendUid)
                }
            }.onFailure {
                _shareEvent.emit(DefaultEvent.Failure(R.string.share_quiz_msg_fail_to_share))
            }.onSuccess {
                _shareEvent.emit(DefaultEvent.Success)
            }
        }
    }
}