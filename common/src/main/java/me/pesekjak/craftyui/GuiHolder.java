package me.pesekjak.craftyui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Custom inventory holder pairing inventory and gui.
 * @param inventory source inventory
 * @param gui gui for the source inventory
 */
@ApiStatus.Internal
public record GuiHolder(@NotNull Inventory inventory,
                        @NotNull AbstractGui gui) implements InventoryHolder {

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

}
