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
 * Specifies the type of the checkmark.
 */
public enum CheckmarkType {
    /**
     * Checkmark against an empty background
     */
    Empty (1),

    /**
     * Checkmark in a circle
     */
    Circle (2),

    /**
     * Checkmark in a square
     */
    Square (3);

    private int value;

    CheckmarkType(int value) {
        this.value = value;
    }
}
