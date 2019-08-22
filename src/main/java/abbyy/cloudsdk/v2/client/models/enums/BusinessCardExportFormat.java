package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies the export format.
 */
public enum BusinessCardExportFormat {
    /**
     * XML
     */
    Xml (1),

    /**
     * vCard
     */
    VCard (2),

    /**
     * CSV
     */
    Csv (3);

    private int value;

    BusinessCardExportFormat(int value) {
        this.value = value;
    }
}
