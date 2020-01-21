// Copyright © 2019 ABBYY Production LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.ocrsdk.abbyy.v2.client.models.enums;

/**
 * Format of exporting recognized text.
 * Several formats may be provided at once
 */
public enum ExportFormat {
    /**
     * TXT
     */
    Txt (1),

    /**
     * TXT. The exported file contains the text that was saved according to the order of the original blocks.
     */
    TxtUnstructured (2),

    /**
     * RTF
     */
    Rtf (3),

    /**
     * DOCX
     */
    Docx (4),

    /**
     * XLSX
     */
    Xlsx (5),

    /**
     * PPTX
     */
    Pptx (6),

    /**
     * PDF. The entire image is saved as a picture, with recognized text put under the image.
     */
    PdfSearchable (7),

    /**
     * PDF. The recognized text is saved as text, and the pictures are embedded as images.
     */
    PdfTextAndImages (8),

    /**
     * PDF/A-1b. The file is saved in PDF/A-1b-compliant format, with the entire image saved
     * as a picture and recognized text put under it.
     */
    PdfA (9),

    /**
     * XML. All coordinates are saved relative to the original image.
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/xml-scheme-recognized-document">
     * Output XML Document</a> for the description of tags. If you select this export format, barcodes
     * are recognized on the image and saved to output XML no matter which profile is used for recognition.
     */
    Xml (10),

    /**
     * XML. All coordinates are saved relative to the image after geometry correction.
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/xml-scheme-recognized-document">
     * Output XML Document</a> for the description of tags. If you select this export format, barcodes
     * are recognized on the image and saved to output XML no matter which profile is used for recognition.
     */
    XmlForCorrectedImage (11),

    /**
     * ALTO
     */
    Alto (12);

    private int value;

    ExportFormat(int value) {
        this.value = value;
    }
}
