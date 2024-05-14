@file:SuppressWarnings("unused")
package com.github.shmoe6.melody

import com.github.shmoe6.melody.command.MelodyCommand
import com.github.shmoe6.melody.command.SimulateCommand
import com.github.shmoe6.melody.command.TestCommand
import com.github.shmoe6.melody.features.combat.DisplayArrowCount
import com.github.shmoe6.melody.features.farming.GardenVisitorDisplay
import com.github.shmoe6.melody.features.general.SilenceSkyBlockNotifications
import com.github.shmoe6.melody.features.inventory.HideEffectsHud
import com.github.shmoe6.melody.features.inventory.LockSlots
import com.github.shmoe6.melody.features.inventory.MissingMaxEnchantments
import com.github.shmoe6.melody.features.mining.WormCooldownTimer
import com.github.shmoe6.melody.features.overlay.Clock
import com.github.shmoe6.melody.handlers.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.init.Blocks
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = "melody", useMetadata = true)
class Melody {

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        try {
            val resource: net.minecraft.client.resources.IResource = Minecraft.getMinecraft().resourceManager
                .getResource(net.minecraft.util.ResourceLocation("test:test.txt"))
            org.apache.commons.io.IOUtils.copy(resource.inputStream, java.lang.System.out)
        } catch (e: java.io.IOException) {
            throw java.lang.RuntimeException(e)
        }

        println("Dirt: ${Blocks.dirt.unlocalizedName}")

        // register general event handlers
        MinecraftForge.EVENT_BUS.register(overlayHandler)
        MinecraftForge.EVENT_BUS.register(TickHandler)

        // register feature-specific event handlers
        MinecraftForge.EVENT_BUS.register(Clock)
        MinecraftForge.EVENT_BUS.register(DisplayArrowCount)
        MinecraftForge.EVENT_BUS.register(GardenVisitorDisplay)
        MinecraftForge.EVENT_BUS.register(HideEffectsHud)
        MinecraftForge.EVENT_BUS.register(LockSlots)
        MinecraftForge.EVENT_BUS.register(MissingMaxEnchantments)
        MinecraftForge.EVENT_BUS.register(SilenceSkyBlockNotifications)
        MinecraftForge.EVENT_BUS.register(WormCooldownTimer)

        // register commands
        ClientCommandHandler.instance.registerCommand(MelodyCommand)
        ClientCommandHandler.instance.registerCommand(SimulateCommand)
        ClientCommandHandler.instance.registerCommand(TestCommand)

        doneLoading = true
    }

    companion object {
        const val MODID = "MELODY"
        var doneLoading = false
        var currentWorld: String? = null
        var currentGui: GuiScreen? = null
        val overlayHandler = OverlayHandler
    }
}
