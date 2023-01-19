package me.pesekjak.craftyui.v1_19_R2;

import net.minecraft.world.SimpleContainer;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

public class SimpleDynamicContainer_v1_19_R2 extends SimpleContainer {

    private InventoryHolder owner;

    public SimpleDynamicContainer_v1_19_R2(int size) {
        super(size);
    }

    @Override
    public @Nullable InventoryHolder getOwner() {
        return owner;
    }

    public void setOwner(@Nullable InventoryHolder owner) {
        this.owner = owner;
    }

}
