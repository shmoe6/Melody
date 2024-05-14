package com.github.shmoe6.melody.mixin;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiContainer.class)
public interface IsMouseOverSlotAccessor {
    @Invoker("isMouseOverSlot")
    boolean isMouseOverSlot_melody(Slot slotIn, int mouseX, int mouseY);
}
