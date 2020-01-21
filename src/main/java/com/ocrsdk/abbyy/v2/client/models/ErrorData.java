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

/**
 * Describes the error details
 */
public final class ErrorData {
    /**
     * The code of the error
     */
    private String code;

    /**
     * The message describing the error
     */
    private String message;

    /**
     * The description of the error occurence location
     */
    private String target;

    /**
     * Describes validation error
     */
    private ErrorData[] details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ErrorData[] getDetails() {
        return details;
    }

    public void setDetails(ErrorData[] details) {
        this.details = details;
    }
}
