package quarris.traitable.api.traits;

import net.minecraftforge.eventbus.api.Event;

public interface ITraitEffect<T extends ITrait, E extends Event> {

    void run(T trait, E event);

}
