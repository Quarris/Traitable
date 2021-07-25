package quarris.traitable.api;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import quarris.traitable.api.traits.TraitType;

public class TraitableAPI {

    public static final IForgeRegistry<TraitType<?>> TRAIT_REGISTRY = RegistryManager.ACTIVE.getRegistry(TraitType.class);

    public static IInternalHooks HOOKS;

}
