package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;

public class Trait {

    private final TraitType type;
    protected final Entity entity;

    public Trait(TraitType type, Entity entity) {
        this.type = type;
        this.entity = entity;
    }

    public void onActivated(boolean forSync) {

    }

    public void onDeactivated() {

    }

    public Entity getEntity() {
        return this.entity;
    }

    public TraitType getType() {
        return this.type;
    }
}
