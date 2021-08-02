package quarris.traitable.mod.traits;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.api.utils.IAttachPredicate;
import quarris.traitable.mod.ModRef;
import quarris.traitable.mod.traits.impl.HealthTrait;
import quarris.traitable.mod.traits.impl.TestTrait;

public class BuiltinTraits {

    public static final DeferredRegister<TraitType<?>> BUILTIN_TRAITS = DeferredRegister.<TraitType<?>>create(c(TraitType.class), ModRef.ID);

    public static final RegistryObject<TraitType<?>> TEST_TRAIT = BUILTIN_TRAITS.register("test", () ->
        new TraitType.Builder<Entity>()
            .addEffect(LivingEvent.LivingUpdateEvent.class, (trait, evt) -> {
                System.out.println(evt.getEntityLiving().tickCount);
            }, LivingEvent::getEntityLiving)
            .create(TestTrait::new));

    public static final RegistryObject<TraitType<?>> HEALTH_TRAIT = BUILTIN_TRAITS.register("max_health", () ->
        new TraitType.Builder<LivingEntity>()
            .setAttachPredicate(IAttachPredicate.IS_LIVING)
            .create(HealthTrait::new));

    public static void init(IEventBus bus) {
        BUILTIN_TRAITS.register(bus);
    }

    private static <T> Class<T> c(Class<?> cls) {
        return (Class<T>) cls;
    }

}
