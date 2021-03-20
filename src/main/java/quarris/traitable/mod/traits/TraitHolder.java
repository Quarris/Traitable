package quarris.traitable.mod.traits;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import quarris.traitable.api.traits.ITrait;
import quarris.traitable.api.traits.TraitType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class TraitHolder implements ITraitHolder {

    @CapabilityInject(ITraitHolder.class)
    public static Capability<ITraitHolder> CAPABILITY;

    private Set<ResourceLocation> availableTraits;
    private Set<ITrait> activeTraits;

    private final Entity holder;

    public TraitHolder(Entity holder) {
        this.holder = holder;
        this.availableTraits = new HashSet<>();
        this.activeTraits = new HashSet<>();
    }

    public boolean activate(TraitType type) {
        if (this.availableTraits.contains(type.getRegistryName())) {
            ITrait trait = type.create(this.holder);
            this.activeTraits.add(trait);
            return true;
        }

        return false;
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
