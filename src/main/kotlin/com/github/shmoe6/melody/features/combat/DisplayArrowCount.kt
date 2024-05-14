package com.github.shmoe6.melody.features.combat

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.features.MelodyFeatureRenderable
import com.github.shmoe6.melody.features.overlay.Clock
import com.github.shmoe6.melody.handlers.OverlayHandler
import gg.essential.elementa.components.UIText
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

object DisplayArrowCount : MelodyFeatureRenderable {

    override var xPos: Int = 550
    override var yPos: Int = 450
    override var mainUiComponent: UIText = UIText().constrain {
        x = xPos.pixels
        y = yPos.pixels
    }

    init {
        this.mainUiComponent.onMouseDrag { mouseX, mouseY, mouseButton ->
            if (OverlayHandler.editMode && isPointInside(mouseX, mouseY)) {
                Clock.xPos = mouseX.toInt()
                Clock.yPos = mouseY.toInt()
                setX(Clock.xPos.pixels)
                setY(Clock.yPos.pixels)
            }
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
        (this.mainUiComponent as UIText).setText("$numArrows")
    }

    private fun clearText() {
        if ((this.mainUiComponent as UIText).getText() != "") {
            (this.mainUiComponent as UIText).setText("")
        }
    }

    override fun isFeatureEnabled(): Boolean {
        return MelodyConfig.displayArrowCountEnabled
    }
}