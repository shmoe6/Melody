package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.gui.OverlayScreen
import gg.essential.universal.UScreen
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object OverlayHandler {

    val overlay = OverlayScreen()
    var renderOverlay = true
    var queueEditScreen = false
    var editMode = false
    var editMade = false

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

    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent) {

        if (Minecraft.getMinecraft().currentScreen !is OverlayScreen) {
            this.editMode = false

            if (this.editMade) {
                MelodyConfig.markDirty()
                MelodyConfig.writeData()
                this.editMade = false
            }
        }
    }
}