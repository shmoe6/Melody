package com.github.shmoe6.melody

import com.github.shmoe6.melody.command.MelodyCommand
import com.github.shmoe6.melody.command.SimulateCommand
import com.github.shmoe6.melody.command.TestCommand
import com.github.shmoe6.melody.features.*
import com.github.shmoe6.melody.handlers.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.init.Blocks
import net.minecraft.util.StringUtils
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = "melody", useMetadata = true)
class Melody {
    fun String.stripControlCodes(): String = StringUtils.stripControlCodes(this)

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        try {
            val resource: net.minecraft.client.resources.IResource = Minecraft.getMinecraft().getResourceManager()
                .getResource(net.minecraft.util.ResourceLocation("test:test.txt"))
            org.apache.commons.io.IOUtils.copy(resource.getInputStream(), java.lang.System.out)
        } catch (e: java.io.IOException) {
            throw java.lang.RuntimeException(e)
        }

        println("Dirt: ${Blocks.dirt.unlocalizedName}")

        // register feature-specific event handlers
        MinecraftForge.EVENT_BUS.register(DisplayArrowCount)
        MinecraftForge.EVENT_BUS.register(GardenVisitorDisplay)
        MinecraftForge.EVENT_BUS.register(HideEffectsHud)
        MinecraftForge.EVENT_BUS.register(MissingMaxEnchantments)
        MinecraftForge.EVENT_BUS.register(SilenceSkyBlockNotifications)
        MinecraftForge.EVENT_BUS.register(WormCooldownTimer)

        // register other event handlers
        MinecraftForge.EVENT_BUS.register(TickHandler)

        // register commands
        ClientCommandHandler.instance.registerCommand(MelodyCommand)
        ClientCommandHandler.instance.registerCommand(SimulateCommand)
        ClientCommandHandler.instance.registerCommand(TestCommand)
    }

    companion object {
        const val MODID = "MELODY"
        var currentWorld: String? = null
        var currentGui: GuiScreen? = null
    }
}
