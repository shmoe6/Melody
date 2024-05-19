@file:SuppressWarnings("unused")
package com.github.shmoe6.melody.core

import com.github.shmoe6.melody.handlers.OverlayHandler
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object MelodyConfig: Vigilant(File("./config/Melody.toml")) {

    @Property(
        type = PropertyType.BUTTON,
        name = "Edit GUI",
        description = "Allows moving around of all active gui elements",
        category = "General"
    )
    fun editGuiButton() {
        OverlayHandler.queueEditScreen = true
    }

    @Property(
        type = PropertyType.SWITCH,
        name = "Missing Max Enchantments",
        description = "Displays missing max enchantments on an item.",
        category = "Inventory"
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
        category = "Farming"
    )
    var gardenVisitorDisplayEnabled = false

    @Property(
        type = PropertyType.NUMBER,
        name = "Visitor Display xPos",
        description = "x coordinate to render the feature at",
        category = "Farming",
        hidden = true
    )
    var visitorDisplayXPos = 50

    @Property(
        type = PropertyType.NUMBER,
        name = "Visitor Display yPos",
        description = "y coordinate to render the feature at",
        category = "Farming",
        hidden = true
    )
    var visitorDisplayYPos = 50

    @Property(
        type = PropertyType.SWITCH,
        name = "Display Arrow Count",
        description = "Displays arrow count when holding a bow.",
        category = "Combat"
    )
    var displayArrowCountEnabled = false

    @Property(
        type = PropertyType.NUMBER,
        name = "Arrow Count xPos",
        description = "x coordinate to render the feature at",
        category = "Combat",
        hidden = true
    )
    var arrowCountXPos = 50

    @Property(
        type = PropertyType.NUMBER,
        name = "Arrow Count yPos",
        description = "y coordinate to render the feature at",
        category = "Combat",
        hidden = true
    )
    var arrowCountYPos = 50

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Effects HUD",
        description = "Hides vanilla potion effects HUD in inventory.",
        category = "Improvements"
    )
    var hideEffectsHudEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Worm Cooldown Timer",
        description = "Displays the 30 second cooldown after you a worm spawns.",
        category = "Mining"
    )
    var wormCooldownTimerEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Lock Slots",
        description = "Enables locking slots. Press L to toggle locking on a slot.",
        category = "Inventory",
    )
    var lockSlotsEnabled = false

    @Property(
        type = PropertyType.TEXT,
        name = "Locked Slots",
        description = "List of locked slots represented by their int index in the mainInventory.",
        category = "Inventory",
        hidden = true
    )
    var lockedSlots = ""

    @Property(
        type = PropertyType.SWITCH,
        name = "Clock",
        description = "Displays the current time.",
        category = "General"
    )
    var clockEnabled = false

    @Property(
        type = PropertyType.NUMBER,
        name = "Clock xPos",
        description = "x coordinate to render the feature at",
        category = "General",
        hidden = true
    )
    var clockXPos = 50

    @Property(
        type = PropertyType.NUMBER,
        name = "Clock yPos",
        description = "y coordinate to render the feature at",
        category = "General",
        hidden = true
    )
    var clockYPos = 50

    @Property(
        type = PropertyType.SWITCH,
        name = "Dungeon Death Notifier",
        description = "Plays a sound when someone dies in a dungeon.",
        category = "Dungeons"
    )
    var dungeonDeathNotifierEnabled = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Sprint",
        description = "Enables toggle sprint",
        category = "Improvements"
    )
    var toggleSprintEnabled = false

    @Property(
        type = PropertyType.NUMBER,
        name = "Toggle Sprint xPos",
        description = "x coordinate to render the feature at",
        category = "Improvements",
        hidden = true
    )
    var toggleSprintXPos = 70

    @Property(
        type = PropertyType.NUMBER,
        name = "Toggle Sprint yPos",
        description = "y coordinate to render the feature at",
        category = "Improvements",
        hidden = true
    )
    var toggleSprintYPos = 70


    init {
        initialize()
        preload()
        setCategoryDescription("General", "General features")
        setCategoryDescription("Combat", "Combat features")
        setCategoryDescription("Dungeons", "Dungeons features")
        setCategoryDescription("Mining", "Mining features")
        setCategoryDescription("Farming", "Farming features")
        setCategoryDescription("Inventory", "Inventory features")
        setCategoryDescription("Improvements", "QoL improvements upon vanilla/base SkyBlock features")
    }
}