package me.pesekjak.craftyui.v1_19_R2;

import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

public interface DynamicMenu_v1_19_R2 {

    @Nullable InventoryHolder getHolder();

    void setHolder(@Nullable InventoryHolder holder);

}
