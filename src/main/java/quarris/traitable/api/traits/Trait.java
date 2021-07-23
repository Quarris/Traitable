package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;

public class Trait implements ITrait {

    private final TraitType type;
    protected final Entity entity;

    public Trait(TraitType type, Entity entity) {
        this.type = type;
        this.entity = entity;
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public TraitType getType() {
        return this.type;
    }
}
