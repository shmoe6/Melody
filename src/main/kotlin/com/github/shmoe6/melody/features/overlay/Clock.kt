package com.github.shmoe6.melody.features.overlay

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.features.MelodyFeatureRenderable
import gg.essential.elementa.components.UIText
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.time.ZoneId
import java.time.ZonedDateTime

object Clock : MelodyFeatureRenderable {

    private var displayedTime: String = ZonedDateTime.now(ZoneId.systemDefault()).toString().substring(11, 16)

    override var xPos: Int = MelodyConfig.clockXPos
    override var yPos: Int = MelodyConfig.clockYPos
    override var mainUiComponent: UIText = UIText(displayedTime).constrain {
        x = xPos.pixels
        y = yPos.pixels
    }

    init {
        Melody.overlayHandler.overlay.addToScreen(this)
    }

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (isFeatureEnabled()) return

        val currentTime = ZonedDateTime.now(ZoneId.systemDefault()).toString().substring(11, 16)

        if (currentTime != displayedTime) {
            this.displayedTime = currentTime
        }

        (this.mainUiComponent as UIText).setText(this.displayedTime)
    }

    override fun isFeatureEnabled(): Boolean {
        return MelodyConfig.clockEnabled
    }
}