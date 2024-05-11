package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.event.PacketReceivedEvent
import net.minecraft.network.play.server.S2DPacketOpenWindow
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object PacketReceivedHandler {
    @SubscribeEvent
    fun onPacketReceived(event: PacketReceivedEvent) {
        println("packet received") // TODO: remove debug print
        if (event.packet is S2DPacketOpenWindow) {
            println(event.packet.windowTitle)
        }
    }
}