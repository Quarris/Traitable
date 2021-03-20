package quarris.traitable.api.traits;

import net.minecraft.entity.Entity;

public class Trait<T extends Entity> implements ITrait<T> {

    protected final T entity;

    public Trait(T entity) {
        this.entity = entity;
    }

    @Override
    public T getEntity() {
        return this.entity;
    }
}
