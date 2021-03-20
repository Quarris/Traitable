package quarris.traitable.mod;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.traits.impl.TestTrait;

@Mod(Traitable.ID)
public class Traitable {

    public static final String ID = "traitable";
    public static final String NAME = "Traitable";

    public static final RegistryKey<Registry<TraitType<?>>> TRAIT_TYPE_REGISTRY_KEY = RegistryKey.getOrCreateRootKey(ModUtil.createRes("trait_type"));

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Traitable() {
        LOGGER.info("--- Initializing " + NAME + " ---");
        TraitableAPI.HOOKS = new InternalHooks();
        FMLJavaModLoadingContext.get().getModEventBus().register(new SetupHandler());
    }

    @Mod.EventBusSubscriber(modid = ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryHandler {

        @SubscribeEvent
        public static void createRegistries(RegistryEvent.NewRegistry event) {
            new RegistryBuilder<TraitType<?>>()
                    .setName(TRAIT_TYPE_REGISTRY_KEY.getLocation())
                    .setType(cast(TraitType.class))
                    .setMaxID(Integer.MAX_VALUE - 1).create();
        }

        @SubscribeEvent
        public static void registerTraits(RegistryEvent.Register<TraitType<?>> event) {
            event.getRegistry().register(new TraitType.Builder().create(TestTrait::new).setRegistryName(ModUtil.createRes("test")));
        }

        @SuppressWarnings({"unchecked", "SameParameterValue"})
        private static <T> Class<T> cast(Class<?> cls) {
            return (Class<T>) cls;
        }
    }
}
