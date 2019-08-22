package abbyy.cloudsdk.v2.client.models;

import java.util.List;

/**
 * Describes the response with the list
 * of tasks created by your application
 */
public class TaskList {
    /**
     * The list of tasks created by your application
     */
    private List<TaskInfo> tasks;

    public List<TaskInfo> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskInfo> tasks) {
        this.tasks = tasks;
    }
}
