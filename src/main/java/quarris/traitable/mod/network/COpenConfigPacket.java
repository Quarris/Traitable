package quarris.traitable.mod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import quarris.traitable.mod.screen.TraitableConfigScreen;

import java.util.function.Supplier;

public class COpenConfigPacket {

    public COpenConfigPacket() {
    }

    public static void encode(COpenConfigPacket packet, PacketBuffer buf) {

    }

    public static COpenConfigPacket decode(PacketBuffer buf) {
        return new COpenConfigPacket();
    }

    public static void handle(COpenConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft.getInstance().displayGuiScreen(new TraitableConfigScreen());
        });
        ctx.get().setPacketHandled(true);
    }

}
