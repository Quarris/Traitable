package quarris.traitable.api.traits;

import net.minecraftforge.common.extensions.IForgeEntity;

public class Trait<T extends IForgeEntity> {

    private final TraitType type;
    protected final T entity;

    public Trait(TraitType type, T entity) {
        this.type = type;
        this.entity = entity;
    }

    public void onActivated(boolean forSync) {

    }

    public void onDeactivated() {

    }

    public T getEntity() {
        return this.entity;
    }

    public TraitType getType() {
        return this.type;
    }
}
