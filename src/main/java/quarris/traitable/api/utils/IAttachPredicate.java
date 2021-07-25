package quarris.traitable.api.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public interface IAttachPredicate extends Predicate<Entity> {

    IAttachPredicate IS_PLAYER = entity -> entity instanceof Player;
    IAttachPredicate IS_LIVING = entity -> entity instanceof LivingEntity;

}
