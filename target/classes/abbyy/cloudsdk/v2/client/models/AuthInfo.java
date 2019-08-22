package abbyy.cloudsdk.v2.client.models;

/**
 * Connection configuration info
 */
public final class AuthInfo {
    /**
     * OCR SDK Host
     */
    private String host;

    /**
     * Application Id
     */
    private String applicationId;

    /**
     * Password
     */
    private String password;

    public AuthInfo(String host, String applicationId, String password) {
        this.host = host;
        this.applicationId = applicationId;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getPassword() {
        return password;
    }

}
