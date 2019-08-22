package abbyy.cloudsdk.v2.client.http;

import abbyy.cloudsdk.v2.client.Serializer;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpAsyncRequest<T> {
    private HttpRequestMethod method;
    private String requestUri;
    private Map<String, String> requestProperties = new HashMap<>();
    private HttpAsyncFileWrapper fileWrapper;

    public HttpRequestMethod getMethod() {
        return method;
    }

    public void setMethod(HttpRequestMethod method) {
        this.method = method;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public Map<String, String> getRequestProperties() {
        return requestProperties;
    }

    public void setRequestProperties(Map<String, String> requestProperties) {
        this.requestProperties = requestProperties;
    }

    public HttpAsyncFileWrapper getFileWrapper() {
        return fileWrapper;
    }

    public void setFileWrapper(HttpAsyncFileWrapper fileWrapper) {
        this.fileWrapper = fileWrapper;
    }

    public static <T> HttpAsyncRequest<T> buildRequest(HttpRequestMethod method, String requestUrl, Object params, FileInputStream fileStream, String fileName) {
        HttpAsyncRequest<T> request = new HttpAsyncRequest<>();
        request.setMethod(method);
        request.setRequestUri(buildRequestUri(requestUrl, params));
        request.getRequestProperties().put("Accept", "application/json");

        if (fileName != null) {
            HttpAsyncFileWrapper fileWrapper = new HttpAsyncFileWrapper(fileStream, fileName);
            request.setFileWrapper(fileWrapper);
        }

        return request;
    }

    private static String buildRequestUri(String requestUrl, Object params) {
        return "/" + requestUrl + "?" + Serializer.toQueryString(params);
    }
}
