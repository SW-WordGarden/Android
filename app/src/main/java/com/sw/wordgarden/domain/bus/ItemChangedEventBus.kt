package com.sw.wordgarden.domain.bus

import kotlinx.coroutines.flow.SharedFlow

interface ItemChangedEventBus {
    val itemChangedEvent: SharedFlow<Unit>
    fun notifyItemChanged()
}