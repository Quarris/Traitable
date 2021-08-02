package quarris.traitable.mod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.setup.SetupHandler;
import quarris.traitable.mod.traits.BuiltinTraits;

@Mod(ModRef.ID)
public class Traitable {

    public static final ResourceKey<Registry<TraitType<?>>> TRAIT_TYPE_REGISTRY_KEY = ResourceKey.createRegistryKey(ModRef.createRes("trait_type"));

    public Traitable() {
        ModRef.LOGGER.info("--- Initializing " + ModRef.NAME + " ---");
        TraitableAPI.HOOKS = new InternalHooks();
        FMLJavaModLoadingContext.get().getModEventBus().register(new SetupHandler());
        BuiltinTraits.init(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryHandler {

        @SubscribeEvent
        public static void createRegistries(RegistryEvent.NewRegistry event) {
            TraitableAPI.TRAIT_REGISTRY = new RegistryBuilder<TraitType<?>>()
                .setName(TRAIT_TYPE_REGISTRY_KEY.location())
                .setType(c(TraitType.class))
                .setMaxID(Integer.MAX_VALUE - 1).create();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> c(Class<?> cls) { return (Class<T>) cls; }
}
