package abbyy.cloudsdk.v2.client.models.enums;

/**
 * ABBYY Cloud OCR SDK provides a set of processing profiles which are designed for the main usage
 * scenarios. Choose the profile which fits your situation, because selecting the wrong profile can
 *significantly reduce processing quality.
 */
public enum ProcessingProfile {
    /**
     * Suitable for converting documents into an editable format such as RTF or DOCX. This profile is
     * focused on retaining the original document structure and appearance, including font styles,
     * pictures, background color, etc.
     */
    DocumentConversion (1),

    /**
     * Suitable for creating a digital archive of searchable documents. This profile is not intended for
     * converting documents into an editable format. It is best optimized for creating searchable PDFs
     * (with the recognized text saved under the image). All possible text is found and recognized on the
     * input image, including text embedded into pictures.
     */
    DocumentArchiving (2),

    /**
     * Suitable for extracting all text from the input image, including small text areas of low quality.
     * The document appearance and structure are ignored, pictures and tables are not detected.
     *
     * <b>Note:</b> This profile is not intended for converting documents into an editable format. It is designed for
     * the situations when you need to retrieve the data from the image for some further processing on
     * your side, such as extracting data from bills, receipts or invoices for use in accounting systems.
     * Consider using XML export format, which allows you to access the recognized text coordinates.
     */
    TextExtraction (3),

    /**
     * Suitable for barcode extraction. Extracts only barcodes (texts, pictures, or tables are not detected)
     */
    BarcodeRecognition (4);

    private int value;

    ProcessingProfile(int value) {
        this.value = value;
    }
}
