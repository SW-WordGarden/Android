package com.sw.wordgarden.presentation.ui.word

import androidx.lifecycle.viewmodel.compose.viewModel
import com.sw.wordgarden.domain.usecase.GetWeeklyWordListUseCase
import com.sw.wordgarden.presentation.model.WordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val getWeeklyWordListUseCase: GetWeeklyWordListUseCase,
){
    private val _wordListState = MutableStateFlow<List<WordModel>>(emptyList())
    var wordListState : StateFlow<List<WordModel>> = _wordListState.asStateFlow()

    private val _wordSelectState = MutableStateFlow<Int>(-1)
    var wordSelectState : StateFlow<Int> = _wordSelectState.asStateFlow()

    init {

    }
//    fun getWordList() = viewModelScope.launch
}