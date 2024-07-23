package com.sw.wordgarden.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.GetUidUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Test용 viewmodel입니다.
 *
 * datastore에서 uid를 가져와 활용하는 예시를 작성해둡니다.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUidUseCase: GetUidUseCase
) : ViewModel() {

    private val _getUidEvent = MutableSharedFlow<DefaultEvent>()
    val getUidEvent: SharedFlow<DefaultEvent> = _getUidEvent.asSharedFlow()

    private val _uid = MutableSharedFlow<String>()
    val uid: SharedFlow<String> = _uid.asSharedFlow()

    fun getUid() {
        viewModelScope.launch {
            runCatching {
                val uidValue = getUidUseCase.invoke()

                if (uidValue != null) {
                    _uid.emit(uidValue)
                } else {
                    _getUidEvent.emit(DefaultEvent.Failure(R.string.uid_msg_fail_check))
                }

            }.onFailure {
                _getUidEvent.emit(DefaultEvent.Failure(R.string.uid_msg_fail_check))
            }.onSuccess {
                _getUidEvent.emit(DefaultEvent.Success)
            }
        }
    }
}