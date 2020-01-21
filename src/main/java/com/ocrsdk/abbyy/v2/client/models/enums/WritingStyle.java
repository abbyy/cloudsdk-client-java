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
 * Provides additional information about handprinted
 * letters writing style.
 */
public enum WritingStyle {
    Default (0),
    American (1),
    German (2),
    Russian (3),
    Polish (4),
    Thai (5),
    Japanese (6),
    Arabic (7),
    Baltic (8),
    British (9),
    Bulgarian (10),
    Canadian (11),
    Czech (12),
    Croatian (13),
    French (14),
    Greek (15),
    Hungarian (16),
    Italian (17),
    Romanian (18),
    Slovak (19),
    Spanish (20),
    Turkish (21),
    Ukrainian (22),
    Common (23),
    Chinese (24),
    Azerbaijan (25),
    Kazakh (26),
    Kirgiz (27),
    Latvian (28);

    private int value;

    WritingStyle(int value) {
        this.value = value;
    }
}
