package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.HopperGui_v1_19_R2;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.Hopper;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class DynamicHopperMenu_v1_19_R2 extends HopperMenu implements DynamicMenu_v1_19_R2 {

    private final HopperGui_v1_19_R2 impl;
    public final HopperContainer hopper;

    private static final Field hopperField;

    static {
        try {
            String s;
            s = "l"; // obf 'hopper'
            hopperField = HopperMenu.class.getDeclaredField(s);
            hopperField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            throw new IllegalStateException();
        }
    }

    public DynamicHopperMenu_v1_19_R2(HopperGui_v1_19_R2 impl, int i, Inventory playerInventory) {
        super(i, playerInventory, new HopperContainer());
        this.impl = impl;
        try {
            hopper = (HopperContainer) hopperField.get(this);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException();
        }
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        return hopper.getOwner();
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        hopper.setOwner(holder);
    }

    @Override
    public MenuType<?> getType() {
        return MenuType.HOPPER;
    }

    /**
     * @see org.bukkit.craftbukkit.v1_19_R2.inventory.CraftInventory#getType
     */
    static class HopperContainer extends SimpleContainer implements Hopper {

        private InventoryHolder owner;

        HopperContainer() {
            super(5);
        }

        @Override
        public double getLevelX() {
            return 0;
        }

        @Override
        public double getLevelY() {
            return 0;
        }

        @Override
        public double getLevelZ() {
            return 0;
        }

        public @Nullable InventoryHolder getOwner() {
            return owner;
        }

        public void setOwner(@Nullable InventoryHolder owner) {
            this.owner = owner;
        }

    }

}
