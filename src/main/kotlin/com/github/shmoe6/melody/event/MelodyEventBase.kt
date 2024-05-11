package com.github.shmoe6.melody.event

import net.minecraftforge.fml.common.eventhandler.Cancelable
import net.minecraftforge.fml.common.eventhandler.Event

@Cancelable
abstract class MelodyEventBase: Event() {

    val eventName by lazy {
        this::class.simpleName
    }
}