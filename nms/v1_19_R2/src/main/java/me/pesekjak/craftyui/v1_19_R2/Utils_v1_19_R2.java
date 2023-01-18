package me.pesekjak.craftyui.v1_19_R2;

import me.pesekjak.craftyui.Gui;
import me.pesekjak.craftyui.events.GuiOpenEvent;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public final class Utils_v1_19_R2 {

    private Utils_v1_19_R2() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull BlockPos toBlockPos(@NotNull Location location) {
        return new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static @NotNull Component toComponent(BaseComponent @NotNull [] components) {
        return CraftChatMessage.fromJSON(ComponentSerializer.toString(components));
    }

    private static final Field holderField;

    static {
        try {
            holderField = SimpleContainer.class.getDeclaredField("bukkitOwner");
            holderField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            throw new IllegalStateException();
        }
    }

    public static void setHolder(@NotNull SimpleContainer container, @Nullable InventoryHolder holder) {
        try {
            holderField.set(container, holder);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static @Nullable InventoryHolder getHolder(@NotNull SimpleContainer container) {
        try {
            return (InventoryHolder) holderField.get(container);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void openGui(@NotNull Player player, @NotNull Gui wrapper, @NotNull InventoryView view) {
        PacketListeners_v1_19_R2.inject(player);
        GuiOpenEvent openEvent = new GuiOpenEvent(player, wrapper);
        Bukkit.getPluginManager().callEvent(openEvent);
        if(openEvent.isCancelled()) return;
        wrapper.onOpen(openEvent);
        if(!openEvent.isCancelled())
            player.openInventory(view);
    }

}
