package com.github.shmoe6.melody.event

import net.minecraft.network.Packet
import net.minecraft.network.play.server.S2DPacketOpenWindow

class PacketReceivedEvent(packetIn: Packet<*>): MelodyEventBase() {

    val packet = packetIn

    init {
        println("PacketReceivedEventCreated")
    }
}