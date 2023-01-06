package me.pesekjak.craftyui.guis;

import me.pesekjak.craftyui.AbstractGui;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface SingleInputGui extends AbstractGui {

    /**
     * Returns item in this gui in the input slot.
     * @return item in input slot
     */
    @NotNull ItemStack getInputSlot();

}
