package abbyy.cloudsdk.v2.client.models.requestparams;

import abbyy.cloudsdk.v2.client.models.TaskInfo;

import java.util.UUID;

/**
 * Parameters for Image Submitting request
 */
public final class ImageSubmittingParams extends RequestParams<TaskInfo> {
    /**
     * Optional. Specifies the identifier of the task.
     * If the task with the specified identifier does not exist or has been deleted, an error is returned.
     */
    private UUID taskId;

    /**
     * Contains a password for accessing password-protected images in PDF format.
     */
    private String pdfPassword;

    public ImageSubmittingParams() {
        super(TaskInfo.class);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getPdfPassword() {
        return pdfPassword;
    }

    public void setPdfPassword(String pdfPassword) {
        this.pdfPassword = pdfPassword;
    }
}
