package abbyy.cloudsdk.v2.client.models.enums;

/**
 * Specifies the country where the receipt was printed.
 * This parameter can contain several names of countries.
 */
public enum ReceiptRecognizingCountry {
    Uk (1),
    Usa (2),
    Australia (3),
    Canada (4),
    Japan (5),
    Germany (6),
    Italy (7),
    France (8),
    Brazil (9),
    Russia (10),
    China (11),
    Korea (12),
    Netherlands (13),
    Spain (14),
    Singapore (15),
    Taiwan (16),
    Turkey (17),
    Poland (18);

    private int value;

    ReceiptRecognizingCountry(int value) {
        this.value = value;
    }
}
