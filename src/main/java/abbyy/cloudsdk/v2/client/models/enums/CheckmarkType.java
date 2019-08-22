package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies the type of the checkmark.
 */
public enum CheckmarkType {
    /**
     * Checkmark against an empty background
     */
    Empty (1),

    /**
     * Checkmark in a circle
     */
    Circle (2),

    /**
     * Checkmark in a square
     */
    Square (3);

    private int value;

    CheckmarkType(int value) {
        this.value = value;
    }
}
