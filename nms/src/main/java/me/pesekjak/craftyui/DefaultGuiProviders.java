package me.pesekjak.craftyui;

import me.pesekjak.craftyui.v1_19_R2.GuiProvider_v1_19_R2;

/**
 * Util class that registers the default providers for
 * supported server versions.
 */
public final class DefaultGuiProviders {

    /**
     * Registers default providers.
     */
    public static void register() {
        GuiProvider.register("v1_19_R2", new GuiProvider_v1_19_R2());
    }

}
