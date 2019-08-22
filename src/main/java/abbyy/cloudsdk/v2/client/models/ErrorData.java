package abbyy.cloudsdk.v2.client.models;

/**
 * Describes the error details
 */
public final class ErrorData {
    /**
     * The code of the error
     */
    private String code;

    /**
     * The message describing the error
     */
    private String message;

    /**
     * The description of the error occurence location
     */
    private String target;

    /**
     * Describes validation error
     */
    private ErrorData[] details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ErrorData[] getDetails() {
        return details;
    }

    public void setDetails(ErrorData[] details) {
        this.details = details;
    }
}
