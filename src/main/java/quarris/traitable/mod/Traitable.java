package quarris.traitable.mod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.setup.SetupHandler;
import quarris.traitable.mod.traits.impl.TestTrait;

@Mod(ModRef.ID)
public class Traitable {

    public static final ResourceKey<Registry<TraitType>> TRAIT_TYPE_REGISTRY_KEY = ResourceKey.createRegistryKey(ModRef.createRes("trait_type"));

    public static final TraitsSettings SETTINGS = new TraitsSettings();

    public static final Logger LOGGER = LogManager.getLogger();

    @ObjectHolder(ModRef.ID + ":test")
    public static TraitType TEST_TRAIT_TYPE;

    public Traitable() {
        LOGGER.info("--- Initializing " + ModRef.NAME + " ---");
        TraitableAPI.HOOKS = new InternalHooks();
        FMLJavaModLoadingContext.get().getModEventBus().register(new SetupHandler());
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryHandler {

        @SubscribeEvent
        public static void createRegistries(RegistryEvent.NewRegistry event) {
            new RegistryBuilder<TraitType>()
                    .setName(TRAIT_TYPE_REGISTRY_KEY.location())
                    .setType(TraitType.class)
                    .setMaxID(Integer.MAX_VALUE - 1).create();
        }

        @SubscribeEvent
        public static void registerTraits(RegistryEvent.Register<TraitType> event) {
            event.getRegistry().register(new TraitType.Builder()
                    .addEffect(LivingEvent.LivingUpdateEvent.class, (trait, evt) -> {
                        System.out.println(evt.getEntityLiving().tickCount);
                    }, LivingEvent::getEntityLiving)
                    .create(TestTrait::new).setRegistryName(ModRef.createRes("test")));
        }
    }
}
