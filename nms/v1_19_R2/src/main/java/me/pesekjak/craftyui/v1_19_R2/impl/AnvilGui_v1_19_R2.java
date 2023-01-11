package me.pesekjak.craftyui.v1_19_R2.impl;

import me.pesekjak.craftyui.GuiHolder;
import me.pesekjak.craftyui.events.GuiClickEvent;
import me.pesekjak.craftyui.events.GuiCloseEvent;
import me.pesekjak.craftyui.events.GuiDragEvent;
import me.pesekjak.craftyui.events.GuiOpenEvent;
import me.pesekjak.craftyui.guis.IAnvilGui;
import me.pesekjak.craftyui.v1_19_R2.PacketListeners_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.wrappers.DynamicAnvilMenu_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.Utils_v1_19_R2;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class AnvilGui_v1_19_R2 implements IAnvilGui {

    private final @NotNull IAnvilGui wrapper;
    private final @NotNull CraftPlayer player;
    private final BaseComponent @NotNull [] title;

    private final @NotNull DynamicAnvilMenu_v1_19_R2 menu;
    private final @NotNull InventoryView view;

    public AnvilGui_v1_19_R2(@NotNull IAnvilGui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        this.wrapper = wrapper;
        this.player = (CraftPlayer) player;
        if(title == null || title.length == 0)
            this.title = TextComponent.fromLegacyText(getType().getDefaultTitle());
        else
            this.title = title;
        menu = new DynamicAnvilMenu_v1_19_R2(
                this,
                this.player.getHandle().nextContainerCounter(),
                this.player.getHandle().getInventory(),
                ContainerLevelAccess.create(this.player.getHandle().level, Utils_v1_19_R2.toBlockPos(player.getLocation())));
        menu.checkReachable = false;
        menu.setTitle(Utils_v1_19_R2.toComponent(this.title));
        view = menu.getBukkitView();
        menu.setHolder(new GuiHolder(view.getTopInventory(), wrapper));
    }

    @Override
    public @NotNull ItemStack get(int slot) {
        checkSize(slot);
        ItemStack item = view.getTopInventory().getItem(slot);
        return item != null ? item : new ItemStack(Material.AIR);
    }

    @Override
    public void set(int slot, @Nullable ItemStack item) {
        checkSize(slot);
        view.getTopInventory().setItem(slot, item);
    }

    @Override
    public @NotNull InventoryType.SlotType getSlotType(int slot) {
        checkSize(slot);
        return view.getSlotType(slot);
    }

    @Override
    public ItemStack @NotNull [] getContents() {
        return getSlots().values().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getType() != Material.AIR)
                .toArray(ItemStack[]::new);
    }

    @Override
    public Map<Integer, @Nullable ItemStack> getSlots() {
        Map<Integer, ItemStack> map = new TreeMap<>();
        for (int i = 0; i < getSize(); i++)
            map.put(i, get(i));
        return map;
    }

    @Override
    public int getSize() {
        return view.getTopInventory().getSize();
    }

    @Override
    public BaseComponent @NotNull [] getTitle() {
        return title;
    }

    @Override
    public @NotNull InventoryType getType() {
        return view.getTopInventory().getType();
    }

    @Override
    public void onOpen(@NotNull GuiOpenEvent event) {

    }

    @Override
    public void onClose(@NotNull GuiCloseEvent event) {

    }

    @Override
    public void onClick(@NotNull GuiClickEvent event) {

    }

    @Override
    public void onDrag(@NotNull GuiDragEvent event) {

    }

    @Override
    public void open(@NotNull Player player) {
        PacketListeners_v1_19_R2.inject(player);
        GuiOpenEvent openEvent = new GuiOpenEvent(player, this);
        Bukkit.getPluginManager().callEvent(openEvent);
        if(openEvent.isCancelled()) return;
        wrapper.onOpen(openEvent);
        if(!openEvent.isCancelled())
            player.openInventory(view);
    }

    @Override
    public @Nullable Player getOwner() {
        return player;
    }

    @Override
    public @NotNull ItemStack getFirstInput() {
        return get(0);
    }

    @Override
    public void setFirstInput(@Nullable ItemStack item) {
        set(0, item);
    }

    @Override
    public @NotNull ItemStack getSecondInput() {
        return get(1);
    }

    @Override
    public void setSecondInput(@Nullable ItemStack item) {
        set(1, item);
    }

    @Override
    public @NotNull ItemStack getOutput() {
        return get(2);
    }

    @Override
    public void setOutput(@Nullable ItemStack item) {
        set(2, item);
    }

    @Override
    public void setOutputName(@Nullable String name) {
        menu.setItemName(name);
    }

    @Override
    public int getRepairCost() {
        return menu.cost.get();
    }

    @Override
    public void setRepairCost(int repairCost) {
        menu.cost.set(repairCost);
    }

    @Override
    public @NotNull String getTextInput() {
        return menu.itemName;
    }

    @Override
    public void setTextInput(@NotNull String input) {
        ItemStack item = getFirstInput();
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return;
        meta.setDisplayName(input);
        item.setItemMeta(meta);
        setFirstInput(item);
    }

    @Override
    public void onTextInputChange(@NotNull String input) {

    }

    private void checkSize(int check) {
        if(check >= getSize())
            throw new NullPointerException("Slot " + check + " doesn't exist in the gui");
    }

}
