package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies the type of marking around letters
 *
 * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/field-marking/"/>
 */
public enum MarkingType {
    /**
     * This value denotes plain text.
     */
    SimpleText (1),

    /**
     * This value specifies that the text is underlined.
     */
    UnderlinedText (2),

    /**
     * This value specifies that the text is enclosed in a
     * frame.
     */
    TextInFrame (3),

    /**
     * This value specifies that the text is located in white
     * fields on a gray background.
     */
    GreyBoxes (4),

    /**
     * This value specifies that the field where the text is
     * located is a set of separate boxes.
     */
    CharBoxSeries (5),

    /**
     * This value specifies that the field where the text is
     * located is a comb.
     */
    SimpleComb (6),

    /**
     * This value specifies that the field where the text is
     * located is a comb and that this comb is also the bottom
     * line of a frame.
     */
    CombInFrame (7),

    /**
     * This value specifies that the field where the text is
     * located is a frame and this frame is split by vertical
     * lines.
     */
    PartitionedFrame (8);

    private int value;

    MarkingType(int value) {
        this.value = value;
    }
}
