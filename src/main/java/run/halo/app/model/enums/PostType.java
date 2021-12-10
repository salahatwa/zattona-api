package run.halo.app.model.enums;

/**
 * Post type.
 *
 * @author ssatwa
 */
@Deprecated
public enum PostType implements ValueEnum<Integer> {

	/**
     * Ordinary article
     */
    POST(0),

    /**
     * Custom page
     */
    PAGE(1),

    /**
     * Log
     */
    JOURNAL(2);

    private final Integer value;

    PostType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
