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
 * Types of text supported by ABBYY Cloud OCR SDK.
 * Several types may be provided at once
 *
 * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/text-types/"/>
 */
public enum TextType {
    /**
     * Common typographic type of text.
     */
    Normal (1),

    /**
     * The text is typed on a typewriter.
     */
    Typewriter (2),

    /**
     * The text is printed on a dot-matrix printer.
     */
    Matrix (3),

    /**
     * A special set of characters including only
     * digits written in ZIP-code style.
     */
    Index (4),

    /**
     * Handprinted text.
     */
    Handprinted (5),

    /**
     * A monospaced font, designed for Optical Character
     * Recognition. Largely used by banks, credit card
     * companies and similar businesses.
     */
    OcrA (6),

    /**
     * A font designed for Optical Character Recognition.
     */
    OcrB (7),

    /**
     * A special set of characters including only digits and
     * A, B, C, D characters printed in magnetic ink.
     * MICR (Magnetic Ink Character Recognition) characters
     * are found in a variety of places, including personal
     * checks.
     */
    E13b (8),

    /**
     * A special set of characters, which includes only digits
     * and A, B, C, D, E characters, written in MICR barcode
     * font (CMC-7).
     */
    Cmc7 (9),

    /**
     * Text printed in Gothic type.
     */
    Gothic (10);

    private int value;

    TextType(int value) {
        this.value = value;
    }
}
