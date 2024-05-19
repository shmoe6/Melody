package com.github.shmoe6.melody.features.combat

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.features.MelodyFeatureOverlayText
import com.github.shmoe6.melody.handlers.OverlayHandler
import gg.essential.elementa.components.UIText
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

object DisplayArrowCount : MelodyFeatureOverlayText {

    override var xPos: Int = MelodyConfig.arrowCountXPos
    override var yPos: Int = MelodyConfig.arrowCountYPos
    override var selectedInEditGui = false
    override var mainUiComponent: UIText = UIText("Current Arrow: null (0)").constrain {
        x = xPos.pixels
        y = yPos.pixels
    }


    init {
        mainUiComponent.onMouseClick { event ->
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
            MelodyConfig.arrowCountXPos = xPos
            MelodyConfig.arrowCountYPos = yPos
        }

        Melody.overlayHandler.overlay.addToScreen(this)
    }

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        val player = Minecraft.getMinecraft().thePlayer

        if (!isFeatureEnabled() || player?.inventory?.mainInventory?.get(8)?.displayName?.contains("Quiver") == false) {
            clearText()
            return
        }

        val numArrows = player?.inventory?.mainInventory?.get(8)?.getTooltip(player, false)?.get(5)
        mainUiComponent.setText("$numArrows")
    }

    private fun clearText() {
        if (mainUiComponent.getText() != "") {
            mainUiComponent.setText("")
        }
    }

    override fun isFeatureEnabled(): Boolean {
        return MelodyConfig.displayArrowCountEnabled
    }
}