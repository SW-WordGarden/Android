package com.sw.wordgarden.presentation.shared

data class IsLoadingUiState(
    val isLoading: Boolean = false
) {
    companion object {
        fun init() = IsLoadingUiState(
            isLoading = false
        )
    }
}