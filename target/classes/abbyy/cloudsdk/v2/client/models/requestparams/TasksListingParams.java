package abbyy.cloudsdk.v2.client.models.requestparams;

import abbyy.cloudsdk.v2.client.models.TaskList;

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
