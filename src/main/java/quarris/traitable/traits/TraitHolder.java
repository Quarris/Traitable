package quarris.traitable.traits;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TraitHolder implements ITraitHolder {

    @CapabilityInject(ITraitHolder.class)
    public static Capability<ITraitHolder> CAPABILITY;

    private Entity holder;

    public TraitHolder(Entity holder) {
        this.holder = holder;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return new CompoundNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }

    public static class Provider implements ICapabilitySerializable<CompoundNBT> {

        private LazyOptional<ITraitHolder> inst;

        public Provider(ITraitHolder holder) {
            this.inst = LazyOptional.of(() -> holder);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if (cap == CAPABILITY) {
                return inst.cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public CompoundNBT serializeNBT() {
            return inst.orElse(EmptyTraitHolder.INST).serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            inst.orElse(EmptyTraitHolder.INST).deserializeNBT(nbt);
        }
    }
}
