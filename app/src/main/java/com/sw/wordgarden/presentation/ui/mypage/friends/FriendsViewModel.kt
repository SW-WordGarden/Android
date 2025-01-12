package com.sw.wordgarden.presentation.ui.mypage.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.usecase.user.AddFriendUseCase
import com.sw.wordgarden.domain.usecase.user.DeleteFriendUseCase
import com.sw.wordgarden.domain.usecase.user.GetFriendsUseCase
import com.sw.wordgarden.domain.usecase.user.ReportFriendUseCase
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
class FriendsViewModel @Inject constructor(
    private val getFriendsUseCase: GetFriendsUseCase,
    private val addFriendsUseCase: AddFriendUseCase,
    private val deleteFriendUseCase: DeleteFriendUseCase,
    private val reportFriendUseCase: ReportFriendUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getFriendsEvent = MutableSharedFlow<DefaultEvent>()
    val getFriendsEvent: SharedFlow<DefaultEvent> = _getFriendsEvent.asSharedFlow()

    private val _getFriends = MutableStateFlow<FriendListEntity?>(null)
    val getFriends: StateFlow<FriendListEntity?> = _getFriends.asStateFlow()

    private val _addFriendEvent = MutableSharedFlow<DefaultEvent>()
    val addFriendEvent: SharedFlow<DefaultEvent> = _addFriendEvent.asSharedFlow()

    private val _deleteFriendEvent = MutableSharedFlow<DefaultEvent>()
    val deleteFriendEvent: SharedFlow<DefaultEvent> = _deleteFriendEvent.asSharedFlow()

    private val _reportFriendEvent = MutableSharedFlow<DefaultEvent>()
    val reportFriendEvent: SharedFlow<DefaultEvent> = _reportFriendEvent.asSharedFlow()

    init {
        getFriends()
    }

    fun getFriends() =
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val response = getFriendsUseCase()

                if (response != null) {
                    _getFriends.update { response }
                } else {
                    _getFriends.update { null }
                }

            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getFriendsEvent.emit(DefaultEvent.Failure(R.string.mypage_friends_msg_fail_load_friends))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getFriendsEvent.emit(DefaultEvent.Success)
            }
        }

    fun addFriend(friendUrl: String?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                addFriendsUseCase.invoke(friendUrl ?: "")
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _addFriendEvent.emit(DefaultEvent.Failure(R.string.mypage_friends_msg_fail_add_friend))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _addFriendEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun deleteFriend(friendUid: String?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                deleteFriendUseCase.invoke(friendUid ?: "")
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _deleteFriendEvent.emit(DefaultEvent.Failure(R.string.mypage_friends_msg_fail_delete_friend))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _deleteFriendEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun reportFriend(friendUid: String?, content: String?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                reportFriendUseCase.invoke(friendUid ?: "", content)
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _reportFriendEvent.emit(DefaultEvent.Failure(R.string.mypage_friends_msg_fail_report_friend))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _reportFriendEvent.emit(DefaultEvent.Success)
            }
        }
    }
}