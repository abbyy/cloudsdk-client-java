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

package com.ocrsdk.abbyy.v2.client.models;

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
