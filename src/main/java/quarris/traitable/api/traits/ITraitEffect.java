package quarris.traitable.api.traits;

import net.minecraftforge.eventbus.api.Event;

public interface ITraitEffect<T extends Trait, E extends Event> {

    void run(T trait, E event);

}
