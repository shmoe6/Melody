package com.github.shmoe6.melody.features

import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.client.Minecraft
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import kotlin.math.floor

object WormCooldownTimer {

    private var runTimer = false
    private var startTime: Long? = null

    @SubscribeEvent
    fun onClientChatReceived(event: ClientChatReceivedEvent) {
        if (!MelodyConfig.wormCooldownTimerEnabled ||
            StringUtils.stripControlCodes(event.message.formattedText) != "You hear the sound of something approaching...")
            return

        runTimer = true
    }

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (!MelodyConfig.wormCooldownTimerEnabled || !runTimer) return

        if (startTime == null) {
            startTime = System.currentTimeMillis()

        } else {
            val timeLeft = 30 - (((System.currentTimeMillis() - startTime!!) / 1000))

            if (timeLeft < 0) {
                startTime = null
                runTimer = false

            } else {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Worm Cooldown: $timeLeft", 550.0F, 450.0F, 0xdcbeb8)
            }
        }
    }
}