package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.Utils_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.AnvilGui_v1_19_R2;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

public class DynamicAnvilMenu_v1_19_R2 extends AnvilMenu implements DynamicMenu_v1_19_R2 {

    private final AnvilGui_v1_19_R2 impl;

    public DynamicAnvilMenu_v1_19_R2(AnvilGui_v1_19_R2 impl, int i, Inventory playerInventory, ContainerLevelAccess containerAccess) {
        super(i, playerInventory, containerAccess);
        this.impl = impl;
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        return Utils_v1_19_R2.getHolder((SimpleContainer) inputSlots);
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        Utils_v1_19_R2.setHolder((SimpleContainer) inputSlots, holder);
    }

    @Override
    public MenuType<?> getType() {
        return MenuType.ANVIL;
    }

}
