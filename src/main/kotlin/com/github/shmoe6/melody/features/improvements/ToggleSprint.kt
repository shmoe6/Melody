package com.github.shmoe6.melody.features.improvements

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.features.MelodyFeatureOverlayText
import com.github.shmoe6.melody.handlers.OverlayHandler
import gg.essential.elementa.components.UIText
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

object ToggleSprint : MelodyFeatureOverlayText {

    override var xPos: Int = MelodyConfig.toggleSprintXPos
    override var yPos: Int = MelodyConfig.toggleSprintYPos
    override var selectedInEditGui = false

    override var mainUiComponent: UIText = UIText("Toggle Sprint: ${isFeatureEnabled()}").constrain {
        x = xPos.pixels
        y = yPos.pixels
    }

    init {
        this.mainUiComponent.onMouseClick { event ->
            if (OverlayHandler.editMode && isPointInside(event.absoluteX, event.absoluteY)) {
               selectedInEditGui = true
            }
        }.onMouseDrag { mouseX, mouseY, _ ->
            if (OverlayHandler.editMode && selectedInEditGui) {
                xPos = (mouseX + this.getLeft()).toInt()
                yPos = (mouseY + this.getTop()).toInt()
                setX(xPos.pixels)
                setY(yPos.pixels)
                OverlayHandler.editMade = true
            }
        }.onMouseRelease {
            selectedInEditGui = false
            MelodyConfig.toggleSprintXPos = xPos
            MelodyConfig.toggleSprintYPos = yPos
        }

        Melody.overlayHandler.overlay.addToScreen(this)
    }

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (!isFeatureEnabled() || Minecraft.getMinecraft().gameSettings.keyBindSprint.isPressed) return

        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.keyCode, true)

        // TODO: allow toggling of toggle sprint while feature enabled with a vanilla KeyBind
    }

    override fun isFeatureEnabled(): Boolean {
        return MelodyConfig.toggleSprintEnabled
    }
}