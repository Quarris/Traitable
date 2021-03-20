package quarris.traitable.api.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public interface IAttachPredicate extends Predicate<Entity> {

    IAttachPredicate IS_PLAYER = entity -> entity instanceof PlayerEntity;
    IAttachPredicate IS_LIVING = entity -> entity instanceof LivingEntity;

}
