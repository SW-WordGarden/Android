package com.sw.wordgarden.presentation.shared

import com.sw.wordgarden.domain.bus.ItemChangedEventBus
import kotlinx.coroutines.flow.SharedFlow

class ItemChangedEventBusImpl : ItemChangedEventBus {
    override val itemChangedEvent: SharedFlow<Unit>
        get() = TODO("Not yet implemented")

    override fun notifyItemChanged() {
        TODO("Not yet implemented")
    }
}