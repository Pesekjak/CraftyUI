package me.pesekjak.craftyui.events;

import lombok.Getter;
import me.pesekjak.craftyui.AbstractGui;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Event when the gui is closed by or for the player.
 */
public class GuiCloseEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    @Getter
    private final @NotNull AbstractGui gui;

    public GuiCloseEvent(@NotNull Player who, @NotNull AbstractGui gui) {
        super(who);
        this.gui = gui;
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
