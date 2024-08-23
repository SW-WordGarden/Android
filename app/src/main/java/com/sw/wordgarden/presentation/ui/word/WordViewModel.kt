package com.sw.wordgarden.presentation.ui.word

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.word.GetDetailWordUseCase
import com.sw.wordgarden.domain.usecase.word.GetWeeklyCategoryWordListUseCase
import com.sw.wordgarden.domain.usecase.word.GetWeeklyCategoryWordListUseCaseImpl
import com.sw.wordgarden.domain.usecase.word.GetWeeklyWordListUseCase
import com.sw.wordgarden.domain.usecase.word.GetWordLikedStatusUseCase
import com.sw.wordgarden.domain.usecase.word.InsertLikedWordUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.model.WordModel
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
class WordViewModel @Inject constructor(
    private val getWeeklyWordListUseCase: GetWeeklyWordListUseCase,
    private val getWordLikedStatusUseCase: GetWordLikedStatusUseCase,
    private val insertLikedWordUseCase: InsertLikedWordUseCase,
) : ViewModel(){
    private val _wordEvent = MutableSharedFlow<DefaultEvent>()
    var wordEvent : SharedFlow<DefaultEvent> = _wordEvent.asSharedFlow()

    private val _wordListState = MutableStateFlow<List<WordModel>>(emptyList())
    var wordListState : StateFlow<List<WordModel>> = _wordListState.asStateFlow()

    private val _wordSelectState = MutableStateFlow<WordModel?>(null)
    var wordSelectState : StateFlow<WordModel?> = _wordSelectState.asStateFlow()

    private val _wordSelectPosition = MutableStateFlow<Int>(-1)
    var wordSelectPosition : StateFlow<Int> = _wordSelectPosition.asStateFlow()

    private val _wordLikeState = MutableStateFlow<Boolean?>(null)
    var wordLikeState:StateFlow<Boolean?> = _wordLikeState.asStateFlow()


    private lateinit var getWordList : List<WordModel>
    private lateinit var wordId :String
    private var position = -1

    init {
        getWordList()
    }
    private fun getWordList() = viewModelScope.launch{
        runCatching {
            getWordList = getWeeklyWordListUseCase()!!.mapIndexed { _, wordEntity ->
                WordModel(
                    id = wordEntity.id,
                    title = wordEntity.title,
                    description = wordEntity.description,
                    thumbnail = wordEntity.thumbnail,
                    category = wordEntity.category
                )
            }
            _wordListState.update {
                getWordList
            }

        } .onSuccess {
            _wordEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _wordEvent.emit(DefaultEvent.Failure(R.string.word_unload))
        }
    }
    fun selectWord(wordData : WordModel) = viewModelScope.launch{
        _wordSelectState.update { wordData }
        position = getWordList.indexOf(wordData)
        _wordSelectPosition.update { position }

        wordData.id?.let {
            getLikedStatus(it)
            wordId = it
        }
    }
    fun selectWord(getPosition : Int) = viewModelScope.launch{
        _wordSelectState.update { getWordList[getPosition] }
        position = getPosition
        _wordSelectPosition.update { position }

        getWordList[getPosition].id?.let {
            getLikedStatus(it)
            wordId = it
        }
    }

    private fun getLikedStatus(wordId : String) = viewModelScope.launch{
        runCatching {
            val isLiked = getWordLikedStatusUseCase(wordId)
            _wordLikeState.update {
                isLiked
            }
        }.onSuccess {
            _wordEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _wordEvent.emit(DefaultEvent.Failure(R.string.word_like))
        }
    }

    fun insertLikeState(state : Boolean) = viewModelScope.launch{
        runCatching {
            insertLikedWordUseCase(wordId, state)
        }.onSuccess {
            _wordEvent.emit(DefaultEvent.Success)
        }.onFailure {
            _wordEvent.emit(DefaultEvent.Failure(R.string.word_like_save))
        }
    }
}