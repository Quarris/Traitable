package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.extensions.IForgeEntity;

public interface ITraitSupplier<T extends IForgeEntity> {

    Trait<T> create(T entity);

}
