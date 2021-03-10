package quarris.traitable;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.traits.ITraitHolder;

@Mod(Traitable.ID)
public class Traitable {

    public static final String ID = "traitable";
    public static final String NAME = "Traitable";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Traitable() {
        LOGGER.info("--- Starting Traitable ---");
        TraitableAPI.HOOKS = new InternalHooks();
        ModUtil.registerCapability(ITraitHolder.class);
    }
}
