package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies whether the result must be written as tagged PDF.
 */
public enum WriteTags {
    /**
     * Automatic selection: the tags are written into the output PDF file
     * if it must comply with PDF/A-1a standard, and are not written otherwise.
     */
    Auto (1),

    /**
     * Write tags
     */
    Write (2),

    /**
     * Don't write tags
     */
    DontWrite (3);

    private int value;

    WriteTags(int value) {
        this.value = value;
    }
}
