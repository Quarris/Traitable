package quarris.traitable.mod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import quarris.traitable.mod.screen.TraitableConfigScreen;

import java.util.function.Supplier;

public class COpenConfigPacket {

    public COpenConfigPacket() {
    }

    public static void encode(COpenConfigPacket packet, FriendlyByteBuf buf) {

    }

    public static COpenConfigPacket decode(FriendlyByteBuf buf) {
        return new COpenConfigPacket();
    }

    public static void handle(COpenConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft.getInstance().setScreen(new TraitableConfigScreen());
        });
        ctx.get().setPacketHandled(true);
    }

}
