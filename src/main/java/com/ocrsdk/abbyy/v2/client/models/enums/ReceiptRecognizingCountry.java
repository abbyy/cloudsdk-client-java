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
