package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.gui.OverlayScreen
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

object OverlayHandler {

    val overlay = OverlayScreen()
    var renderOverlay = true

    @SubscribeEvent
    fun onPostRenderGameOverlay(event: RenderGameOverlayEvent.Post) {
        if (!renderOverlay) return

        this.overlay.refreshEnabledElements()
        overlay.window.draw()
    }
}