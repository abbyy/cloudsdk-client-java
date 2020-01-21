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

package com.ocrsdk.abbyy.v2.client.models.requestparams;

import com.ocrsdk.abbyy.v2.client.models.TaskInfo;

import java.util.UUID;

/**
 * Parameters for Image Submitting request
 */
public final class ImageSubmittingParams extends RequestParams<TaskInfo> {
    /**
     * Optional. Specifies the identifier of the task.
     * If the task with the specified identifier does not exist or has been deleted, an error is returned.
     */
    private UUID taskId;

    /**
     * Contains a password for accessing password-protected images in PDF format.
     */
    private String pdfPassword;

    public ImageSubmittingParams() {
        super(TaskInfo.class);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getPdfPassword() {
        return pdfPassword;
    }

    public void setPdfPassword(String pdfPassword) {
        this.pdfPassword = pdfPassword;
    }
}
