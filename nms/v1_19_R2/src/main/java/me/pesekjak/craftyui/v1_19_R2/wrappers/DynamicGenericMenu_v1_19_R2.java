package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.AbstractGui;
import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.Utils_v1_19_R2;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DynamicGenericMenu_v1_19_R2 extends ChestMenu implements DynamicMenu_v1_19_R2 {

    private final AbstractGui impl;

    public DynamicGenericMenu_v1_19_R2(AbstractGui impl, int i, Inventory playerInventory, int rows) {
        super(getMenuType(rows), i, playerInventory, new SimpleContainer(9 * rows), rows);
        this.impl = impl;
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        Utils_v1_19_R2.getHolder((SimpleContainer) getContainer());
        return null;
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        Utils_v1_19_R2.setHolder((SimpleContainer) getContainer(), holder);
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

}
