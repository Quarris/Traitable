package quarris.traitable.mod.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import quarris.traitable.mod.ModUtil;

public class PacketHandler {

    private static final String version = "1";
    private static SimpleChannel channel;

    public static void init() {
        channel = NetworkRegistry.newSimpleChannel(
                ModUtil.createRes("channel"),
                () -> version,
                version::equals,
                version::equals
        );

        channel.registerMessage(0, COpenConfigPacket.class, COpenConfigPacket::encode, COpenConfigPacket::decode, COpenConfigPacket::handle);
    }

    public static <Packet> void sendTo(Packet packet, ServerPlayerEntity player) {
        channel.sendTo(packet, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }
}
