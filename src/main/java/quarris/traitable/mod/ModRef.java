package quarris.traitable.mod;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import quarris.traitable.mod.traits.EmptyTraitHolder;
import quarris.traitable.mod.traits.ITraitHolder;
import quarris.traitable.mod.traits.TraitHolder;

public class ModRef {

    public static final String ID = "traitable";
    public static final String NAME = "Traitable";

    public static ResourceLocation createRes(String res) {
        return new ResourceLocation(ID, res);
    }

    public static ITraitHolder getTraitHolder(Entity entity) {
        return entity.getCapability(TraitHolder.CAPABILITY).orElse(EmptyTraitHolder.INST);
    }

}
