package me.pesekjak.craftyui;

import me.pesekjak.craftyui.events.GuiClickEvent;
import me.pesekjak.craftyui.events.GuiCloseEvent;
import me.pesekjak.craftyui.events.GuiDragEvent;
import me.pesekjak.craftyui.events.GuiOpenEvent;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Represents a custom intractable gui.
 */
@ApiStatus.NonExtendable
public interface AbstractGui {

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
    @NotNull InventoryType.SlotType getSlotType(int slot);

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
    int getSize();

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
    void onOpen(@NotNull GuiOpenEvent event);

    /**
     * Called when the gui is closed.
     * @param event event
     */
    void onClose(@NotNull GuiCloseEvent event);

    /**
     * Called when player click interacts in the gui.
     * @param event event
     */
    void onClick(@NotNull GuiClickEvent event);

    /**
     * Called when player drag interacts in the gui.
     * @param event event
     */
    void onDrag(@NotNull GuiDragEvent event);

    /**
     * Opens the gui for a player.
     * @param player player
     * @apiNote has to call {@link GuiOpenEvent} with plugin manager
     * and {@link AbstractGui#onOpen(GuiOpenEvent)} internally because
     * listener for open inventory event isn't and can't be implemented
     */
    void open(@NotNull Player player);

    /**
     * The player that was used to create the gui, can be null.
     * @return owner
     */
    @Nullable Player getOwner();

    /**
     * Returns all current viewers of this gui.
     * @return viewers
     */
    default @NotNull @Unmodifiable Set<Player> getViewers() {
        return Bukkit.getOnlinePlayers().stream()
                .filter(player -> equals(CraftUI.getGui(player)))
                .collect(Collectors.toSet());
    }

    /**
     * Returns amount of current viewers.
     * @return amount of viewers
     */
    default int getViewerCount() {
        return getViewers().size();
    }

}
