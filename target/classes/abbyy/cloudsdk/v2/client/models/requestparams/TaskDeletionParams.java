package abbyy.cloudsdk.v2.client.models.requestparams;

import abbyy.cloudsdk.v2.client.models.TaskList;

import java.util.UUID;

/**
 * Parameters for Task Deletion request
 */
public final class TaskDeletionParams extends RequestParams<TaskList> {
    /**
     * Required. Specifies the identifier of the task. If the task with the specified identifier does not exist, an error is returned.
     */
    private UUID taskId;

    public TaskDeletionParams() {
        super(TaskList.class);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
}
