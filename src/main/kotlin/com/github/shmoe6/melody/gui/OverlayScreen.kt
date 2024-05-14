package com.github.shmoe6.melody.gui

import com.github.shmoe6.melody.features.MelodyFeatureRenderable
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.childOf

class OverlayScreen : WindowScreen(ElementaVersion.V5) {

    // <feature, isHidden>
    private val elements = HashMap<MelodyFeatureRenderable, Boolean>() // TODO: add compatibility for MelodyFeatureRenderable

    fun addToScreen(f : MelodyFeatureRenderable) {
        this.elements[f] = false
        f.mainUiComponent.childOf(this.window)
        refreshEnabledElements()
    }

    fun refreshEnabledElements() {
        this.elements.entries.forEach {
            if (it.key.isFeatureEnabled() && it.value) {
                it.key.mainUiComponent.unhide(true)
                it.setValue(false)

            } else if (!it.key.isFeatureEnabled() && !it.value){
                it.key.mainUiComponent.hide(true)
                it.setValue(true)
            }
        }
    }
}