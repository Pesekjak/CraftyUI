package me.pesekjak.craftyui;

import lombok.Getter;
import me.pesekjak.craftyui.events.GuiClickEvent;
import me.pesekjak.craftyui.events.GuiCloseEvent;
import me.pesekjak.craftyui.events.GuiDragEvent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handles craft ui internals.
 */
public final class CraftUI implements Listener {

    final static HashMap<String, GuiProvider> providers = new HashMap<>();

    @Getter
    static @Nullable GuiProvider serverProvider;

    static {
        registerDefaultProviders();
        serverProvider = GuiProvider.get(Bukkit.getServer());
    }

    private CraftUI() {

    }

    /**
     * Initializes craft ui with given plugin.
     * @param plugin plugin
     */
    public static @NotNull Listener registerListeners(@NotNull JavaPlugin plugin) {
        Listener registered = HandlerList.getRegisteredListeners(plugin).stream()
                .map(RegisteredListener::getListener)
                .filter(listener -> listener instanceof CraftUI)
                .findAny().orElse(null);
        if(registered != null) return registered;
        CraftUI instance = new CraftUI();
        Bukkit.getPluginManager().registerEvents(instance, plugin);
        return instance;
    }

    /**
     * Checks if provided inventory view is a gui.
     * @param view view
     * @return if the inventory view is a gui
     */
    public static boolean isGui(@NotNull InventoryView view) {
        return isGui(view.getTopInventory());
    }

    /**
     * Checks if provided inventory is a gui.
     * @param inventory inventory
     * @return if the inventory is a gui
     */
    public static boolean isGui(@NotNull Inventory inventory) {
        return inventory.getHolder() instanceof GuiHolder;
    }

    /**
     * Converts inventory view to a gui.
     * @param view view
     * @return gui from the inventory view
     * @throws UnsupportedOperationException if the inventory view is not a gui
     */
    public static @NotNull Gui getGui(@NotNull InventoryView view) {
        return getGui(view.getTopInventory());
    }

    /**
     * Converts inventory to a gui.
     * @param inventory inventory
     * @return gui from the inventory
     * @throws UnsupportedOperationException if the inventory is not a gui
     */
    public static @NotNull Gui getGui(@NotNull Inventory inventory) {
        if(!(inventory.getHolder() instanceof GuiHolder holder))
            throw new UnsupportedOperationException("Provided inventory is not a gui");
        return holder.gui();
    }

    /**
     * Returns currently opened gui of a player.
     * @param player player
     * @return player's opened gui
     */
    public static @Nullable Gui getGui(@NotNull Player player) {
        if(isGui(player.getOpenInventory()))
            return getGui(player.getOpenInventory());
        return null;
    }

    /**
     * Returns version of the provided server.
     * @return server's version string
     */
    static @NotNull String getVersion(@NotNull Server server) {
        String pack = server.getClass().getPackage().getName();
        return pack.substring(pack.lastIndexOf('.') + 1);
    }

    /**
     * Registers default providers for supported versions.
     */
    static void registerDefaultProviders() {
        try {
            Class<?> registerUtil = Class.forName("me.pesekjak.craftyui.DefaultGuiProviders");
            registerUtil.getMethod("register").invoke(null);
        } catch (Exception exception) {
            throw new IllegalStateException(exception);
        }
    }

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent event) {
        if(!(event.getPlayer() instanceof Player player))
            return;
        if(!(player.getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        GuiCloseEvent close = new GuiCloseEvent(player, holder.gui());
        Bukkit.getPluginManager().callEvent(close);
        holder.gui().onClose(close);
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if(event.isCancelled()) return;
        if(!(event.getWhoClicked() instanceof Player player))
            return;
        if(!(player.getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        GuiClickEvent click = new GuiClickEvent(
                player,
                holder.gui(),
                event.getAction(),
                event.getClick(),
                event.getSlot() < event.getView().getTopInventory().getSize(),
                event.getHotbarButton() != -1 ? event.getHotbarButton() : null,
                event.getSlot());
        Bukkit.getPluginManager().callEvent(click);
        if(!click.isCancelled()) {
            holder.gui().onClick(click);
            if(click.isCancelled())
                event.setCancelled(true);
        } else
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(@NotNull InventoryDragEvent event) {
        if(event.isCancelled()) return;
        if(!(event.getWhoClicked() instanceof Player player))
            return;
        if(!(player.getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        GuiDragEvent drag = new GuiDragEvent(
                player,
                holder.gui(),
                event.getType(),
                event.getNewItems().entrySet().stream()
                        .filter(entry -> player.getOpenInventory().getTopInventory().equals(player.getOpenInventory().getInventory(entry.getKey())))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new)),
                event.getNewItems().entrySet().stream()
                        .filter(entry -> player.getOpenInventory().getTopInventory().equals(player.getInventory()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new))
        );
        Bukkit.getPluginManager().callEvent(drag);
        if(!drag.isCancelled()) {
            holder.gui().onDrag(drag);
            if(drag.isCancelled())
                event.setCancelled(true);
        } else
            event.setCancelled(true);
    }

}
