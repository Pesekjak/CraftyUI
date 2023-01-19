package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.Gui;
import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.SimpleDynamicContainer_v1_19_R2;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DynamicGenericMenu_v1_19_R2 extends ChestMenu implements DynamicMenu_v1_19_R2 {

    private final Gui impl;
    public final int rows;

    public DynamicGenericMenu_v1_19_R2(Gui impl, int i, Inventory playerInventory, int rows) {
        // simple container functions as chest type automatically, no need for special implementation
        super(getMenuType(rows), i, playerInventory, new SimpleDynamicContainer_v1_19_R2(9 * rows), rows);
        this.impl = impl;
        this.rows = rows;
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        return ((SimpleDynamicContainer_v1_19_R2) getContainer()).getOwner();
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        ((SimpleDynamicContainer_v1_19_R2) getContainer()).setOwner(holder);
    }

    private static @NotNull MenuType<?> getMenuType(int rows) {
        MenuType<?> type = switch (rows) {
            case 1 -> MenuType.GENERIC_9x1;
            case 2 -> MenuType.GENERIC_9x2;
            case 3 -> MenuType.GENERIC_9x3;
            case 4 -> MenuType.GENERIC_9x4;
            case 5 -> MenuType.GENERIC_9x5;
            case 6 -> MenuType.GENERIC_9x6;
            default -> null;
        };
        if(type == null) throw new IndexOutOfBoundsException();
        return type;
    }

    @Override
    public MenuType<?> getType() {
        return getMenuType(rows);
    }

}
