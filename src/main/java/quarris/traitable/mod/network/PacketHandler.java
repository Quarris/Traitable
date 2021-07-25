package quarris.traitable.mod.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;
import quarris.traitable.mod.ModRef;

public class PacketHandler {

    private static final String version = "1";
    private static SimpleChannel channel;

    public static void init() {
        channel = NetworkRegistry.newSimpleChannel(
                ModRef.createRes("channel"),
                () -> version,
                version::equals,
                version::equals
        );

        channel.registerMessage(0, COpenConfigPacket.class, COpenConfigPacket::encode, COpenConfigPacket::decode, COpenConfigPacket::handle);
    }

    public static <Packet> void sendTo(ServerPlayer player, Packet packet) {
        channel.sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
}
