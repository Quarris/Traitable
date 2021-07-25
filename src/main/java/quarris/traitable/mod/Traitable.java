package quarris.traitable.mod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.setup.SetupHandler;

@Mod(ModRef.ID)
public class Traitable {

    public static final ResourceKey<Registry<TraitType<?>>> TRAIT_TYPE_REGISTRY_KEY = ResourceKey.createRegistryKey(ModRef.createRes("trait_type"));

    public static final TraitsSettings SETTINGS = new TraitsSettings();

    public static final Logger LOGGER = LogManager.getLogger();

    public Traitable() {
        LOGGER.info("--- Initializing " + ModRef.NAME + " ---");
        TraitableAPI.HOOKS = new InternalHooks();
        FMLJavaModLoadingContext.get().getModEventBus().register(new SetupHandler());
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryHandler {

        @SubscribeEvent
        public static void createRegistries(RegistryEvent.NewRegistry event) {
            new RegistryBuilder<TraitType<?>>()
                    .setName(TRAIT_TYPE_REGISTRY_KEY.location())
                    .setType(c(TraitType.class))
                    .setMaxID(Integer.MAX_VALUE - 1).create();
        }
    }

    private static <T> Class<T> c(Class<?> cls) {return (Class<T>)cls; }
}
