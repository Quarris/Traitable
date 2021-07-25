package quarris.traitable.mod.traits;

import net.minecraft.nbt.CompoundTag;

public final class EmptyTraitHolder implements ITraitHolder {

    public static final EmptyTraitHolder INST = new EmptyTraitHolder();

    private EmptyTraitHolder() { }

    @Override
    public CompoundTag serializeNBT() { return new CompoundTag(); }
    @Override
    public void deserializeNBT(CompoundTag nbt) { }
}
