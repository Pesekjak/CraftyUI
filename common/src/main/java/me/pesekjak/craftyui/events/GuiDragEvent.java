package me.pesekjak.craftyui.events;

import lombok.Getter;
import lombok.Setter;
import me.pesekjak.craftyui.AbstractGui;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.DragType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class GuiDragEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final @NotNull AbstractGui gui;
    private final @NotNull DragType drag;
    /**
     * Ids of changed slots in the gui mapped to their new content.
     */
    private final @NotNull Map<Integer, @NotNull ItemStack> guiChanges;
    /**
     * Ids of changed slots in player's inventory mapped to their new content.
     */
    private final @NotNull Map<Integer, @NotNull ItemStack> playerChanges;

    @Setter
    private boolean cancelled = false;

    public GuiDragEvent(@NotNull Player who,
                        @NotNull AbstractGui gui,
                        @NotNull DragType drag,
                        Map<Integer, ItemStack> guiChanges,
                        Map<Integer, ItemStack> playerChanges) {
        super(who);
        this.gui = gui;
        this.drag = drag;
        this.guiChanges = new TreeMap<>();
        this.guiChanges.putAll(guiChanges);
        this.playerChanges = new TreeMap<>();
        this.playerChanges.putAll(playerChanges);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns handler list for the event.
     *
     * @return handler list
     */
    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

}