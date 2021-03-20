package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import quarris.traitable.api.utils.IAttachPredicate;

public class TraitType extends ForgeRegistryEntry<TraitType> {

    private final ITraitSupplier traitSupplier;

    private TraitType(ITraitSupplier traitSupplier) {
        this.traitSupplier = traitSupplier;
    }

    public ITrait create(Entity entity) {
        return this.traitSupplier.create(entity);
    }

    public static class Builder {

        private IAttachPredicate attachPredicate = e -> true;

        public Builder setAttachPredicate(IAttachPredicate predicate) {
            this.attachPredicate = predicate;
            return this;
        }

        public TraitType create(ITraitSupplier supplier) {
            return new TraitType(supplier);
        }
    }

}
