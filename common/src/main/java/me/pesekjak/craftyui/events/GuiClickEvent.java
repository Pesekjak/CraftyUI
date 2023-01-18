package me.pesekjak.craftyui.events;

import lombok.Getter;
import lombok.Setter;
import me.pesekjak.craftyui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Event when player interacts with the gui.
 */
@Getter
public class GuiClickEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final @NotNull Gui gui;
    private final @NotNull InventoryAction action;
    private final @NotNull ClickType click;
    /**
     * Whether the interaction was in the gui or player's inventory.
     */
    private final boolean inGui;
    private final @Nullable ItemStack cursor;
    private final @Nullable Integer hotbarButton;
    private final int slot;
    private final @NotNull InventoryType.SlotType slotType;

    @Setter
    private boolean cancelled = false;

    public GuiClickEvent(@NotNull Player who,
                         @NotNull Gui gui,
                         @NotNull InventoryAction action,
                         @NotNull ClickType click,
                         boolean inGui,
                         @Nullable Integer hotbarButton,
                         int slot) {
        super(who);
        this.gui = gui;
        this.action = action;
        this.click = click;
        this.inGui = inGui;
        this.cursor = who.getItemOnCursor();
        this.hotbarButton = hotbarButton;
        this.slot = slot;
        this.slotType = inGui ? gui.getSlotType(slot) : InventoryType.SlotType.CONTAINER;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns handler list for the event.
     * @return handler list
     */
    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

}
