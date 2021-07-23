package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;

public interface ITrait {

    default void onActivated(boolean forSync) {}
    default void onDeactivated() {}

    Entity getEntity();
    TraitType getType();

}
