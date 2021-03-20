package quarris.traitable;

import net.minecraftforge.registries.*;
import quarris.traitable.api.traits.TraitType;

public class TraitRegistry {

    public static final IForgeRegistry<TraitType> TRAIT_TYPES = RegistryManager.ACTIVE.getRegistry(TraitType.class);
}
