package com.github.shmoe6.melody.features

import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

object SilenceSkyBlockNotifications {

    private val sbNotifications: HashSet<String> = hashSetOf("Mining Speed Boost is now available!",
        "New day! Your Sky Mall buff changed!", "New buff: Gain +15% more Powder while mining.",
        "New buff: Gain +50 ☘ Mining Fortune.", "[NPC] Jacob: My contest has started!",
        "You can disable this messaging by toggling Sky Mall in your /hotm!", "Welcome to Hypixel SkyBlock!",
        "You used your Mining Speed Boost Pickaxe Ability!", "Your Mining Speed Boost has expired!")

    @SubscribeEvent
    fun onClientChatReceived(event: ClientChatReceivedEvent) {
        if (!MelodyConfig.silenceSBNotifsEnabled) return

        if (sbNotifications.contains(StringUtils.stripControlCodes(event.message.formattedText))) {
            event.isCanceled = true
        }
    }
}