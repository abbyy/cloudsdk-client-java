package abbyy.cloudsdk.v2.client;

import abbyy.cloudsdk.client.models.*;
import abbyy.cloudsdk.client.models.enums.*;
import abbyy.cloudsdk.client.models.requestparams.*;
import abbyy.cloudsdk.v2.client.models.Application;
import abbyy.cloudsdk.v2.client.models.AuthInfo;
import abbyy.cloudsdk.v2.client.models.TaskInfo;
import abbyy.cloudsdk.v2.client.models.TaskList;
import abbyy.cloudsdk.v2.client.models.enums.BusinessCardExportFormat;
import abbyy.cloudsdk.v2.client.models.enums.ExportFormat;
import abbyy.cloudsdk.v2.client.models.enums.TaskStatus;
import abbyy.cloudsdk.v2.client.models.requestparams.*;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ClientTest {
    private static final String HOST = "https://cloud-eu.ocrsdk.com";

    private static final String APPLICATION_ID = "YOUR_APP_ID";
    private static final String PASSWORD = "YOUR_APP_PWD";

    private static final String BARCODE_FILEPATH = "";
    private static final String BUSINESS_CARD_FILEPATH = "";
    private static final String CHECKMARK_FILEPATH = "";
    private static final String DOCUMENT_IMAGE_FILEPATH = "";
    private static final String IMAGE_FILEPATH = "";
    private static final String MRZ_FILEPATH = "";
    private static final String RECEIPT_FILEPATH = "";
    private static final String TEXTFIELD_FILEPATH = "";

    private static final String UNSUPPORTED_FORMAT_FILEPATH = "";


    private IOcrClient ocrClient;

    private Comparator<TaskInfo> taskInfoComparator;

    @Before
    public void init() {
        AuthInfo authInfo = new AuthInfo(HOST, APPLICATION_ID, PASSWORD);
        ocrClient = new OcrClient(authInfo);

        taskInfoComparator = Comparator.comparing(TaskInfo::getTaskId);
    }

    @Test
    public void processImageTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(IMAGE_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating image processing task
            ImageProcessingParams imageProcessingParams = new ImageProcessingParams();
            imageProcessingParams.setDescription("Test image");
            imageProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.Docx, ExportFormat.Txt});
            imageProcessingParams.setLanguage("English,French");

            CompletableFuture<TaskInfo> imageProcessingTask = ocrClient.processImageAsync(imageProcessingParams, fileStream, file.getName(), true);
            TaskInfo imageProcessingTaskInfo = imageProcessingTask.get();
            Assert.assertNotNull(imageProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, imageProcessingTaskInfo.getStatus());
            Assert.assertEquals(2, imageProcessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(imageProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, imageProcessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(imageProcessingTaskInfo.getTaskId());
            TaskInfo imageProcessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(imageProcessingTaskInfo, imageProcessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, imageProcessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, imageProcessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processDocumentTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(DOCUMENT_IMAGE_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // submitting image
            ImageSubmittingParams imageSubmittingParams = new ImageSubmittingParams();
            CompletableFuture<TaskInfo> documentSubmittingTask = ocrClient.submitImageAsync(imageSubmittingParams, fileStream, file.getName());
            TaskInfo documentProcessingTaskInfo = documentSubmittingTask.get();
            Assert.assertNotNull(documentProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Submitted, documentProcessingTaskInfo.getStatus());

            // processing document
            DocumentProcessingParams documentProcessingParams = new DocumentProcessingParams();
            documentProcessingParams.setTaskId(documentProcessingTaskInfo.getTaskId());

            CompletableFuture<TaskInfo> documentProcessingTask = ocrClient.processDocumentAsync(documentProcessingParams, true);
            TaskInfo documentProcessingTaskInfo2 = documentProcessingTask.get();

            Assert.assertNotNull(documentProcessingTaskInfo2.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, documentProcessingTaskInfo2.getStatus());
            Assert.assertEquals(1, documentProcessingTaskInfo2.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(documentProcessingTaskInfo2.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, documentProcessingTaskInfo2) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(documentProcessingTaskInfo2.getTaskId());
            TaskInfo documentProcessingTaskInfo3 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(documentProcessingTaskInfo2, documentProcessingTaskInfo3));
            Assert.assertEquals(TaskStatus.Deleted, documentProcessingTaskInfo3.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, documentProcessingTaskInfo2) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processBusinessCardTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(BUSINESS_CARD_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating business card processing task
            BusinessCardProcessingParams businessCardProcessingParams = new BusinessCardProcessingParams();
            businessCardProcessingParams.setDescription("Test business card");
            businessCardProcessingParams.setExportFormats(BusinessCardExportFormat.Xml);

            CompletableFuture<TaskInfo> businessCardProcessingTask = ocrClient.processBusinessCardAsync(businessCardProcessingParams, fileStream, file.getName(), true);
            TaskInfo businessCardProcessingTaskInfo = businessCardProcessingTask.get();

            Assert.assertNotNull(businessCardProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, businessCardProcessingTaskInfo.getStatus());
            Assert.assertEquals(1, businessCardProcessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(businessCardProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, businessCardProcessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(businessCardProcessingTaskInfo.getTaskId());
            TaskInfo businessCardProcessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(businessCardProcessingTaskInfo, businessCardProcessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, businessCardProcessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, businessCardProcessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processTextFieldTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(TEXTFIELD_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating text field processing task
            TextFieldProcessingParams textFieldProcessingParams = new TextFieldProcessingParams();
            textFieldProcessingParams.setDescription("Test textfield");

            CompletableFuture<TaskInfo> textFieldProcessingTask = ocrClient.processTextFieldAsync(textFieldProcessingParams, fileStream, file.getName(), true);
            TaskInfo textFieldProcessingTaskInfo = textFieldProcessingTask.get();

            Assert.assertNotNull(textFieldProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, textFieldProcessingTaskInfo.getStatus());
            Assert.assertEquals(1, textFieldProcessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(textFieldProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, textFieldProcessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(textFieldProcessingTaskInfo.getTaskId());
            TaskInfo textFieldProcessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(textFieldProcessingTaskInfo, textFieldProcessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, textFieldProcessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, textFieldProcessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processBarcodeFieldTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(BARCODE_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating barcode processing task
            BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();
            barcodeFieldProcessingParams.setDescription("Test barcode");

            CompletableFuture<TaskInfo> barcodeProcessingTask = ocrClient.processBarcodeFieldAsync(barcodeFieldProcessingParams, fileStream, file.getName(), true);
            TaskInfo barcodeProcessingTaskInfo = barcodeProcessingTask.get();

            Assert.assertNotNull(barcodeProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, barcodeProcessingTaskInfo.getStatus());
            Assert.assertEquals(1, barcodeProcessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(barcodeProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, barcodeProcessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(barcodeProcessingTaskInfo.getTaskId());
            TaskInfo barcodeProcessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(barcodeProcessingTaskInfo, barcodeProcessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, barcodeProcessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, barcodeProcessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processCheckmarkFieldTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(CHECKMARK_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating checkmark field processing task
            CheckmarkFieldProcessingParams checkmarkFieldProcessingParams = new CheckmarkFieldProcessingParams();
            checkmarkFieldProcessingParams.setDescription("Test checkmark");

            CompletableFuture<TaskInfo> icheckmarkFieldProcessingTask = ocrClient.processCheckmarkFieldAsync(checkmarkFieldProcessingParams, fileStream, file.getName(), true);
            TaskInfo checkmarkFieldProcessingTaskInfo = icheckmarkFieldProcessingTask.get();

            Assert.assertNotNull(checkmarkFieldProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, checkmarkFieldProcessingTaskInfo.getStatus());
            Assert.assertEquals(1, checkmarkFieldProcessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(checkmarkFieldProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, checkmarkFieldProcessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(checkmarkFieldProcessingTaskInfo.getTaskId());
            TaskInfo checkmarkFieldProcessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(checkmarkFieldProcessingTaskInfo, checkmarkFieldProcessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, checkmarkFieldProcessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, checkmarkFieldProcessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processMrzTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(MRZ_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating mrz processing task
            MrzProcessingParams mrzProcessingParams = new MrzProcessingParams();
            mrzProcessingParams.setDescription("Test barcode");

            CompletableFuture<TaskInfo> mrzProcessingTask = ocrClient.processMrzAsync(mrzProcessingParams, fileStream, file.getName(), true);
            TaskInfo mrzProcessingTaskInfo = mrzProcessingTask.get();

            Assert.assertNotNull(mrzProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, mrzProcessingTaskInfo.getStatus());
            Assert.assertEquals(1, mrzProcessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(mrzProcessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, mrzProcessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(mrzProcessingTaskInfo.getTaskId());
            TaskInfo mrzProcessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(mrzProcessingTaskInfo, mrzProcessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, mrzProcessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, mrzProcessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void processReceiptTest() throws InterruptedException, ExecutionException {
        try {
            File file = new File(RECEIPT_FILEPATH);
            FileInputStream fileStream = new FileInputStream(file);

            // creating receipt processing task
            ReceiptProccessingParams receiptProccessingParams = new ReceiptProccessingParams();
            receiptProccessingParams.setDescription("Test receipt");

            CompletableFuture<TaskInfo> receiptProccessingTask = ocrClient.processReceiptAsync(receiptProccessingParams, fileStream, file.getName(), true);
            TaskInfo receiptProccessingTaskInfo = receiptProccessingTask.get();

            Assert.assertNotNull(receiptProccessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, receiptProccessingTaskInfo.getStatus());
            Assert.assertEquals(1, receiptProccessingTaskInfo.getResultUrls().size());

            // checking task status
            CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(receiptProccessingTaskInfo.getTaskId());
            Assert.assertEquals(TaskStatus.Completed, taskStatusTask.get().getStatus());

            // checking task list
            TasksListingParams tasksListingParams = new TasksListingParams();

            CompletableFuture<TaskList> taskListTask = ocrClient.listTasksAsync(tasksListingParams);
            TaskList taskList = taskListTask.get();
            Assert.assertNotNull(taskList.getTasks());
            boolean contains = taskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, receiptProccessingTaskInfo) == 0);
            Assert.assertTrue(contains);

            // deleting task
            CompletableFuture<TaskInfo> taskDeletionTask = ocrClient.deleteTaskAsync(receiptProccessingTaskInfo.getTaskId());
            TaskInfo receiptProccessingTaskInfo2 = taskDeletionTask.get();
            Assert.assertEquals(0, taskInfoComparator.compare(receiptProccessingTaskInfo, receiptProccessingTaskInfo2));
            Assert.assertEquals(TaskStatus.Deleted, receiptProccessingTaskInfo2.getStatus());

            // checking finished task list
            CompletableFuture<TaskList> finishedTaskListTask = ocrClient.listFinishedTaskAsync();
            TaskList finishedTaskList = finishedTaskListTask.get();
            if (finishedTaskList.getTasks() != null) {
                contains = finishedTaskList.getTasks().stream().anyMatch(taskInfo -> taskInfoComparator.compare(taskInfo, receiptProccessingTaskInfo) == 0);
                Assert.assertFalse(contains);
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void applicationInfoTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Application> applicationInfo = ocrClient.getApplicationInfoAsync();
        Application application = applicationInfo.get();
        Assert.assertNotNull(application);
        Assert.assertNotNull(application.getId());
    }


    @Test
    public void invalidTaskIdTest() throws Throwable {
        // Due to Java async-call mechanism, any exception, thrown during asynchronous method is wrapped with ExecutionException
        // So, to obtain original exception, one has to get cause of ExecutionException instance
        try {
            try {
                UUID invalidTaskId = UUID.randomUUID();
                CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(invalidTaskId);
                taskStatusTask.get();
                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.InvalidArgument, ValidationErrorCode.InvalidParameterValue, ErrorTarget.TaskId);
        }
    }

    @Test
    public void nullTaskIdTest() throws Throwable {
        try {
            try {
                CompletableFuture<TaskInfo> taskStatusTask = ocrClient.getTaskStatusAsync(null);
                taskStatusTask.get();
                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.InvalidArgument, ValidationErrorCode.MissingArgument, ErrorTarget.TaskId);
        }
    }

    @Test
    public void invalidRegionTest() throws Throwable {
        try {
            try {
                File file = new File(BARCODE_FILEPATH);
                FileInputStream fileStream = new FileInputStream(file);

                // creating barcode processing task
                BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();
                barcodeFieldProcessingParams.setDescription("Test barcode");
                barcodeFieldProcessingParams.setRegion("some_invalid_region");

                CompletableFuture<TaskInfo> barcodeProcessingTask = ocrClient.processBarcodeFieldAsync(barcodeFieldProcessingParams, fileStream, file.getName(), true);
                barcodeProcessingTask.get();
                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.InvalidArgument, ValidationErrorCode.InvalidParameterValue, ErrorTarget.Region);
        }
    }

    @Test
    public void unsupportedFormatTest() throws Throwable {
        try {
            try {
                File file = new File(UNSUPPORTED_FORMAT_FILEPATH);
                FileInputStream fileStream = new FileInputStream(file);

                // creating barcode processing task
                BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();
                barcodeFieldProcessingParams.setDescription("Test barcode");

                CompletableFuture<TaskInfo> barcodeProcessingTask = ocrClient.processBarcodeFieldAsync(barcodeFieldProcessingParams, fileStream, file.getName(), true);
                barcodeProcessingTask.get();
                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.FileFormatUnsupported);
        }
    }

    @Test
    public void noImageTest() throws Throwable {
        try {
            try {
                // creating barcode processing task
                BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();
                barcodeFieldProcessingParams.setDescription("Test barcode");

                CompletableFuture<TaskInfo> barcodeProcessingTask = ocrClient.processBarcodeFieldAsync(barcodeFieldProcessingParams, null, null, true);
                barcodeProcessingTask.get();
                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (FileNotFoundException e) {
            Assume.assumeNoException(e);
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.InvalidArgument, ValidationErrorCode.MissingArgument, ErrorTarget.File);
        }
    }
}
