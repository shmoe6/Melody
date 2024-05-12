package com.github.shmoe6.melody.features

import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
object DisplayArrowCount {

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        val player = Minecraft.getMinecraft().thePlayer
        if (!MelodyConfig.displayArrowCountEnabled ||
            player?.inventory?.mainInventory?.get(8)?.displayName?.contains("Quiver") == false)
            return

        val numArrows = player?.inventory?.mainInventory?.get(8)?.getTooltip(player, false)?.get(5)

        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(numArrows, 550.0F, 450.0F, 0xdcbeb8)
    }
}