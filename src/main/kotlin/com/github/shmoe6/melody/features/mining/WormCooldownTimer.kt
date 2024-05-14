package com.github.shmoe6.melody.features.mining

import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.client.Minecraft
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase

object WormCooldownTimer {

    private var runTimer = false
    private var startTime: Long? = null

    @SubscribeEvent
    fun onClientChatReceived(event: ClientChatReceivedEvent) {
        if (!MelodyConfig.wormCooldownTimerEnabled ||
            !StringUtils.stripControlCodes(event.message.formattedText).equals("You hear the sound of something approaching...") ||
            !StringUtils.stripControlCodes(event.message.formattedText).equals("[311] α [MVP+] musicallyanna: testd"))
            return

        runTimer = true
    }

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (!MelodyConfig.wormCooldownTimerEnabled || !event.phase.equals(Phase.START) || !runTimer) return

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