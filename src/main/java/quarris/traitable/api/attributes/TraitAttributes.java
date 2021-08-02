package quarris.traitable.api.attributes;

import java.util.HashMap;
import java.util.Map;

public class TraitAttributes {

    private Map<String, ITraitAttribute<?>> attributes = new HashMap<>();

    public void setAttribute(String name, ITraitAttribute<?> attribute) {
        this.attributes.put(name, attribute);
    }
}
