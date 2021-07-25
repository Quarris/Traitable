package quarris.traitable.mod.traits.impl;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import quarris.traitable.api.traits.Trait;
import quarris.traitable.api.traits.TraitType;

import java.util.UUID;

public class HealthTrait extends Trait {

    private static final UUID ATTR_UUID = UUID.fromString("55560ed0-1545-4b52-bb0a-519529d6950e");

    public HealthTrait(TraitType type, LivingEntity entity) {
        super(type, entity);
    }

    @Override
    public void onActivated(boolean forSync) {
        AttributeInstance attribute = ((LivingEntity) this.getEntity()).getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null)
            attribute.addTransientModifier(new AttributeModifier(ATTR_UUID, "health_trait", 10, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void onDeactivated() {
        AttributeInstance attribute = ((LivingEntity) this.getEntity()).getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null)
            attribute.removeModifier(ATTR_UUID);
    }
}
