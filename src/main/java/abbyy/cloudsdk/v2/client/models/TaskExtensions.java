package abbyy.cloudsdk.v2.client.models;

public class TaskExtensions {
    public static boolean isInProcess(TaskInfo taskInfo) {
        switch (taskInfo.getStatus()) {
            case Queued:
            case InProgress:
                return true;
            default:
                return false;
        }
    }
}
