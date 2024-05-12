package com.github.shmoe6.melody.core

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object MelodyConfig: Vigilant(File("./config/Melody.toml")) {

    @Property(
        type = PropertyType.SWITCH,
        name = "Missing Max Enchantments",
        description = "Displays missing max enchantments on an item.",
        category = "General"
    )
    var missingMaxEnchantsEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Silence SkyBlock Notifications",
        description = "Removes notifications that can be irrelevant when grinding. More flexibility coming soon.",
        category = "General"
    )
    var silenceSBNotifsEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Garden Visitor Display",
        description = "Displays what crop the most recently talked to garden visitor has requested.",
        category = "General"
    )
    var gardenVisitorDisplayEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Display Arrow Count",
        description = "Displays arrow count when holding a bow.",
        category = "General"
    )
    var displayArrowCountEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Effects HUD",
        description = "Hides vanilla potion effects HUD in inventory.",
        category = "General"
    )
    var hideEffectsHudEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Worm Cooldown Timer",
        description = "Displays the 30 second cooldown after you a worm spawns.",
        category = "General"
    )
    var wormCooldownTimerEnabled = false

    init {
        initialize()
        preload()
        setCategoryDescription("General", "General features")
    }
}