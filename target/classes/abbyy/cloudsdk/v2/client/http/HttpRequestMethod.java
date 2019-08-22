package abbyy.cloudsdk.v2.client.http;

public enum HttpRequestMethod {
    Get ("GET"),
    Post ("POST");

    private String value;

    HttpRequestMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
