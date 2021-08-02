package quarris.traitable.mod.traits;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.attributes.TraitAttributes;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.ModRef;

import java.util.Set;
import java.util.stream.Collectors;

public class TraitManager {

    private final Table<EntityType<?>, TraitType<?>, TraitAttributes> attachedTraits = HashBasedTable.create();

    public boolean canAttachTraitTo(Entity entity, TraitType<?> trait) {
        return trait.canAttach.test(entity);
    }

    public void detachTrait(EntityType<?> entity, TraitType<?> trait) {
        this.attachedTraits.remove(entity, trait);
    }

    public void attachTrait(EntityType<?> entity, TraitType<?> trait) {
        if (this.attachedTraits.contains(entity, trait)) {
            ModRef.LOGGER.error("Tried to attach a trait '{}' to an entity '{}' that already exists.", entity.getRegistryName(), trait.getRegistryName());
            return;
        }
        this.attachedTraits.put(entity, trait, trait.defaultAttributes);
    }

    public void attachTraitWithAttributes(EntityType<?> entity, TraitType<?> trait, TraitAttributes attributes) {
        if (this.attachedTraits.contains(entity, trait)) {
            ModRef.LOGGER.error("Tried to attach a trait to an entity that already exists.");
            return;
        }
        this.attachedTraits.put(entity, trait, attributes);
    }

    public boolean hasAttachedTraits(Entity entity) {
        return TraitableAPI.TRAIT_REGISTRY.getValues().stream().anyMatch(type -> TraitableAPI.canAttachTraitTo(entity, type));
    }

    public <T extends Entity> TraitAttributes getAttributesForEntity(T entity) {
        return null;
    }

    public Set<ResourceLocation> getTraitKeysForEntity(EntityType<?> entity) {
        return attachedTraits.row(entity).keySet().stream().map(TraitType::getRegistryName).collect(Collectors.toSet());
    }

    // public static void attachTraitType(EntityType<?> entity)

    //public record TraitTypeInstance(TraitType<?> type, TraitAttributes attributes) {};

}
