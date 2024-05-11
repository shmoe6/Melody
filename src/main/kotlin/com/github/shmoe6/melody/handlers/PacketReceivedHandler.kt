package com.github.shmoe6.melody.handlers

import com.github.shmoe6.melody.ExampleMod
import com.github.shmoe6.melody.event.PacketReceivedEvent
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.network.play.server.S2DPacketOpenWindow
import net.minecraft.network.play.server.S30PacketWindowItems
import net.minecraft.util.StringUtils
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.opengl.GLContext


object PacketReceivedHandler {

    // represents whether we want the contents of the gui or not (dont want to handle every time)
    private var awaitS30PacketWindowItems = false

    @SubscribeEvent
    fun onPacketReceived(event: PacketReceivedEvent) {
        //println("packet received") // TODO: remove debug print

        if (event.packet is S2DPacketOpenWindow) {
            val windowTitle = StringUtils.stripControlCodes(event.packet.windowTitle.formattedText)
            println(windowTitle)

            if (ExampleMod.currentWorld == "Garden" && windowTitle != "SkyBlock Menu") {
                awaitS30PacketWindowItems = true
            }
        }

        else if (awaitS30PacketWindowItems && event.packet is S30PacketWindowItems) {
            awaitS30PacketWindowItems = false
            // index 29 is where the accept offer button is in the garden
            val acceptOfferItem = event.packet.itemStacks[29]
            // line 3 (index 2) of the tooltip has the required items for the garden request
            val tooltip = acceptOfferItem.getTooltip(Minecraft.getMinecraft().thePlayer, false)[2]

            RenderTickHandler.activeGardenOffer = StringUtils.stripControlCodes(tooltip)
        }
    }
}