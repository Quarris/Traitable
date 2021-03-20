package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;

public interface ITraitSupplier {

    ITrait create(Entity entity);

}
