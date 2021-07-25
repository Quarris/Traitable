package quarris.traitable.mod.setup;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import quarris.traitable.mod.network.PacketHandler;
import quarris.traitable.mod.traits.ITraitHolder;

public class SetupHandler {

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        PacketHandler.init();
        CapabilityManager.INSTANCE.register(ITraitHolder.class);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {
    }
}
