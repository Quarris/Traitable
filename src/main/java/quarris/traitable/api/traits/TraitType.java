package quarris.traitable.api.traits;

import net.minecraft.util.ResourceLocation;

public class TraitType {

    public final ResourceLocation name;
    //public final ITraitSupplier supplier;

    private TraitType(ResourceLocation name) {
        this.name = name;
    }

    public static class Builder {

    }

}
