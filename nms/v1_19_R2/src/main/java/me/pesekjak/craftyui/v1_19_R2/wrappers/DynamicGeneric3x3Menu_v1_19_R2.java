package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.Generic3x3Gui_v1_19_R2;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class DynamicGeneric3x3Menu_v1_19_R2 extends DispenserMenu implements DynamicMenu_v1_19_R2 {

    private final Generic3x3Gui_v1_19_R2 impl;
    public final DispenserContainer dispenser;

    private static final Field dispenserField;

    static {
        try {
            String s;
            s = "p"; // obf 'dispenser'
            dispenserField = DispenserMenu.class.getDeclaredField(s);
            dispenserField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            throw new IllegalStateException();
        }
    }

    public DynamicGeneric3x3Menu_v1_19_R2(Generic3x3Gui_v1_19_R2 impl, int i, Inventory playerInventory) {
        super(i, playerInventory, new DispenserContainer(null));
        this.impl = impl;
        try {
            dispenser = (DispenserContainer) dispenserField.get(this);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException();
        }
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        return dispenser.getOwner();
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        dispenser.setOwner(holder);
    }

    @Override
    public MenuType<?> getType() {
        return MenuType.GENERIC_3x3;
    }

    /**
     * @see org.bukkit.craftbukkit.v1_19_R2.inventory.CraftInventory#getType
     */
    static class DispenserContainer extends DispenserBlockEntity {

        private InventoryHolder owner;

        DispenserContainer(@Nullable InventoryHolder owner) {
            super(BlockEntityType.DISPENSER, new BlockPos(0, 0, 0), Blocks.DISPENSER.defaultBlockState());
        }

        public @Nullable InventoryHolder getOwner() {
            return owner;
        }

        public void setOwner(@Nullable InventoryHolder owner) {
            this.owner = owner;
        }

    }

}
