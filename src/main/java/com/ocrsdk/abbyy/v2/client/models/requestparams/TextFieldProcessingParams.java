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
import com.ocrsdk.abbyy.v2.client.models.enums.MarkingType;
import com.ocrsdk.abbyy.v2.client.models.enums.TextType;
import com.ocrsdk.abbyy.v2.client.models.enums.WritingStyle;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parameters for Text Field Processing request.
 */
public final class TextFieldProcessingParams extends RequestParams<TaskInfo> {
    /**
     * Optional. Contains a password for accessing password-protected images in PDF format.
     */
    private String pdfPassword;

    /**
     * Optional. Contains the description of the processing task. Cannot contain more than 255 characters.
     */
    private String description;

    /**
     * Optional. Default "-1,-1,-1,-1". Specifies the region of the text field on the image.
     * The coordinates of the region are measured in pixels relative to the left top corner of the image and
     * are specified in the following order: left, top, right, bottom. By default, the region of the whole image is used.
     */
    private String region;

    /**
     * Optional. Default "English". Specifies recognition language of the document.
     * This parameter can contain several language names separated with commas, for example
     * "English,French,German".
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/recognition-languages/"/>
     */
    private String language;

    /**
     * Optional. Default "". Specifies the letter set, which should be used during recognition.
     * Contains a string with the letter set characters. For example, "ABCDabcd'-.".
     * By default, the letter set of the language, specified in the {@link #language} parameter, is used.
     */
    private String letterSet;

    /**
     * Optional. Default "". Specifies the regular expression which defines which words are allowed in the field
     * and which are not. By default, the set of allowed words is defined by the dictionary of the language,
     * specified in the language parameter.
     *
     * <b>Note:</b> See the <a href="https://www.ocrsdk.com/documentation/specifications/regular-expressions/"> description of regular expressions</a>.
     * Note that regular expressions do not strictly limit the set of characters of the output result,
     * i.e. the recognized value may contain characters which are not included into the regular expression.
     * During recognition all hypotheses of a word recognition are checked against the specified regular expression.
     * If a given recognition variant conforms to the expression, it has higher probability of being selected
     * as final recognition output. But if there is no variant that matches regular expression,
     * the result will not conform to the expression. If you want to limit the set of characters, which can be recognized,
     * the best way to do it is to use letterSet parameter.
     */
    private String regExp;

    /**
     * Optional. Default is {@link TextType#Normal}. Specifies the type of the text on a page.
     */
    @JsonProperty("textType")
    private TextType[] textTypes;

    /**
     * Optional. Default "false". Specifies whether the field contains only one text line.
     * The value should be true, if there is one text line in the field; otherwise it should be false.
     */
    private Boolean oneTextLine;

    /**
     * Optional. Default "false". Specifies whether the field contains only one word in each text line.
     * The value should be true, if no text line contains more than one word (so the lines of text will be recognized
     * as a single word); otherwise it should be false.
     */
    private Boolean oneWordPerTextLine;

    /**
     * Optional. Default is {@link MarkingType#SimpleText}. This property is valid only
     * for the {@link TextType#Handprinted} recognition. Specifies the type of marking around letters
     * (for example, underline, frame, box, etc.). By default, there is no marking around letters.
     */
    private MarkingType markingType;

    /**
     * Optional. Default "1". Specifies the number of character cells for the field.
     * This property has a sense only for the field marking types(the markingType parameter) that imply splitting the text in cells.
     * Default value for this property is 1, but you should set the appropriate value to recognize the text correctly.
     */
    private Integer placeholdersCount;

    /**
     * Optional. Default "default". Provides additional information about handprinted letters writing style.
     */
    private WritingStyle writingStyle;

    public TextFieldProcessingParams() {
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLetterSet() {
        return letterSet;
    }

    public void setLetterSet(String letterSet) {
        this.letterSet = letterSet;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public TextType[] getTextTypes() {
        return textTypes;
    }

    public void setTextTypes(TextType[] textTypes) {
        this.textTypes = textTypes;
    }

    public Boolean getOneTextLine() {
        return oneTextLine;
    }

    public void setOneTextLine(Boolean oneTextLine) {
        this.oneTextLine = oneTextLine;
    }

    public Boolean getOneWordPerTextLine() {
        return oneWordPerTextLine;
    }

    public void setOneWordPerTextLine(Boolean oneWordPerTextLine) {
        this.oneWordPerTextLine = oneWordPerTextLine;
    }

    public MarkingType getMarkingType() {
        return markingType;
    }

    public void setMarkingType(MarkingType markingType) {
        this.markingType = markingType;
    }

    public Integer getPlaceholdersCount() {
        return placeholdersCount;
    }

    public void setPlaceholdersCount(Integer placeholdersCount) {
        this.placeholdersCount = placeholdersCount;
    }

    public WritingStyle getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(WritingStyle writingStyle) {
        this.writingStyle = writingStyle;
    }
}
