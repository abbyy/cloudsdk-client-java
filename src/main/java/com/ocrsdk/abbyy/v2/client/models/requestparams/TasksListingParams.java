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

import com.ocrsdk.abbyy.v2.client.models.TaskList;

import java.util.Date;

/**
 * Parameters for Tasks Listing request
 */
public final class TasksListingParams extends RequestParams<TaskList> {
    /**
     * Optional. Default is the current date minus 7 days. Specifies the date to list tasks from.
     */
    private Date fromDate;

    /**
     * Optional. Default is the current date. Specifies the date to list tasks to.
     */
    private Date toDate;

    /**
     * Optional. Default is "false". Specifies if the tasks that have already been deleted must be excluded from the listing.
     */
    private Boolean excludeDeleted;

    public TasksListingParams() {
        super(TaskList.class);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Boolean getExcludeDeleted() {
        return excludeDeleted;
    }

    public void setExcludeDeleted(Boolean excludeDeleted) {
        this.excludeDeleted = excludeDeleted;
    }


}
