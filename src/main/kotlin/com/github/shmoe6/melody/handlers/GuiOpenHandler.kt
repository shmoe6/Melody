package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.ExampleMod
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiIngameMenu
import net.minecraft.client.gui.inventory.GuiInventory
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.InventoryBasic
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object GuiOpenHandler {

    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent) {

//        val currentScreen = Minecraft.getMinecraft().currentScreen
//
//        if (ExampleMod.currentWorld == "Garden") {
//            println("Non-inventory GUI opened in Garden!")
//            println(currentScreen)
//        }
    }
}