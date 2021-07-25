package quarris.traitable.mod.traits;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.ModRef;
import quarris.traitable.mod.traits.impl.HealthTrait;
import quarris.traitable.mod.traits.impl.TestTrait;

public class BuiltinTraits {

    public static final DeferredRegister<TraitType> BUILTIN_TRAITS = DeferredRegister.create(TraitableAPI.TRAIT_REGISTRY, ModRef.ID);

    public static final RegistryObject<TraitType> TEST_TRAIT = BUILTIN_TRAITS.register("test", () ->
        TraitType.builder()
            .addEffect(LivingEvent.LivingUpdateEvent.class, (trait, evt) -> {
                System.out.println(evt.getEntityLiving().tickCount);
            }, LivingEvent::getEntityLiving)
            .create(TestTrait::new).setRegistryName(ModRef.createRes("test")));

    //public static final RegistryObject<TraitType> HEALTH_TRAIT = BUILTIN_TRAITS.register("max_health", () ->
        //TraitType.builder().create(HealthTrait::new).setRegistryName(ModRef.createRes("max_health")));

}
