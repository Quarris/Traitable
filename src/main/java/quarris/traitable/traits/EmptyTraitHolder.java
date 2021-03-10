package quarris.traitable.traits;

import net.minecraft.nbt.CompoundNBT;

public final class EmptyTraitHolder implements ITraitHolder {

    public static final EmptyTraitHolder INST = new EmptyTraitHolder();

    private EmptyTraitHolder() { }

    @Override
    public CompoundNBT serializeNBT() { return new CompoundNBT(); }
    @Override
    public void deserializeNBT(CompoundNBT nbt) { }
}
