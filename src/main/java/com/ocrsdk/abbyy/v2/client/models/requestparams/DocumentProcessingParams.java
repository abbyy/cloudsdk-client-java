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
import com.ocrsdk.abbyy.v2.client.models.enums.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Parameters for Document Processing request
 */
public final class DocumentProcessingParams extends RequestParams<TaskInfo> {
    /**
     * Required. Specifies the identifier of the task. If the task with the
     * specified identifier does not exist or has been deleted, an error is
     * returned.
     */
    private UUID taskId;

    /**
     * Optional. Contains the description of the processing task. Cannot
     * contain more than 255 characters.
     */
    private String description;

    /**
     * Optional. Default is {@link ExportFormat#Rtf}. Specifies the export format.
     */
    @JsonProperty("exportFormat")
    private ExportFormat[] exportFormats;

    /**
     *  Optional. Default is {@link ProcessingProfile#DocumentConversion}. Specifies a profile with predefined processing settings.
     */
    private ProcessingProfile profile;

    /**
     * Optional. Default is {@link TextType#Normal}. Specifies the type of the text on a page.
     */
    @JsonProperty("textType")
    private TextType[] textTypes;

    /**
     * Optional. Default "English". Specifies recognition language of the document.
     * This parameter can contain several language names separated with commas, for example
     * "English,French,German".
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/recognition-languages/"/>
     */
    private String language;

    /**
     * Optional. Default is {@link ImageSource#Auto}. Specifies the source of the image.
     */
    private ImageSource imageSource;

    /**
     * Optional. Default "true". Specifies whether the orientation of the image should be automatically detected and corrected.
     * <ul>
     *   <li><b>true</b></li> The page orientation is automatically detected, and if it differs from normal the image is rotated.
     *   <li><b>false</b></li> The page orientation detection and correction is not performed.
     * </ul>
     */
    private Boolean correctOrientation;

    /**
     * Optional. Default "true". Specifies whether the skew of the image should be automatically detected and corrected.
     */
    private Boolean correctSkew;

    /**
     * Optional. Default is {@link WriteTags#Auto}. Specifies whether the result must be written as tagged PDF.
     * This parameter can be used only if the {@link ExportFormat} parameter contains one of the
     * values for export to PDF.
     */
    @JsonProperty("pdf:writeTags")
    private WriteTags writeTags;

    /**
     * Optional. Default "false". Specifies whether the variants of characters recognition
     * should be written to an output file in XML format. This parameter can be used only
     * if the {@link ExportFormat} parameter contains xml or xmlForCorrectedImage value.
     */
    @JsonProperty("xml:writeRecognitionVariants")
    private Boolean writeRecognitionVariants;

    /**
     * Optional. Default "false". Specifies whether the paragraph and character styles
     * should be written to an output file in XML format. This parameter can be
     * used only if the {@link ExportFormat} parameter contains xml or
     * xmlForCorrectedImage value.
     */
    @JsonProperty("xml:writeFormatting")
    private Boolean writeFormatting;

    /**
     * Optional. Default "true" for xml export format and "false" in other cases.
     * Specifies whether barcodes must be detected on the image, recognized and exported
     * to the result file.
     */
    private Boolean readBarcodes;

    public DocumentProcessingParams() {
        super(TaskInfo.class);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExportFormat[] getExportFormats() {
        return exportFormats;
    }

    public void setExportFormats(ExportFormat[] exportFormats) {
        this.exportFormats = exportFormats;
    }

    public ProcessingProfile getProfile() {
        return profile;
    }

    public void setProfile(ProcessingProfile profile) {
        this.profile = profile;
    }

    public TextType[] getTextTypes() {
        return textTypes;
    }

    public void setTextTypes(TextType[] textTypes) {
        this.textTypes = textTypes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ImageSource getImageSource() {
        return imageSource;
    }

    public void setImageSource(ImageSource imageSource) {
        this.imageSource = imageSource;
    }

    public Boolean getCorrectOrientation() {
        return correctOrientation;
    }

    public void setCorrectOrientation(Boolean correctOrientation) {
        this.correctOrientation = correctOrientation;
    }

    public Boolean getCorrectSkew() {
        return correctSkew;
    }

    public void setCorrectSkew(Boolean correctSkew) {
        this.correctSkew = correctSkew;
    }

    public WriteTags getWriteTags() {
        return writeTags;
    }

    public void setWriteTags(WriteTags writeTags) {
        this.writeTags = writeTags;
    }

    public Boolean getWriteRecognitionVariants() {
        return writeRecognitionVariants;
    }

    public void setWriteRecognitionVariants(Boolean writeRecognitionVariants) {
        this.writeRecognitionVariants = writeRecognitionVariants;
    }

    public Boolean getWriteFormatting() {
        return writeFormatting;
    }

    public void setWriteFormatting(Boolean writeFormatting) {
        this.writeFormatting = writeFormatting;
    }

    public Boolean getReadBarcodes() {
        return readBarcodes;
    }

    public void setReadBarcodes(Boolean readBarcodes) {
        this.readBarcodes = readBarcodes;
    }
}
