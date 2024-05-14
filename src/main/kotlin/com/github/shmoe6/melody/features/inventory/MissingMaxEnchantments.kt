package com.github.shmoe6.melody.features.inventory

import com.github.shmoe6.melody.core.MelodyConfig
import net.minecraft.util.StringUtils
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object MissingMaxEnchantments {

    private val maxEnchantsSword: HashSet<String> = hashSetOf("Bane of Arthropods VII", "Champion X", "Cleave VI",
        "Critical VII", "Cubism VI", "Divine Gift III", "Dragon Hunter V", "Ender Slayer VII", "Execute VI",
        "Experience V", "Fire Aspect III", "First Strike V", "Giant Killer VII", "Impaling III", "Lethality VI",
        "Looting V", "Luck VI", "Mana Steal III", "Scavenger V", "Sharpness VII", "Smoldering V", "Syphon V",
        "Tabasco III", "Thunderlord VII", "Titan Killer VII", "Vampirism VI", "Venomous VI", "Vicious V")

    private val maxEnchantsBow: HashSet<String> = hashSetOf("Chance V", "Cubism VI", "Divine Gift III", "Dragon Hunter V",
        "Dragon Tracer V", "Flame II", "Impaling III", "Infinite Quiver X", "Overload V", "Piercing I", "Power VII",
        "Punch II", "Smoldering V", "Snipe IV", "Tabasco III", "Vicious V")

    @SubscribeEvent
    fun onTooltip(event: ItemTooltipEvent) {
        if (!MelodyConfig.missingMaxEnchantsEnabled) return

        var itemTypeEnchants = HashSet<String>()

        for (line in event.toolTip.reversed()) {

            val strippedLine = StringUtils.stripControlCodes(line)

            itemTypeEnchants = when {
                strippedLine.contains("SWORD") -> maxEnchantsSword
                strippedLine.contains("BOW") -> maxEnchantsBow
                else -> itemTypeEnchants
            }
        }

        val missingEnchants = HashSet<String>()
        missingEnchants.addAll(itemTypeEnchants)

        for (line in event.toolTip) {
            val lineContents = line.split(Regex("(§[0-9a-z])+"))

            for (content in lineContents) {
                if (itemTypeEnchants.contains(content)) {
                    missingEnchants.remove(content)
                }
            }
        }

        // if not one of the item types listed, dont display anything
        if (itemTypeEnchants.isNotEmpty()) {
            event.toolTip.add("Missing max enchants: $missingEnchants")
        }
    }
}