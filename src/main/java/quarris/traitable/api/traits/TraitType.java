package quarris.traitable.api.traits;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class TraitType extends ForgeRegistryEntry<TraitType> {

    //public final ITraitSupplier supplier;

    private TraitType() {
    }

    public static class Builder {


        public TraitType create() {
            return new TraitType();
        }
    }

}
