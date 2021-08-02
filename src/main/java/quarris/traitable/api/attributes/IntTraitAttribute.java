package quarris.traitable.api.attributes;

public record IntTraitAttribute(
    int value
) implements ITraitAttribute<Integer> {

    @Override
    public Integer get() {
        return this.value;
    }
}
