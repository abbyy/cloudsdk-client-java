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
 * Specifies the source of the image. It can be either a scanned image,
 * or a photograph created with a digital camera. Special preprocessing
 * operations can be performed with the image depending on the selected
 * source. For example, the system can automatically correct distorted
 * text lines, poor focus and lighting on photos.
 */
public enum ImageSource {
    /**
     * The image source is detected automatically.
     */
    Auto (1),

    /**
     * Photo
     */
    Photo (2),

    /**
     * Scanner
     */
    Scanner (3);

    private int value;

    ImageSource(int value) {
        this.value = value;
    }
}
