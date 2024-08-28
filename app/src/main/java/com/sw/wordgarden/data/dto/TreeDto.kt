package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class TreeDto (
    @SerializedName("plantName") val name:String?,
    @SerializedName("plantNum") val plantNum:Int?,
    @SerializedName("growthValue") val growthValue : Int?,
    @SerializedName("growthStage") val growthStage : Int?,
    @SerializedName("completionDate") val date : Date?,
)