package com.github.shmoe6.melody.features.general

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.features.MelodyFeatureOverlayText
import com.github.shmoe6.melody.handlers.OverlayHandler
import gg.essential.elementa.components.UIText
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.time.ZoneId
import java.time.ZonedDateTime

object Clock : MelodyFeatureOverlayText {

    private var displayedTime: String = ZonedDateTime.now(ZoneId.systemDefault()).toString().substring(11, 16)

    override var xPos: Int = MelodyConfig.clockXPos
    override var yPos: Int = MelodyConfig.clockYPos
    override var selectedInEditGui = false
    override var mainUiComponent: UIText = UIText(displayedTime).constrain {
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
            MelodyConfig.clockXPos = xPos
            MelodyConfig.clockYPos = yPos
//            MelodyConfig.markDirty()
//            MelodyConfig.writeData()
        }

        Melody.overlayHandler.overlay.addToScreen(this)
    }

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (!isFeatureEnabled()) return

        val currentTime = ZonedDateTime.now(ZoneId.systemDefault()).toString().substring(11, 16)

        if (currentTime != displayedTime) {
            displayedTime = currentTime
        }

        mainUiComponent.setText(displayedTime)
    }

    override fun isFeatureEnabled(): Boolean {
        return MelodyConfig.clockEnabled
    }
}