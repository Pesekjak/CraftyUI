package me.pesekjak.craftyui.v1_19_R2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import me.pesekjak.craftyui.AbstractGui;
import me.pesekjak.craftyui.CraftUI;
import me.pesekjak.craftyui.guis.IAnvilGui;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.BiConsumer;

public class PacketListeners_v1_19_R2 {

    public static void inject(@NotNull Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().connection.connection.channel;
        if(channel.pipeline().get("craftui") != null) return;
        channel.pipeline().addBefore("packet_handler", "craftui", new PacketHandler(player));
    }

    @RequiredArgsConstructor
    private static class PacketHandler extends ChannelDuplexHandler {

        private final Player player;
        private final Map<Class<? extends Packet<?>>, BiConsumer<Player, Packet<?>>> handlers = Map.ofEntries(
                    Map.entry(ServerboundRenameItemPacket.class, (player, packet) -> {
                        AbstractGui gui = CraftUI.getGui(player);
                        if(gui instanceof IAnvilGui anvil)
                            anvil.onTextInputChange(((ServerboundRenameItemPacket) packet).getName());
                    })
                );

        @Override
        public void channelRead(@NotNull ChannelHandlerContext context, @NotNull Object packet) {
            BiConsumer<Player, Packet<?>> consumer = handlers.get(packet.getClass());
            if(consumer != null)
                consumer.accept(player, (Packet<?>) packet);
            channelRead0(context, packet);
        }

        private void channelRead0(@NotNull ChannelHandlerContext context, @NotNull Object packet) {
            try {
                super.channelRead(context, packet);
            } catch (Exception ignored) {

            }
        }

    }

}
