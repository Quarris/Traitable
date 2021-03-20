package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;

public interface ITraitSupplier<T extends Entity> {

    ITrait<T> create(T entity);

}
