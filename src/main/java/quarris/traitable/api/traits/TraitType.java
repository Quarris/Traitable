package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistryEntry;
import quarris.traitable.api.utils.IAttachPredicate;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class TraitType<T extends IForgeEntity> extends ForgeRegistryEntry<TraitType<?>> {

    private final Set<TraitTypeEffect<?, ?, ?>> effects;
    private final ITraitSupplier<T> traitSupplier;

    private TraitType(ITraitSupplier<T> traitSupplier, Set<TraitTypeEffect<?, ?, ?>> effects) {
        this.traitSupplier = traitSupplier;
        this.effects = effects;
    }

    public Trait<T> create(T entity) {
        return this.traitSupplier.create(entity);
    }

    public static class Builder<T extends IForgeEntity> {

        private Set<TraitTypeEffect<?, ?, ?>> effects = new HashSet<>();
        private IAttachPredicate attachPredicate = IAttachPredicate.ALWAYS;

        public Builder<T> setAttachPredicate(IAttachPredicate predicate) {
            this.attachPredicate = predicate;
            return this;
        }

        public <R extends Trait<T>, E extends Event> Builder<T> addEffect(Class<E> eventClass, ITraitEffect<R, E> effect, Function<E, T> entityGetter) {
            this.effects.add(new TraitTypeEffect<>(eventClass, effect, entityGetter));
            return this;
        }

        public TraitType<T> create(ITraitSupplier<T> supplier) {
            return new TraitType<>(supplier, effects);
        }
    }

    public record TraitTypeEffect<T extends IForgeEntity, R extends Trait<T>, E extends Event>(
        Class<E> eventClass,
        ITraitEffect<R, E> effect,
        Function<E, T> entityGetter) {
    }

}
