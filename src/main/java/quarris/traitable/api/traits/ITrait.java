package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITrait<T extends Entity> {

    T getEntity();

}
