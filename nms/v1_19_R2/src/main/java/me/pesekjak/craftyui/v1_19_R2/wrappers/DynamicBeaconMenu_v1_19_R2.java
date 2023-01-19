package me.pesekjak.craftyui.v1_19_R2.wrappers;

import me.pesekjak.craftyui.v1_19_R2.DynamicMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.Utils_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.BeaconGui_v1_19_R2;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.*;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class DynamicBeaconMenu_v1_19_R2 extends BeaconMenu implements DynamicMenu_v1_19_R2 {

    private final BeaconGui_v1_19_R2 impl;
    public final Container beacon;
    public final ContainerData beaconData;

    private static final Field beaconField, dataField;

    static {
        try {
            String s;
            s = "r"; // obf 'beacon'
            beaconField = BeaconMenu.class.getDeclaredField(s);
            beaconField.setAccessible(true);
            s = "u"; // obf 'beaconData'
            dataField = BeaconMenu.class.getDeclaredField(s);
            dataField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            throw new IllegalStateException();
        }
    }

    public DynamicBeaconMenu_v1_19_R2(BeaconGui_v1_19_R2 impl, int i, Container playerInventory, ContainerLevelAccess containerAccess) {
        super(i, playerInventory, new SimpleContainerData(3), containerAccess);
        this.impl = impl;
        try {
            beacon = (Container) beaconField.get(this);
            beaconData = (ContainerData) dataField.get(this);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException();
        }
    }

    @Override
    public @Nullable InventoryHolder getHolder() {
        return Utils_v1_19_R2.getHolder((SimpleContainer) beacon);
    }

    @Override
    public void setHolder(@Nullable InventoryHolder holder) {
        Utils_v1_19_R2.setHolder((SimpleContainer) beacon, holder);
    }

    @Override
    public MenuType<?> getType() {
        return MenuType.BEACON;
    }

}
