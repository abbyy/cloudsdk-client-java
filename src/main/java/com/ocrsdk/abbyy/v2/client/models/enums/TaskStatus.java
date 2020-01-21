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

package com.ocrsdk.abbyy.v2.client.models.enums;

import com.ocrsdk.abbyy.v2.client.IOcrClient;

import java.util.UUID;

/**
 * The task can have one of the following statuses
 */
public enum TaskStatus {
    /**
     * The task has been registered in the system, but has not yet been passed for processing.
     */
    Submitted (1),

    /**
     * The task has been placed in the processing queue and is waiting to be processed.
     */
    Queued (2),

    /**
     * The task is being processed.
     */
    InProgress (3),

    /**
     * The task has been processed successfully. For a task with this status, the URL for
     * downloading the result of processing is available in the response.
     */
    Completed (4),

    /**
     * The task has not been processed because an error occurred. You can find the description of the
     * error in the XML response.
     *
     * <b>Note:</b> We do not recommend sending the same file for processing repeatedly, if the first task
     * failed. However, if you have created several tasks for the same file, and all of them have
     * failed, the fullest and most specific error description can be received by calling
     * {@link IOcrClient#getTaskStatusAsync(UUID)} for the first of those tasks.
     */
    ProcessingFailed (5),

    /**
     * The task has been deleted.
     */
    Deleted (6),

    /**
     * You do not have enough money on your account to process the task.
     */
    NotEnoughCredits (7);

    private int value;

    TaskStatus(int value) {
        this.value = value;
    }
}
