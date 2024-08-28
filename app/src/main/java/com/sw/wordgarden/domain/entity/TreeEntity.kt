package com.sw.wordgarden.domain.entity

import java.util.Date

data class TreeEntity (
    val name:String?,
    val plantNum:Int?,
    val growthValue : Int?,
    val growthStage : Int?,
    val date : Date?,
)