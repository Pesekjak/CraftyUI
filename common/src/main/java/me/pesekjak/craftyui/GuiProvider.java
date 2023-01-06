package me.pesekjak.craftyui;

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

}
