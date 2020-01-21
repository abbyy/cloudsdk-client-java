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

import com.ocrsdk.abbyy.v2.client.IOcrClient;
import com.ocrsdk.abbyy.v2.client.models.enums.TaskStatus;
import com.ocrsdk.abbyy.v2.client.models.requestparams.TasksListingParams;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class TaskInfo {
    /**
     * Task identifier
     */
    private UUID taskId;

    /**
     * Task creation time
     */
    private Date registrationTime;

    /**
     * Last Task modification time
     */
    private Date statusChangeTime;

    /**
     * The task can have one of the following statuses
     */
    private TaskStatus status;

    /**
     * Description of the processing error. Specified only with
     * ProcessingFailed Task status
     */
    private String error;

    /**
     * Number of files added to a Task
     */
    private int filesCount;

    /**
     * Recommended delay before request for new Task Status in milliseconds
     */
    private int requestStatusDelay;

    /**
     * The hyperlink collection with recognition results.
     * The links have limited lifetime. If you want to download the
     * result after the time has passed, call the
     * {@link IOcrClient#getTaskStatusAsync(UUID)}
     * or {@link IOcrClient#listTasksAsync(TasksListingParams)} method
     * to obtain the new hyperlink collection.
     */
    private List<String> resultUrls;

    /**
     * Task description specified when the Task is created
     */
    private String description;

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Date statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public int getRequestStatusDelay() {
        return requestStatusDelay;
    }

    public void setRequestStatusDelay(int requestStatusDelay) {
        this.requestStatusDelay = requestStatusDelay;
    }

    public List<String> getResultUrls() {
        return resultUrls;
    }

    public void setResultUrls(List<String> resultUrls) {
        this.resultUrls = resultUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
