package quarris.traitable.mod.setup;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import quarris.traitable.api.traits.TraitType;

public class TraitRegistry {

    public static final IForgeRegistry<TraitType> TRAIT_TYPES = RegistryManager.ACTIVE.getRegistry(TraitType.class);

}
