package quarris.traitable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import quarris.traitable.traits.EmptyTraitHolder;
import quarris.traitable.traits.ITraitHolder;
import quarris.traitable.traits.TraitHolder;

public class ModUtil {

    public static <T> void registerCapability(Class<T> capabilityClass) {
        CapabilityManager.INSTANCE.register(capabilityClass, new Capability.IStorage<T>() {
            public INBT writeNBT(Capability<T> capability, T instance, Direction side) { return null; }
            public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) { }
        }, () -> null);
    }

    public static ResourceLocation createRes(String res) {
        return new ResourceLocation(Traitable.ID, res);
    }

    public static ITraitHolder getTraitHolder(Entity entity) {
        return entity.getCapability(TraitHolder.CAPABILITY).orElse(EmptyTraitHolder.INST);
    }

}
