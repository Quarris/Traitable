package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import quarris.traitable.api.utils.IAttachPredicate;

public class TraitType<T extends Entity> extends ForgeRegistryEntry<TraitType<T>> {

    private final IAttachPredicate attachPredicate;
    private final ITraitSupplier<T> traitSupplier;

    private TraitType(IAttachPredicate attachPredicate, ITraitSupplier<T> traitSupplier) {
        this.attachPredicate = attachPredicate;
        this.traitSupplier = traitSupplier;
    }

    public boolean canAttachTo(Entity entity) {
        return this.attachPredicate.test(entity);
    }

    public ITrait<T> create(T entity) {
        return this.traitSupplier.create(entity);
    }

    public static class Builder {

        private IAttachPredicate attachPredicate = e -> true;

        public Builder setAttachPredicate(IAttachPredicate predicate) {
            this.attachPredicate = predicate;
            return this;
        }

        public <T extends Entity> TraitType<T> create(ITraitSupplier<T> supplier) {
            return new TraitType<>(this.attachPredicate, supplier);
        }
    }

}
