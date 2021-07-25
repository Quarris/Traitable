package quarris.traitable.mod.traits.impl;

import net.minecraft.world.entity.Entity;
import quarris.traitable.api.traits.Trait;
import quarris.traitable.mod.Traitable;

public class TestTrait extends Trait {

    public TestTrait(Entity entity) {
        super(Traitable.TEST_TRAIT_TYPE, entity);
    }
}
