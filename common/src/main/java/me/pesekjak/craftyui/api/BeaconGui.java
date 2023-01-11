package me.pesekjak.craftyui.api;

import lombok.experimental.Delegate;
import me.pesekjak.craftyui.CraftUI;
import me.pesekjak.craftyui.GuiProvider;
import me.pesekjak.craftyui.guis.IBeaconGui;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @apiNote All methods must be invoked by overriders
 */
public class BeaconGui implements IBeaconGui {

    @Delegate
    private final IBeaconGui delegatingGui;

    public BeaconGui(@NotNull Player player, BaseComponent @NotNull [] title) {
        GuiProvider provider = CraftUI.getServerProvider();
        if(provider == null)
            throw new NullPointerException("No provider has been found for your server version");
        delegatingGui = provider.provideBeacon(this, player, title);
    }

}
