package com.github.shmoe6.melody.features.dungeons

import com.github.shmoe6.melody.Melody
import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.features.MelodyFeature
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object DeathNotifier : MelodyFeature {

    @SubscribeEvent
    fun onDeath(event: ClientChatReceivedEvent) {
        if (!isFeatureEnabled() || Melody.currentWorld != "The Catacombs") return

        // TODO: play sound, add functionality
    }

    override fun isFeatureEnabled(): Boolean {
        return MelodyConfig.dungeonDeathNotifierEnabled
    }
}