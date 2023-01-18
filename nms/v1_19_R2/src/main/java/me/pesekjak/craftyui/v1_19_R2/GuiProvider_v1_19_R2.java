package me.pesekjak.craftyui.v1_19_R2;

import me.pesekjak.craftyui.GuiProvider;
import me.pesekjak.craftyui.guis.*;
import me.pesekjak.craftyui.v1_19_R2.impl.AnvilGui_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.BeaconGui_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.Generic3x3Gui_v1_19_R2;
import me.pesekjak.craftyui.v1_19_R2.impl.GenericGui_v1_19_R2;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiProvider_v1_19_R2 implements GuiProvider {

    @Override
    public @NotNull IAnvilGui provideAnvil(@NotNull IAnvilGui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new AnvilGui_v1_19_R2(wrapper, player, title);
    }

    @Override
    public @NotNull IBeaconGui provideBeacon(@NotNull IBeaconGui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new BeaconGui_v1_19_R2(wrapper, player, title);
    }

    @Override
    public @NotNull IGeneric3x3Gui provideGeneric3x3(@NotNull IGeneric3x3Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new Generic3x3Gui_v1_19_R2(wrapper, player, title);
    }

    @Override
    public @NotNull IGeneric9x1Gui provideGeneric9x1(@NotNull IGeneric9x1Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new GenericGui_v1_19_R2(wrapper, player, title, 1);
    }

    @Override
    public @NotNull IGeneric9x2Gui provideGeneric9x2(@NotNull IGeneric9x2Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new GenericGui_v1_19_R2(wrapper, player, title, 2);
    }

    @Override
    public @NotNull IGeneric9x3Gui provideGeneric9x3(@NotNull IGeneric9x3Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new GenericGui_v1_19_R2(wrapper, player, title, 3);
    }

    @Override
    public @NotNull IGeneric9x4Gui provideGeneric9x4(@NotNull IGeneric9x4Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new GenericGui_v1_19_R2(wrapper, player, title, 4);
    }

    @Override
    public @NotNull IGeneric9x5Gui provideGeneric9x5(@NotNull IGeneric9x5Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new GenericGui_v1_19_R2(wrapper, player, title, 5);
    }

    @Override
    public @NotNull IGeneric9x6Gui provideGeneric9x6(@NotNull IGeneric9x6Gui wrapper, @NotNull Player player, BaseComponent @Nullable [] title) {
        return new GenericGui_v1_19_R2(wrapper, player, title, 6);
    }

}
