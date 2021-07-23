package quarris.traitable.mod;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.setup.TraitRegistry;

public class TraitsSettings {

    private final Multimap<EntityType<?>, TraitType> entityAttachableTraitTypes = HashMultimap.create();

    public TraitsSettings() {

    }

    public void attachTraitTypeToEntity(EntityType<?> entity, TraitType trait) {
        this.entityAttachableTraitTypes.put(entity, trait);
    }

    public boolean hasAttachableTraits(Entity entity) {
        return this.entityAttachableTraitTypes.containsKey(entity.getType());
    }

    public boolean canAttachTraitTo(Entity entity, ResourceLocation traitName) {
        return this.entityAttachableTraitTypes.containsEntry(entity.getType(), TraitRegistry.TRAIT_TYPES.getValue(traitName));
    }
}
