package quarris.traitable.mod.traits.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import quarris.traitable.api.traits.Trait;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.Traitable;

public class TestTrait extends Trait {

    public TestTrait(Entity entity) {
        super(Traitable.TEST_TRAIT_TYPE, entity);
    }
}
