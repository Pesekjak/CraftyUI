package me.pesekjak.craftyui;

import me.pesekjak.craftyui.events.GuiClickEvent;
import me.pesekjak.craftyui.events.GuiCloseEvent;
import me.pesekjak.craftyui.events.GuiDragEvent;
import me.pesekjak.craftyui.events.GuiOpenEvent;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Represents a custom intractable gui.
 */
@ApiStatus.NonExtendable
public interface AbstractGui {

    @MagicConstant
    short UNKNOWN = -1,
        CONTAINER = 0,
        CRAFTING = 1,
        FUEL = 2,
        OUTSIDE = 3,
        QUICKBAR = 4,
        RESULT = 5;

    /**
     * Checks if provided inventory view is a gui.
     * @param view view
     * @return if the inventory view is a gui
     */
    static boolean isGui(@NotNull InventoryView view) {
        return isGui(view.getTopInventory());
    }

    /**
     * Checks if provided inventory is a gui.
     * @param inventory inventory
     * @return if the inventory is a gui
     */
    static boolean isGui(@NotNull Inventory inventory) {
        return inventory.getHolder() instanceof GuiHolder;
    }

    /**
     * Converts inventory view to a gui.
     * @param view view
     * @return gui from the inventory view
     * @throws UnsupportedOperationException if the inventory view is not a gui
     */
    static @NotNull AbstractGui getGui(@NotNull InventoryView view) {
        return getGui(view.getBottomInventory());
    }

    /**
     * Converts inventory to a gui.
     * @param inventory inventory
     * @return gui from the inventory
     * @throws UnsupportedOperationException if the inventory is not a gui
     */
    static @NotNull AbstractGui getGui(@NotNull Inventory inventory) {
        if(!(inventory.getHolder() instanceof GuiHolder holder))
            throw new UnsupportedOperationException("Provided inventory is not a gui");
        return holder.gui();
    }

    /**
     * Returns currently opened gui of a player.
     * @param player player
     * @return player's opened gui
     */
    static @Nullable AbstractGui getGui(@NotNull Player player) {
        if(isGui(player.getOpenInventory()))
            return getGui(player.getOpenInventory());
        return null;
    }

    /**
     * Returns item in this gui in given slot.
     * @param slot slot id
     * @return item in the slot with given id
     * @throws NullPointerException if the slot with given id doesn't exist in this gui
     */
    @NotNull ItemStack get(int slot);

    /**
     * Sets item to given slot of this gui.
     * @param slot slot id
     * @param item new item
     * @throws NullPointerException if the slot with given id doesn't exist in this gui
     */
    void set(int slot, @Nullable ItemStack item);

    /**
     * Clears given slot of this gui.
     * @param slot slot id
     * @throws NullPointerException if the slot with given id doesn't exist in this gui
     */
    default void clear(int slot) {
        set(slot, new ItemStack(Material.AIR));
    }

    /**
     * Returns slot type of the given slot of this gui.
     * @param slot slot id
     * @return slot type of given slot
     * @throws NullPointerException if the slot with given id doesn't exist in this gui
     * @apiNote returns -1 if the type is unknown
     */
    @MagicConstant short getSlotType(int slot);

    /**
     * Returns all contents of this gui, not including empty slots.
     * @return all not empty slots in this gui
     */
    ItemStack @NotNull [] getContents();

    /**
     * Returns map of slot ids and their content - null if the slot is empty.
     * @return slots
     */
    Map<Integer, @Nullable ItemStack> getSlots();

    /**
     * Checks if the inventory contains given item.
     * @param item item
     * @return if the inventory contains given item
     */
    default boolean contains(@NotNull ItemStack item) {
        return Arrays.asList(getContents()).contains(item);
    }

    /**
     * Applies given function to each slot in this gui.
     * @param function function
     */
    default void apply(BiFunction<AbstractGui, Integer, ItemStack> function) {
        getSlots().keySet().forEach(i -> function.apply(this, i));
    }

    /**
     * Size of the gui - number of available slots.
     * @return size
     */
    int size();

    /**
     * Title used by the gui.
     * @return title
     */
    BaseComponent @NotNull [] getTitle();

    /**
     * Inventory type used by the gui.
     * @return inventory type
     */
    @NotNull InventoryType getType();

    /**
     * Called when the gui is opened.
     * @param event event
     */
    void open(@NotNull GuiOpenEvent event);

    /**
     * Called when the gui is closed.
     * @param event event
     */
    void close(@NotNull GuiCloseEvent event);

    /**
     * Called when player click interacts in the gui.
     * @param event event
     */
    void click(@NotNull GuiClickEvent event);

    /**
     * Called when player drag interacts in the gui.
     * @param event event
     */
    void drag(@NotNull GuiDragEvent event);

    /**
     * Opens the gui for a player.
     * @param player player
     */
    void open(@NotNull Player player);

}
