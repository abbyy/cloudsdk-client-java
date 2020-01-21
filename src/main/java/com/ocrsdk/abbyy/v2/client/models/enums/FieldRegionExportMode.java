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
