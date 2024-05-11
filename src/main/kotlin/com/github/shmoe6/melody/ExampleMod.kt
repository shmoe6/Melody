package com.github.shmoe6.melody

import com.github.shmoe6.melody.command.TestCommand
import com.github.shmoe6.melody.handlers.*
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraft.util.StringUtils
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = "melody", useMetadata = true)
class ExampleMod {
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

        // register event handlers
        MinecraftForge.EVENT_BUS.register(GuiOpenHandler)
        MinecraftForge.EVENT_BUS.register(ItemTooltipHandler)
        MinecraftForge.EVENT_BUS.register(PacketReceivedHandler)
        MinecraftForge.EVENT_BUS.register(ClientChatReceivedHandler)
        MinecraftForge.EVENT_BUS.register(TickHandler)

        // register commands
        ClientCommandHandler.instance.registerCommand(TestCommand)
    }

    companion object {
        const val MODID = "MELODY"
        var currentWorld: String? = null
    }
}
