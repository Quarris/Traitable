package quarris.traitable.mod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import quarris.traitable.mod.traits.ITraitHolder;

public class SetupHandler {

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        ModUtil.registerCapability(ITraitHolder.class);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {

    }

}
