package com.github.shmoe6.melody.event

import net.minecraft.network.Packet

class PacketReceivedEvent(packetIn: Packet<*>): MelodyEventBase() {

    val packet = packetIn
}