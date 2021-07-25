package quarris.traitable.mod.traits;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import quarris.traitable.api.traits.Trait;
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

    private final Map<ResourceLocation, Trait> activeTraits;

    private final Entity holder;

    public TraitHolder(Entity holder) {
        this.holder = holder;
        this.activeTraits = new HashMap<>();
    }

    public boolean activate(TraitType type) {
        if (Traitable.SETTINGS.canAttachTraitTo(this.holder, type.getRegistryName())) {
            Trait trait = type.create(this.holder);
            trait.onActivated(false);
            this.activeTraits.put(type.getRegistryName(), trait);
            return true;
        }

        return false;
    }

    public boolean deactivate(TraitType type) {
        if (this.activeTraits.containsKey(type.getRegistryName())) {
            Trait removed = this.activeTraits.remove(type.getRegistryName());
            removed.onDeactivated();
            return true;
        }

        return false;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        ListTag traitsNBT = new ListTag();
        for (Trait trait : this.activeTraits.values()) {
            CompoundTag traitData = new CompoundTag();
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
    public void deserializeNBT(CompoundTag nbt) {
        this.activeTraits.clear();
        ListTag traitsNBT = nbt.getList("Traits", Constants.NBT.TAG_COMPOUND);
        for (Tag rawTraitData : traitsNBT) {
            CompoundTag traitData = (CompoundTag) rawTraitData;
            ResourceLocation typeName = new ResourceLocation(traitData.getString("Type"));
            TraitType type = TraitRegistry.TRAIT_TYPES.getValue(typeName);
            if (type == null) {
                Traitable.LOGGER.warn("Couldn't load trait '{}' for entity '{}' because it is not registered.", typeName, this.holder.getDisplayName().getString());
                continue;
            }
            Trait trait = type.create(this.holder);
            if (trait instanceof INBTSerializable && traitData.contains("Data")) {
                ((INBTSerializable) trait).deserializeNBT(traitData.get("Data"));
            }
            trait.onActivated(true);
            this.activeTraits.put(typeName, trait);
        }
    }

    public static class Provider implements ICapabilitySerializable<CompoundTag> {

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
        public CompoundTag serializeNBT() {
            return inst.orElse(EmptyTraitHolder.INST).serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            inst.orElse(EmptyTraitHolder.INST).deserializeNBT(nbt);
        }
    }
}
