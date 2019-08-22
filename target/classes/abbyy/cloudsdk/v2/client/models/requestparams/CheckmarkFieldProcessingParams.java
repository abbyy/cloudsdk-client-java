package abbyy.cloudsdk.v2.client.models.requestparams;

import abbyy.cloudsdk.v2.client.models.TaskInfo;
import abbyy.cloudsdk.v2.client.models.enums.CheckmarkType;

/**
 * Parameters for Checkmark Field Processing request
 */
public final class CheckmarkFieldProcessingParams extends RequestParams<TaskInfo> {
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
     * Optional. Default is {@link CheckmarkType#Empty}. Specifies the type of the checkmark.
     */
    private CheckmarkType checkmarkType;

    /**
     * Optional. Default "false". This property set to true means that checkmark block can be selected and then corrected.
     */
    private Boolean correctionAllowed;

    public CheckmarkFieldProcessingParams() {
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

    public CheckmarkType getCheckmarkType() {
        return checkmarkType;
    }

    public void setCheckmarkType(CheckmarkType checkmarkType) {
        this.checkmarkType = checkmarkType;
    }

    public Boolean getCorrectionAllowed() {
        return correctionAllowed;
    }

    public void setCorrectionAllowed(Boolean correctionAllowed) {
        this.correctionAllowed = correctionAllowed;
    }
}
