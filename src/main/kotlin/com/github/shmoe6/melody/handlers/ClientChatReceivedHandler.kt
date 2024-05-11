package com.github.shmoe6.melody.handlers

import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object ClientChatReceivedHandler {
    @SubscribeEvent
    fun onClientChatReceived(event: ClientChatReceivedEvent) {

        if (event.message.formattedText.replace(Regex("(§[1-9a-z])+"), "") == "Mining Speed Boost is now available!") {
            event.isCanceled = true
        }
    }
}