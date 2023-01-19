package me.pesekjak.craftyui;

import me.pesekjak.craftyui.guis.*;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Version based provider for guis.
 */
public interface GuiProvider {

    /**
     * Registers new provider under given identifier.
     * @param identifier identifier
     * @param provider provider
     */
    static void register(@NotNull String identifier, @NotNull GuiProvider provider) {
        CraftUI.providers.put(identifier, provider);
    }

    /**
     * Removes registered provider with given identifier.
     * @param identifier identifier
     */
    static void remove(@NotNull String identifier) {
        CraftUI.providers.remove(identifier);
    }

    /**
     * Returns registered provider with given identifier.
     * @param identifier identifier
     * @return provider
     */
    static @Nullable GuiProvider get(@NotNull String identifier) {
        return CraftUI.providers.get(identifier);
    }

    /**
     * Returns gui provider for given server if there is any.
     * @param server server
     * @return provider
     */
    static @Nullable GuiProvider get(@NotNull Server server) {
        return get(CraftUI.getVersion(server));
    }

    @NotNull IAnvilGui provideAnvil(@NotNull IAnvilGui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IBeaconGui provideBeacon(@NotNull IBeaconGui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric3x3Gui provideGeneric3x3(@NotNull IGeneric3x3Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric9x1Gui provideGeneric9x1(@NotNull IGeneric9x1Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric9x2Gui provideGeneric9x2(@NotNull IGeneric9x2Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric9x3Gui provideGeneric9x3(@NotNull IGeneric9x3Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric9x4Gui provideGeneric9x4(@NotNull IGeneric9x4Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric9x5Gui provideGeneric9x5(@NotNull IGeneric9x5Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IGeneric9x6Gui provideGeneric9x6(@NotNull IGeneric9x6Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

    @NotNull IHopperGui provideHopper(@NotNull IHopperGui wrapper, @NotNull Player player, BaseComponent @Nullable [] title);

}
