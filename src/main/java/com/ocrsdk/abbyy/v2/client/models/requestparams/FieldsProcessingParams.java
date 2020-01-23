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
 * Parameters for Fields Processing request
 */
public final class FieldsProcessingParams extends RequestParams<TaskInfo> {
    /**
     * Required. Specifies the identifier of the task. If the task with the specified identifier does not exist or
     * has been deleted, an error is returned.
     */
    private UUID taskId;

    /**
     * Optional. Contains the description of the processing task. Cannot contain more than 255 characters.
     */
    private String description;

    /**
     * Optional. Default "false". Specifies whether the recognition variants should be written to the result.
     * If you set this value to true, additional recognition variants (charRecVariants) appear in the XML result file.
     */
    private Boolean writeRecognitionVariants;

    public FieldsProcessingParams() {
        super(TaskInfo.class);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWriteRecognitionVariants() {
        return writeRecognitionVariants;
    }

    public void setWriteRecognitionVariants(Boolean writeRecognitionVariants) {
        this.writeRecognitionVariants = writeRecognitionVariants;
    }
}
