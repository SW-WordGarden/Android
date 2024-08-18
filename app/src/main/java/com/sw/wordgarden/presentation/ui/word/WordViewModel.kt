package com.sw.wordgarden.presentation.ui.word

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.domain.usecase.GetWeeklyWordListUseCase
import com.sw.wordgarden.presentation.model.WordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val getWeeklyWordListUseCase: GetWeeklyWordListUseCase,
) : ViewModel(){
    private val _wordListState = MutableStateFlow<List<WordModel>>(emptyList())
    var wordListState : StateFlow<List<WordModel>> = _wordListState.asStateFlow()

    private val _wordSelectState = MutableStateFlow<WordModel?>(null)
    var wordSelectState : StateFlow<WordModel?> = _wordSelectState.asStateFlow()

    private val _wordSelectPosition = MutableStateFlow<Int>(-1)
    var wordSelectPosition : StateFlow<Int> = _wordSelectPosition.asStateFlow()

    private lateinit var getWordList : List<WordModel>
    private var position = -1

    init {
        getWordList()
    }
    private fun getWordList() = viewModelScope.launch{
        runCatching {
//            getWordList = getWeeklyWordListUseCase()!!.mapIndexed { _, wordEntity ->
//                WordModel(
//                    id = wordEntity.id,
//                    title = wordEntity.title,
//                    description = wordEntity.description,
//                    thumbnail = wordEntity.thumbnail,
//                    category = wordEntity.category
//                )
//            }
            //dummy data
            getWordList = listOf(
                WordModel("1", "a", "aaaaaaa", "", ""),
                WordModel("2", "a", "aaaaaaa", "", ""),
                WordModel("3", "a", "aaaaaaa", "", ""),
                WordModel("4", "a", "aaaaaaa", "", ""),
                WordModel("5", "a", "aaaaaaa", "", ""),
                WordModel("6", "a", "aaaaaaa", "", ""),
                WordModel("7", "a", "aaaaaaa", "", ""),
                WordModel("8", "a", "aaaaaaa", "", ""),
                WordModel("9", "a", "aaaaaaa", "", ""),
                WordModel("10", "a", "aaaaaaa", "", ""),
                WordModel("11", "b", "bbbbbbb", "", ""),
                WordModel("12", "b", "bbbbbbb", "", ""),
                WordModel("13", "b", "bbbbbbb", "", ""),
                WordModel("14", "b", "bbbbbbb", "", ""),
                WordModel("15", "b", "bbbbbbb", "", ""),
                WordModel("16", "b", "bbbbbbb", "", ""),
                WordModel("17", "b", "bbbbbbb", "", ""),
                WordModel("18", "b", "bbbbbbb", "", ""),
                WordModel("19", "b", "bbbbbbb", "", ""),
                WordModel("20", "c", "ccccccc", "", ""),
                WordModel("21", "c", "ccccccc", "", ""),
                WordModel("22", "c", "ccccccc", "", ""),
                WordModel("23", "c", "ccccccc", "", ""),
                WordModel("24", "c", "ccccccc", "", ""),
                WordModel("25", "c", "ccccccc", "", ""),
                WordModel("26", "c", "ccccccc", "", ""),
                WordModel("27", "c", "ccccccc", "", ""),
                WordModel("28", "c", "ccccccc", "", ""),
                WordModel("29", "c", "ccccccc", "", ""),
                WordModel("30", "d", "ddddddd", "", ""),
                WordModel("31", "d", "ddddddd", "", ""),
                WordModel("32", "d", "ddddddd", "", ""),
                WordModel("33", "d", "ddddddd", "", ""),
                WordModel("34", "d", "ddddddd", "", ""),
                WordModel("35", "d", "ddddddd", "", ""),
                WordModel("36", "d", "ddddddd", "", ""),
                WordModel("37", "d", "ddddddd", "", ""),
                WordModel("38", "d", "ddddddd", "", ""),
                WordModel("39", "d", "ddddddd", "", ""),
                WordModel("40", "d", "ddddddd", "", ""),
                WordModel("41", "d", "ddddddd", "", ""),)
            _wordListState.update {
                getWordList
            }

        } .onSuccess {

        }.onFailure {

        }
    }
    fun selectWord(wordData : WordModel) = viewModelScope.launch{
        _wordSelectState.update { wordData }
        position = getWordList.indexOf(wordData)
        _wordSelectPosition.update { position }
    }
    fun selectWord(getPosition : Int) = viewModelScope.launch{
        _wordSelectState.update { getWordList[getPosition] }
        position = getPosition
        _wordSelectPosition.update { position }
    }
}