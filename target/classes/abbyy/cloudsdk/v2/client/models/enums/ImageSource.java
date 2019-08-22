package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies the source of the image. It can be either a scanned image,
 * or a photograph created with a digital camera. Special preprocessing
 * operations can be performed with the image depending on the selected
 * source. For example, the system can automatically correct distorted
 * text lines, poor focus and lighting on photos.
 */
public enum ImageSource {
    /**
     * The image source is detected automatically.
     */
    Auto (1),

    /**
     * Photo
     */
    Photo (2),

    /**
     * Scanner
     */
    Scanner (3);

    private int value;

    ImageSource(int value) {
        this.value = value;
    }
}
