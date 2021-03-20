package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;

public class Trait implements ITrait {

    protected final Entity entity;

    public Trait(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }
}
