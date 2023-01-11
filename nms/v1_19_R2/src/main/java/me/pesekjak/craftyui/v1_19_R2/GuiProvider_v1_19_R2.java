package me.pesekjak.craftyui.v1_19_R2;

import me.pesekjak.craftyui.GuiProvider;
import me.pesekjak.craftyui.guis.*;
import me.pesekjak.craftyui.v1_19_R2.impl.AnvilGui_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.BeaconGui_v1_19_R2;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiProvider_v1_19_R2 implements GuiProvider {

    @Override
    public @NotNull IAnvilGui provideAnvil(@NotNull IAnvilGui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        if(player == null) throw new NullPointerException();
        return new AnvilGui_v1_19_R2(wrapper, player, title);
    }

    @Override
    public @NotNull IBeaconGui provideBeacon(@NotNull IBeaconGui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        if(player == null) throw new NullPointerException();
        return new BeaconGui_v1_19_R2(wrapper, player, title);
    }

    @Override
    public @NotNull IGeneric9x1Gui provideGeneric9x1(@NotNull IGeneric9x1Gui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        return null;
    }

    @Override
    public @NotNull IGeneric9x2Gui provideGeneric9x2(@NotNull IGeneric9x2Gui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        return null;
    }

    @Override
    public @NotNull IGeneric9x3Gui provideGeneric9x3(@NotNull IGeneric9x3Gui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        return null;
    }

    @Override
    public @NotNull IGeneric9x4Gui provideGeneric9x4(@NotNull IGeneric9x4Gui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        return null;
    }

    @Override
    public @NotNull IGeneric9x5Gui provideGeneric9x5(@NotNull IGeneric9x5Gui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        return null;
    }

    @Override
    public @NotNull IGeneric9x6Gui provideGeneric9x6(@NotNull IGeneric9x6Gui wrapper, @Nullable Player player, BaseComponent @Nullable [] title) {
        return null;
    }

}
