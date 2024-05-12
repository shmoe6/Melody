package com.github.shmoe6.melody.features

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.event.PacketReceivedEvent
import net.minecraft.client.Minecraft

import net.minecraft.network.play.server.S2DPacketOpenWindow
import net.minecraft.network.play.server.S30PacketWindowItems
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent


object GardenVisitorDisplay {

    // represents whether we want the contents of the gui or not (dont want to handle every time)
    private var awaitS30PacketWindowItems = false

    // active garden offer to display
    private var activeGardenOffer: String? = null

    private val gardenVisitors: HashSet<String> = hashSetOf("Adventurer", "Alchemist", "Andrew", "Anita", "Arthur",
        "Banker Broadjaw", "Bartender", "Beth", "Clerk Seraphine", "Dalbrek", "Duke", "Dusk", "Emissary Carlton",
        "Emissary Ceanna", "Emissary Fraiser", "Emissary Sisko", "Emissary Wilson", "Farmer Jon", "Farmhand",
        "Fear Mongerer", "Felix", "Fisherman", "Fragilis", "Friendly Hiker", "Geonathan Greatforge", "Gimley",
        "Gold Forger", "Grandma Wolf", "Guy", "Gwendolyn", "Hornum", "Hungry Hiker", "Iron Forger", "Jack", "Jacob",
        "Jamie", "Jerry", "Jotraeline Greatforge", "Lazy Miner", "Leo", "Liam", "Librarian", "Lumber Jack", "Lumina",
        "Lynn", "Madame Eleanor Q. Goldsworth III", "Mason", "Maeve", "Odawa", "Old Man Garry", "Oringo", "Pete",
        "Plumber Joe", "Puzzler", "Queen Mismyla", "Rhys", "Royal Resident", "Rusty", "Ryu", "Sargwyn", "Seymour",
        "Shaggy", "Shifty", "Sirius", "Spaceman", "Stella", "Tammy", "Tarwen", "Terry", "Tia the Fairy", "Tom",
        "Trevor", "Vex", "Weaponsmith", "Wizard", "Xalx", "Zog")

    @SubscribeEvent
    fun onPacketReceived(event: PacketReceivedEvent) {
        if (!MelodyConfig.gardenVisitorDisplayEnabled) return

        if (event.packet is S2DPacketOpenWindow) {
            val windowTitle = StringUtils.stripControlCodes(event.packet.windowTitle.formattedText)

            if (Melody.currentWorld == "Garden" && gardenVisitors.contains(windowTitle)) {
                // enable S30 packet handling
                awaitS30PacketWindowItems = true
            }

        } else if (awaitS30PacketWindowItems && event.packet is S30PacketWindowItems) {
            // disable handling of other S30 packets
            awaitS30PacketWindowItems = false
            // index 29 is where the accept offer button is in the garden
            val acceptOfferItem = event.packet.itemStacks[29]
            // line 3 (index 2) of the tooltip has the required items for the garden request
            val tooltip = acceptOfferItem.getTooltip(Minecraft.getMinecraft().thePlayer, false)[2]

            activeGardenOffer = StringUtils.stripControlCodes(tooltip)
        }
    }

    @SubscribeEvent
    fun onRenderTick(event: TickEvent.RenderTickEvent) {
        if (!MelodyConfig.gardenVisitorDisplayEnabled || activeGardenOffer == null) return

        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(activeGardenOffer, 30.0F, 30.0F, 0xdcbeb8)
    }

    @SubscribeEvent
    fun onClientChatReceived(event: ClientChatReceivedEvent) {
        if (!MelodyConfig.gardenVisitorDisplayEnabled || activeGardenOffer == null) return

        // stop displaying offer information after accepted
        if (StringUtils.stripControlCodes(event.message.formattedText).contains("OFFER ACCEPTED")) {
            activeGardenOffer = null
        }
    }
}