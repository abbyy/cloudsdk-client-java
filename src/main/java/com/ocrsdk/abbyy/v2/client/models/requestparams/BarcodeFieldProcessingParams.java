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

package com.ocrsdk.abbyy.v2.client.models.requestparams;

import com.ocrsdk.abbyy.v2.client.models.TaskInfo;
import com.ocrsdk.abbyy.v2.client.models.enums.BarcodeType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parameters for Barcode Field Processing
 */
public final class BarcodeFieldProcessingParams extends RequestParams<TaskInfo> {
    /**
     * Optional. Contains a password for accessing password-protected images in PDF format.
     */
    private String pdfPassword;

    /**
     * Optional. Contains the description of the processing task. Cannot contain more than 255 characters.
     */
    private String description;

    /**
     * Optional. Default "-1,-1,-1,-1". Specifies the region of the text field on the image. The coordinates of the region
     * are measured in pixels relative to the left top corner of the image and are specified in the following order:
     * left, top, right, bottom. By default, the region of the whole image is used.
     */
    private String region;

    /**
     * Optional. Default is Autodetect. Specifies the type of the barcode.
     * This parameter may also contain several barcode types.
     */
    @JsonProperty("barcodeType")
    private BarcodeType[] barcodeTypes;

    /**
     * Optional. Default "false". This parameter makes sense only for {@link BarcodeType#Pdf417}
     * and {@link BarcodeType#Aztec} barcodes, which encode some binary data.
     * If this parameter is set to true, the binary data encoded in a barcode are saved as a sequence of hexadecimal
     * values for corresponding bytes.
     */
    private Boolean containsBinaryData;

    public BarcodeFieldProcessingParams() {
        super(TaskInfo.class);
    }

    public String getPdfPassword() {
        return pdfPassword;
    }

    public void setPdfPassword(String pdfPassword) {
        this.pdfPassword = pdfPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BarcodeType[] getBarcodeTypes() {
        return barcodeTypes;
    }

    public void setBarcodeTypes(BarcodeType[] barcodeTypes) {
        this.barcodeTypes = barcodeTypes;
    }

    public Boolean getContainsBinaryData() {
        return containsBinaryData;
    }

    public void setContainsBinaryData(Boolean containsBinaryData) {
        this.containsBinaryData = containsBinaryData;
    }
}
