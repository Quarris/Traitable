package quarris.traitable.mod.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import quarris.traitable.mod.ModUtil;
import quarris.traitable.mod.network.PacketHandler;
import quarris.traitable.mod.traits.ITraitHolder;

public class SetupHandler {

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        PacketHandler.init();
        ModUtil.registerCapability(ITraitHolder.class);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {

    }
}
