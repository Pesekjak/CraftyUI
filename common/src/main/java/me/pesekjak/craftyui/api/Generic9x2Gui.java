package me.pesekjak.craftyui.api;

import lombok.experimental.Delegate;
import me.pesekjak.craftyui.CraftUI;
import me.pesekjak.craftyui.GuiProvider;
import me.pesekjak.craftyui.guis.IGeneric9x2Gui;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @apiNote All methods must be invoked by overriders
 */
public class Generic9x2Gui implements IGeneric9x2Gui {

    @Delegate
    private final IGeneric9x2Gui delegatingGui;

    public Generic9x2Gui(@NotNull Player player, BaseComponent @NotNull [] title) {
        GuiProvider provider = CraftUI.getServerProvider();
        if(provider == null)
            throw new NullPointerException("No provider has been found for your server version");
        delegatingGui = provider.provideGeneric9x2(this, player, title);
    }

}
