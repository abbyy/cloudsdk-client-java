package abbyy.cloudsdk.v2.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error container
 */
public final class Error {
    /**
     * Error object
     */
    @JsonProperty("error")
    private ErrorData errorData;

    public ErrorData getErrorData() {
        return errorData;
    }

    public void setErrorData(ErrorData errorData) {
        this.errorData = errorData;
    }

    public static Error fromText(String error) {
        Error newError = new Error();
        ErrorData newErrorData = new ErrorData();
        newErrorData.setMessage(error);
        newError.setErrorData(newErrorData);
        return newError;
    }
}
