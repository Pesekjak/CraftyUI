package me.pesekjak.craftyui;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.pesekjak.craftyui.events.GuiClickEvent;
import me.pesekjak.craftyui.events.GuiCloseEvent;
import me.pesekjak.craftyui.events.GuiDragEvent;
import me.pesekjak.craftyui.events.GuiOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

/**
 * Handles craft ui internals.
 */
public final class CraftUI implements Listener {

    private final static WeakHashMap<JavaPlugin, Service> services = new WeakHashMap<>();
    protected final static HashMap<String, GuiProvider> providers = new HashMap<>();

    private CraftUI() {

    }

    /**
     * Initializes craft ui with given plugin.
     * @param plugin plugin
     * @return service
     */
    public static @NotNull CraftUI.Service init(@NotNull JavaPlugin plugin) {
        if(services.containsKey(plugin))
            return services.get(plugin);
        Service service = new Service(new CraftUI(), new WeakReference<>(plugin));
        service.registerListeners();
        services.put(plugin, service);
        return service;
    }

    /**
     * Provides easier and safe manipulation over registered
     * craft ui instance.
     */
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Service {

        private final @NotNull CraftUI instance;
        private final @NotNull WeakReference<JavaPlugin> provider;
        @Getter
        boolean registered = false;

        /**
         * Registers craft ui listeners.
         */
        public void registerListeners() {
            if(registered) return;
            JavaPlugin provider = this.provider.get();
            if(provider == null) return;
            Bukkit.getPluginManager().registerEvents(instance, provider);
        }

        /**
         * Unregisters craft ui listeners.
         */
        public void unregisterListeners() {
            if(!registered) return;
            HandlerList.unregisterAll(instance);
        }

    }

    @EventHandler
    public void onInventoryOpen(@NotNull InventoryOpenEvent event) {
        if(!(event.getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        if(!(event.getPlayer() instanceof Player player))
            return;
        GuiOpenEvent open = new GuiOpenEvent(player, holder.gui());
        Bukkit.getPluginManager().callEvent(open);
        if(!open.isCancelled())
            holder.gui().open(open);
    }

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent event) {
        if(!(event.getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        if(!(event.getPlayer() instanceof Player player))
            return;
        GuiCloseEvent close = new GuiCloseEvent(player, holder.gui());
        Bukkit.getPluginManager().callEvent(close);
        holder.gui().close(close);
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if(!(event.getWhoClicked().getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        if(!(event.getWhoClicked() instanceof Player player))
            return;
        GuiClickEvent click = new GuiClickEvent(
                player,
                holder.gui(),
                event.getAction(),
                event.getClick(),
                event.getInventory().equals(player.getOpenInventory().getTopInventory()),
                event.getHotbarButton() != -1 ? event.getHotbarButton() : null,
                event.getSlot());
        Bukkit.getPluginManager().callEvent(click);
        if(!click.isCancelled())
            holder.gui().click(click);
    }

    @EventHandler
    public void onInventoryDrag(@NotNull InventoryDragEvent event) {
        if(!(event.getWhoClicked().getOpenInventory().getTopInventory().getHolder() instanceof GuiHolder holder))
            return;
        if(!(event.getWhoClicked() instanceof Player player))
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
        if(!drag.isCancelled())
            holder.gui().drag(drag);
    }

}
