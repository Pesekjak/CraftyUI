package me.pesekjak.craftyui.guis;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface MultipleInputGui {

    /**
     * Returns item in this gui in the input slot with given id.
     * @return item in input slot
     * @throws NullPointerException if the input slot with given id doesn't exist in this gui
     */
    @NotNull ItemStack getInputSlot(int slot);

}
