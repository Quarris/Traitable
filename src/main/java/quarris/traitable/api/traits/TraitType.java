package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistryEntry;
import quarris.traitable.api.utils.IAttachPredicate;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class TraitType extends ForgeRegistryEntry<TraitType> {

    private final Set<TraitTypeEffect<?, ?>> effects;
    private final ITraitSupplier traitSupplier;

    private TraitType(ITraitSupplier traitSupplier, Set<TraitTypeEffect<?, ?>> effects) {
        this.traitSupplier = traitSupplier;
        this.effects = effects;
    }

    public Trait create(Entity entity) {
        return this.traitSupplier.create(entity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Set<TraitTypeEffect<?, ?>> effects = new HashSet<>();
        private IAttachPredicate attachPredicate = IAttachPredicate.ALWAYS;

        public Builder setAttachPredicate(IAttachPredicate predicate) {
            this.attachPredicate = predicate;
            return this;
        }

        public <T extends Trait, E extends Event> Builder addEffect(Class<E> eventClass, ITraitEffect<T, E> effect, Function<E, Entity> entityGetter) {
            this.effects.add(new TraitTypeEffect<>(eventClass, effect, entityGetter));
            return this;
        }

        public TraitType create(ITraitSupplier supplier) {
            return new TraitType(supplier, effects);
        }
    }

    public record TraitTypeEffect<T extends Trait, E extends Event>(
        Class<E> eventClass,
        ITraitEffect<T, E> effect,
        Function<E, Entity> entityGetter) {
    }

}
