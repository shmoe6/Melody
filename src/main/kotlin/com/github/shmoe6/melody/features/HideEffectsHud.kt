package com.github.shmoe6.melody.features

import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.client.renderer.InventoryEffectRenderer
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object HideEffectsHud {

    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent){
        // TODO implement mixin to hide potion effect hud in invenotry
    }
}