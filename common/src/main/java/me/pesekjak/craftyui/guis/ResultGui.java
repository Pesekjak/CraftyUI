package me.pesekjak.craftyui.guis;

import me.pesekjak.craftyui.AbstractGui;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ResultGui extends AbstractGui {

    /**
     * Returns item in this gui in the result slot.
     * @return item in result slot
     */
    @NotNull ItemStack getResultSlot();

}
