package abbyy.cloudsdk.v2.client;

import abbyy.cloudsdk.v2.client.models.Error;

import java.util.Map;

/**
 * Describes the API related error
 */
public final class ApiException extends RuntimeException {
    /**
     * HTTP Status code returned by a server
     */
    private int statusCode;

    /**
     * Details about an error
     */
    private Error error;

    /**
     * Response headers
     */
    private Map<String, Iterable<String>> headers;

    /**
     * Instantiates the {@link ApiException}
     * @param message Exception message
     * @param statusCode HTTP Status code returned by a server
     * @param error Details about an error
     * @param headers Response headers
     * @param exception Actual exception
     */
    public ApiException(String message, int statusCode, Error error,
                        Map<String, Iterable<String>> headers, Exception exception) {
        super(message, exception);
        this.statusCode = statusCode;
        this.error = error;
        this.headers = headers;
    }

    /**
     * Instantiates the {@link ApiException}
     * @param message Exception message
     * @param statusCode HTTP Status code returned by a server
     * @param error Details about an error
     * @param headers Response headers
     */
    public ApiException(String message, int statusCode, Error error,
                        Map<String, Iterable<String>> headers) {
        this(message, statusCode, error, headers, null);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Error getError() {
        return error;
    }

    public Map<String, Iterable<String>> getHeaders() {
        return headers;
    }
}
