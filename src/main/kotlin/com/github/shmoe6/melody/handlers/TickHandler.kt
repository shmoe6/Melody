package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.ExampleMod
import com.google.common.collect.ComparisonChain
import com.google.common.collect.Ordering
import net.minecraft.client.Minecraft
import net.minecraft.client.network.NetworkPlayerInfo
import net.minecraft.scoreboard.ScorePlayerTeam
import net.minecraft.util.StringUtils
import net.minecraft.world.WorldSettings
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

object TickHandler {
    val NetworkPlayerInfo.text: String
        get() = displayName?.formattedText ?: ScorePlayerTeam.formatPlayerName(
            playerTeam,
            gameProfile.name
        )

    private val playerInfoOrdering = object : Ordering<NetworkPlayerInfo>() {
        override fun compare(p1: NetworkPlayerInfo?, p2: NetworkPlayerInfo?): Int {
            return when {
                p1 != null && p2 != null -> {
                    ComparisonChain.start().compareTrueFirst(
                        p1.gameType != WorldSettings.GameType.SPECTATOR,
                        p2.gameType != WorldSettings.GameType.SPECTATOR
                    ).compare(
                        p1.playerTeam?.registeredName ?: "",
                        p2.playerTeam?.registeredName ?: ""
                    ).compare(p1.gameProfile.name, p2.gameProfile.name).result()
                }

                p1 == null -> -1
                else -> 0
            }
        }
    }

    fun fetchTabEntries(): List<NetworkPlayerInfo> = Minecraft.getMinecraft().thePlayer?.let {
        playerInfoOrdering.immutableSortedCopy(
            Minecraft.getMinecraft().thePlayer.sendQueue.playerInfoMap
        )
    } ?: emptyList()

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {

        if (event.phase != TickEvent.Phase.START) return

        val tabEntries = ArrayList<String>()
        fetchTabEntries().forEach { tabEntries.add(StringUtils.stripControlCodes(it.text)) }
        tabEntries.forEach {
            if (it.contains("Area:")) {
                ExampleMod.currentWorld = it.substring(6)
                //println("CURRENT AREA: ${ExampleMod.currentWorld}")
            }
        }
    }
}