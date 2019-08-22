package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies if the coordinates of field regions should be
 * saved to the resulting XML file, and how the coordinates
 * should be specified: on the original or on the corrected
 * image
 */
public enum FieldRegionExportMode {
    DoNotExport (1),
    ForOriginalImage (2),
    ForCorrectedImage (3);

    private int value;

    FieldRegionExportMode(int value) {
        this.value = value;
    }
}
