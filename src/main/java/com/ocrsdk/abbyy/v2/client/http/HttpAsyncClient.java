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

import com.ocrsdk.abbyy.v2.client.models.AuthInfo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class HttpAsyncClient {
    private String host;
    private String applicationId;
    private String password;

    public HttpAsyncClient(AuthInfo authInfo) {
        this.host = authInfo.getHost();
        this.applicationId = authInfo.getApplicationId();
        this.password = authInfo.getPassword();
    }

    public <T> CompletableFuture<HttpAsyncResponse<T>> sendRequest(HttpAsyncRequest<T> request) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                URL url = new URL(host + request.getRequestUri());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                HttpRequestMethod method = request.getMethod();
                connection.setRequestMethod(method.getValue());
                addAuthenticationProperty(connection);
                request.getRequestProperties().forEach(connection::setRequestProperty);

                if (method == HttpRequestMethod.Post) {
                    HttpAsyncFileWrapper fileWrapper = request.getFileWrapper();
                    connection.setDoOutput(true);
                    if (fileWrapper != null) {
                        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + fileWrapper.getBoundary());
                        fileWrapper.transferFileTo(connection.getOutputStream());
                    }
                    else {
                        connection.getOutputStream().flush();
                        connection.getOutputStream().close();
                    }
                }
                HttpAsyncResponse<T> response = HttpAsyncResponse.buildResponse(connection);
                connection.disconnect();
                return response;
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    private void addAuthenticationProperty(HttpURLConnection connection) {
        String encoded = Base64.getEncoder().encodeToString((applicationId + ":" + password).getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encoded);
    }
}
