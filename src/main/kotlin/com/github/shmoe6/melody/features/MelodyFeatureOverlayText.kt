package com.github.shmoe6.melody.features

import gg.essential.elementa.components.UIText


interface MelodyFeatureOverlayText : MelodyFeatureRenderable {

    var xPos: Int
    var yPos: Int
    var selectedInEditGui: Boolean
    override val mainUiComponent: UIText
}

