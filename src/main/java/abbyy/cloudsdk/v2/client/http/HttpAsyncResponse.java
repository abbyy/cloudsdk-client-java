package abbyy.cloudsdk.v2.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpAsyncResponse<T> {
    public static final int STATUS_CODE_OK = 200;

    private Map<String, Iterable<String>> headers = new HashMap<>();
    private int statusCode;
    private String content;

    public Map<String, Iterable<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Iterable<String>> headers) {
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static <T> HttpAsyncResponse<T> buildResponse(HttpURLConnection connection) throws IOException {
        HttpAsyncResponse<T> response = new HttpAsyncResponse<>();
        int statusCode = connection.getResponseCode();
        response.setStatusCode(statusCode);
        connection.getHeaderFields().forEach((a,b) -> response.getHeaders().put(a,b));


        BufferedReader in = new BufferedReader(new InputStreamReader(
                statusCode == STATUS_CODE_OK ? connection.getInputStream() : connection.getErrorStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        response.setContent(content.toString());

        return response;
    }
}
