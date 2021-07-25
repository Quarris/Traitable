package quarris.traitable.mod.traits.impl;

import net.minecraft.world.entity.Entity;
import quarris.traitable.api.traits.Trait;
import quarris.traitable.mod.Traitable;
import quarris.traitable.mod.traits.BuiltinTraits;

public class TestTrait extends Trait<Entity> {

    public TestTrait(Entity entity) {
        super(BuiltinTraits.TEST_TRAIT.get(), entity);
    }
}
