package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistryEntry;
import quarris.traitable.api.attributes.ITraitAttribute;
import quarris.traitable.api.attributes.TraitAttributes;
import quarris.traitable.api.utils.IAttachPredicate;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class TraitType<T extends Entity> extends ForgeRegistryEntry<TraitType<?>> {

    public final Set<TraitTypeEffect<?, ?, ?>> effects;
    public final IAttachPredicate canAttach;
    public final ITraitSupplier<T> traitSupplier;
    public final TraitAttributes defaultAttributes;

    private TraitType(ITraitSupplier<T> traitSupplier, IAttachPredicate attachPredicate, Set<TraitTypeEffect<?, ?, ?>> effects, TraitAttributes defaultAttributes) {
        this.traitSupplier = traitSupplier;
        this.canAttach = attachPredicate;
        this.effects = effects;
        this.defaultAttributes = defaultAttributes;
    }

    public Trait<T> create(T entity) {
        return this.traitSupplier.create(entity);
    }

    public static class Builder<T extends Entity> {

        private Set<TraitTypeEffect<?, ?, ?>> effects = new HashSet<>();
        private IAttachPredicate attachPredicate = IAttachPredicate.ALWAYS;
        private TraitAttributes defaultAttributes = new TraitAttributes();

        public Builder<T> setAttachPredicate(IAttachPredicate predicate) {
            this.attachPredicate = predicate;
            return this;
        }

        public Builder<T> setDefaultAttributes(TraitAttributes attributes) {
            this.defaultAttributes = attributes;
            return this;
        }

        public Builder<T> setAttribute(String name, ITraitAttribute<?> attribute) {
            this.defaultAttributes.setAttribute(name, attribute);
            return this;
        }

        public <R extends Trait<T>, E extends Event> Builder<T> addEffect(Class<E> eventClass, ITraitEffect<R, E> effect, Function<E, T> entityGetter) {
            this.effects.add(new TraitTypeEffect<>(eventClass, effect, entityGetter));
            return this;
        }

        public TraitType<T> create(ITraitSupplier<T> supplier) {
            return new TraitType<>(supplier, this.attachPredicate, this.effects, this.defaultAttributes);
        }
    }

    public record TraitTypeEffect<T extends Entity, R extends Trait<T>, E extends Event>(
        Class<E> eventClass,
        ITraitEffect<R, E> effect,
        Function<E, T> entityGetter) {
    }

}
