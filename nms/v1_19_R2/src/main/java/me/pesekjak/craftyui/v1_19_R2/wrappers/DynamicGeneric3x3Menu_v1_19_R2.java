package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.Gui;
import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.Utils_v1_19_R2;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BeaconMenu;
import net.minecraft.world.inventory.DispenserMenu;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class DynamicGeneric3x3Menu_v1_19_R2 extends DispenserMenu implements DynamicMenu_v1_19_R2 {

    private final Gui impl;
    public final Container dispenser;

    private static final Field dispenserField;

    static {
        try {
            String s;
            s = "p"; // obf 'dispenser'
            dispenserField = BeaconMenu.class.getDeclaredField(s);
            dispenserField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            throw new IllegalStateException();
        }
    }

    public DynamicGeneric3x3Menu_v1_19_R2(Gui impl, int i, Inventory playerInventory) {
        super(i, playerInventory);
        this.impl = impl;
        try {
            dispenser = (Container) dispenserField.get(this);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException();
        }
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        Utils_v1_19_R2.getHolder((SimpleContainer) dispenser);
        return null;
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        Utils_v1_19_R2.setHolder((SimpleContainer) dispenser, holder);
    }

}
