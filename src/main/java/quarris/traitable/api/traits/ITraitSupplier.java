package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;

public interface ITraitSupplier {

    Trait create(Entity entity);

}
