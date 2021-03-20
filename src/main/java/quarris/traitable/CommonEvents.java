package quarris.traitable;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.traitable.traits.TraitHolder;


@Mod.EventBusSubscriber(modid = Traitable.ID)
public class CommonEvents {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(ModUtil.createRes("trait_holder"), new TraitHolder.Provider(new TraitHolder(event.getObject())));
    }

}
