package com.github.shmoe6.melody.features

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIText

interface MelodyFeatureRenderable : MelodyFeature {

    var xPos: Int
    var yPos: Int
    var mainUiComponent: UIText
}