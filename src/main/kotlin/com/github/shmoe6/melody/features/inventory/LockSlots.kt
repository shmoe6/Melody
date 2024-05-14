package com.github.shmoe6.melody.features.inventory

import com.github.shmoe6.melody.core.MelodyConfig
import com.github.shmoe6.melody.mixin.IsMouseOverSlotAccessor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiInventory
import net.minecraft.inventory.Slot
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse

object LockSlots {

    private var allowSlotLockEditing: Boolean = false

    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent) {
        if (!MelodyConfig.lockSlotsEnabled) return

        allowSlotLockEditing = (event.gui is GuiInventory)
    }

    @SubscribeEvent
    fun onKeyInput(event: GuiScreenEvent) {

        //println("Event triggered. L pressed: ${Keyboard.isKeyDown(Keyboard.KEY_L)}, editing enabled: ${this.allowSlotLockEditing}")
        if (!MelodyConfig.lockSlotsEnabled || !allowSlotLockEditing || !Keyboard.isKeyDown(Keyboard.KEY_L)) return

        //println("Condition passed")
        val inventory = Minecraft.getMinecraft().thePlayer.inventory
        val guiWidth = event.gui.width
        val guiHeight = event.gui.height
        val mouseX = Mouse.getEventX() * guiWidth / Minecraft.getMinecraft().displayWidth
        val mouseY = guiHeight - Mouse.getEventY() * guiHeight / Minecraft.getMinecraft().displayHeight

        fun isMouseOverSlot(slotIn: Slot, mouseX: Int, mouseY: Int) = (event.gui as IsMouseOverSlotAccessor).isMouseOverSlot_melody(slotIn, mouseX, mouseY)

        var hoveredItemIndex: Int? = null
        inventory.mainInventory.indices.forEach {
            val slotToCheck = Minecraft.getMinecraft().thePlayer.openContainer.inventorySlots[it] as Slot

            if (isMouseOverSlot(slotToCheck, mouseX, mouseY)) {
                hoveredItemIndex = it
                println("Mouse over slot!")

                val currentLockedSlots = HashSet<String>()
                MelodyConfig.lockedSlots.removePrefix("[").removeSuffix("]").split(",").forEach { slotIndex ->
                    if (currentLockedSlots.contains(slotIndex)) {
                        currentLockedSlots.remove(slotIndex)
                        println("Slot $slotIndex unlocked!")
                    } else {
                        currentLockedSlots.add(slotIndex)
                        println("Slot $slotIndex locked!")
                    }

                    println(currentLockedSlots)
                    MelodyConfig.lockedSlots = currentLockedSlots.toString()
                }
            }
        }

        // println("Event handling finished. Hovered slot: $hoveredItemIndex")
    }
}