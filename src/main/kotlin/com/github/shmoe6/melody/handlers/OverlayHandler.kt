package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.gui.OverlayScreen
import gg.essential.universal.UScreen
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object OverlayHandler {

    val overlay = OverlayScreen()
    var renderOverlay = true
    var queueEditScreen = false
    var editMode = false

    @SubscribeEvent
    fun onPostRenderGameOverlay(event: RenderGameOverlayEvent.Post) {
        if (!renderOverlay) return

        this.overlay.refreshEnabledElements()

        if (queueEditScreen) {
            UScreen.displayScreen(this.overlay)
            this.editMode = true
            queueEditScreen = false
        } else if (!editMode){
            this.overlay.window.draw()
        }
    }
}