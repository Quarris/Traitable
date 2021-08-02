package quarris.traitable.mod;

import quarris.traitable.api.IInternalHooks;
import quarris.traitable.mod.traits.TraitManager;

public class InternalHooks implements IInternalHooks {

    private final TraitManager traitManager = new TraitManager();

    @Override
    public TraitManager getTraitManager() {
        return this.traitManager;
    }
}
