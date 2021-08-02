package quarris.traitable.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.IForgeRegistry;
import quarris.traitable.api.attributes.TraitAttributes;
import quarris.traitable.api.traits.TraitType;

import java.util.Set;

public class TraitableAPI {

    public static IForgeRegistry<TraitType<?>> TRAIT_REGISTRY;

    public static IInternalHooks HOOKS;

    public static Set<ResourceLocation> getTraitsForEntity(EntityType<?> entity) {
        return HOOKS.getTraitManager().getTraitKeysForEntity(entity);
    }

    public static boolean canAttachTraitTo(Entity entity, TraitType<?> trait) {
        return HOOKS.getTraitManager().canAttachTraitTo(entity, trait);
    }

    public static void attachTrait(EntityType<?> entity, TraitType<?> trait) {
        HOOKS.getTraitManager().attachTrait(entity, trait);
    }

    public static void attachTraitWithAttributes(EntityType<?> entity, TraitType<?> trait, TraitAttributes attributes) {
        HOOKS.getTraitManager().attachTraitWithAttributes(entity, trait, attributes);
    }

    public static boolean hasAttachedTraits(Entity entity) {
        return HOOKS.getTraitManager().hasAttachedTraits(entity);
    }

    public static <T extends Entity> TraitAttributes getAttributesForEntity(T entity) {
        return HOOKS.getTraitManager().getAttributesForEntity(entity);
    }

    // public static void attachTraitType(EntityType<?> entity)

}
