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

package com.ocrsdk.abbyy.v2.client.http;

import com.ocrsdk.abbyy.v2.client.Serializer;

import java.io.InputStream;
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

    public static <T> HttpAsyncRequest<T> buildRequest(HttpRequestMethod method, String requestUrl, Object params, InputStream fileStream, String fileName) {
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
