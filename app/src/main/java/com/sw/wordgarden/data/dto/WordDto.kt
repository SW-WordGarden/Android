package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class WordDto (
    @SerializedName("wordId") val id: String?,
    @SerializedName("word") val title: String?,
    @SerializedName("wordInfo") val description: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("category") val category: String?,
)