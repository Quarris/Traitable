package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;

public interface ITraitSupplier<T extends Entity> {

    Trait<T> create(T entity);

}
