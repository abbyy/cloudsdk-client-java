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

package com.ocrsdk.abbyy.v2.client;

import com.ocrsdk.abbyy.v2.client.models.Error;

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
