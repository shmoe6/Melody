package com.github.shmoe6.melody.handlers

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.FontRenderer
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent
import org.fusesource.jansi.Ansi

object RenderTickHandler {

    var activeGardenOffer: String? = null
    @SubscribeEvent
    fun onRenderTick(event: RenderTickEvent) {

        if (activeGardenOffer != null) {
            Minecraft.getMinecraft().fontRendererObj.drawString(activeGardenOffer, 30, 30, 3)
        }
    }
}