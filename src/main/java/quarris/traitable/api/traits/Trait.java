package quarris.traitable.api.traits;

import net.minecraft.world.entity.Entity;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.api.attributes.TraitAttributes;

public class Trait<T extends Entity> {

    private final TraitType<?> type;
    protected final T entity;

    public Trait(TraitType<?> type, T entity) {
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

    public TraitType<?> getType() {
        return this.type;
    }

    protected TraitAttributes getAttributes() {
        return TraitableAPI.getAttributesForEntity(this.getEntity());
    }
}
