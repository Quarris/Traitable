package quarris.traitable.mod.traits;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import quarris.traitable.api.traits.ITrait;
import quarris.traitable.api.traits.TraitType;
import quarris.traitable.mod.Traitable;
import quarris.traitable.mod.setup.TraitRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TraitHolder implements ITraitHolder {

    @CapabilityInject(ITraitHolder.class)
    public static Capability<ITraitHolder> CAPABILITY;

    private final Map<ResourceLocation, ITrait> activeTraits;

    private final Entity holder;

    public TraitHolder(Entity holder) {
        this.holder = holder;
        this.activeTraits = new HashMap<>();
    }

    public boolean activate(TraitType type) {
        if (Traitable.SETTINGS.canAttachTraitTo(this.holder, type.getRegistryName())) {
            ITrait trait = type.create(this.holder);
            trait.onActivated(false);
            this.activeTraits.put(type.getRegistryName(), trait);
            return true;
        }

        return false;
    }

    public boolean deactivate(TraitType type) {
        if (this.activeTraits.containsKey(type.getRegistryName())) {
            ITrait removed = this.activeTraits.remove(type.getRegistryName());
            removed.onDeactivated();
            return true;
        }

        return false;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();

        ListNBT traitsNBT = new ListNBT();
        for (ITrait trait : this.activeTraits.values()) {
            CompoundNBT traitData = new CompoundNBT();
            traitData.putString("Type", trait.getType().getRegistryName().toString());
            if (trait instanceof INBTSerializable) {
                traitData.put("Data", ((INBTSerializable<?>) trait).serializeNBT());
            }
            traitsNBT.add(traitData);
        }
        nbt.put("Traits", traitsNBT);

        return nbt;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.activeTraits.clear();
        ListNBT traitsNBT = nbt.getList("Traits", Constants.NBT.TAG_COMPOUND);
        for (INBT rawTraitData : traitsNBT) {
            CompoundNBT traitData = (CompoundNBT) rawTraitData;
            ResourceLocation typeName = new ResourceLocation(traitData.getString("Type"));
            TraitType type = TraitRegistry.TRAIT_TYPES.getValue(typeName);
            if (type == null) {
                Traitable.LOGGER.warn("Couldn't load trait '{}' for entity '{}' because it is not registered.", typeName, this.holder.getDisplayName().getString());
                continue;
            }
            ITrait trait = type.create(this.holder);
            if (trait instanceof INBTSerializable && traitData.contains("Data")) {
                ((INBTSerializable) trait).deserializeNBT(traitData.get("Data"));
            }
            trait.onActivated(true);
            this.activeTraits.put(typeName, trait);
        }
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
