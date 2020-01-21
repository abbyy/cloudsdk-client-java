package com.ocrsdk.abbyy.v2.client;

import com.ocrsdk.abbyy.v2.client.models.Application;
import com.ocrsdk.abbyy.v2.client.models.AuthInfo;
import com.ocrsdk.abbyy.v2.client.models.TaskInfo;
import com.ocrsdk.abbyy.v2.client.models.TaskList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocrsdk.abbyy.v2.client.models.enums.*;
import com.ocrsdk.abbyy.v2.client.models.requestparams.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ClientTest {
    private TestConfig testConfig;
    private IOcrClient apiClient;

    @Before
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/testsettings.json");
        testConfig = objectMapper.readValue(file, TestConfig.class);

        apiClient = new OcrClient(new AuthInfo(testConfig.getHost(), testConfig.getApplicationId(), testConfig.getPassword()));
    }

    @Test
    public void processImageTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.IMAGE);

        ImageProcessingParams imageProcessingParams = new ImageProcessingParams();
        imageProcessingParams.setLanguage("English");
        imageProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.Docx});

        TaskInfo processImageTask = apiClient.processImageAsync(
                imageProcessingParams,
                fileStream,
                TestFile.IMAGE,
                true
        ).get();

        checkResultTask(processImageTask);
    }

    @Test
    public void submitImageTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        TaskInfo first = submitImageAsync(TestFile.IMAGE);
        TaskInfo second = submitImageAsync(TestFile.FIELDS, first.getTaskId());

        Assert.assertEquals(1, first.getFilesCount());
        Assert.assertEquals(2, second.getFilesCount());
    }

    @Test
    public void processDocumentTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        TaskInfo submitImageTask = submitImageAsync(TestFile.IMAGE);

        DocumentProcessingParams documentProcessingParams = new DocumentProcessingParams();
        documentProcessingParams.setTaskId(submitImageTask.getTaskId());
        documentProcessingParams.setLanguage("English");
        documentProcessingParams.setProfile(ProcessingProfile.DocumentConversion);
        documentProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.PdfSearchable, ExportFormat.Rtf});

        TaskInfo processDocumentTask = apiClient.processDocumentAsync(documentProcessingParams, true).get();

        checkResultTask(processDocumentTask, submitImageTask.getTaskId(), 2);
    }

    @Test
    public void processBusinessCardTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.BUSINESS_CARD);

        BusinessCardProcessingParams businessCardProcessingParams = new BusinessCardProcessingParams();
        businessCardProcessingParams.setLanguage("English");
        businessCardProcessingParams.setExportFormats(BusinessCardExportFormat.Xml);

        TaskInfo processBusinessCardTask = apiClient.processBusinessCardAsync(
                businessCardProcessingParams,
                fileStream,
                TestFile.BUSINESS_CARD,
                true
        ).get();

        checkResultTask(processBusinessCardTask);
    }

    @Test
    public void processTextFieldTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.BUSINESS_CARD);

        TextFieldProcessingParams textFieldProcessingParams = new TextFieldProcessingParams();
        textFieldProcessingParams.setLanguage("English");
        textFieldProcessingParams.setRegion("140,550,1130,700");

        TaskInfo processTextFieldTask = apiClient.processTextFieldAsync(
                textFieldProcessingParams,
                fileStream,
                TestFile.BUSINESS_CARD,
                true
        ).get();

        checkResultTask(processTextFieldTask);
    }

    @Test
    public void processBarcodeFieldTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.FIELDS);

        BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();
        barcodeFieldProcessingParams.setBarcodeTypes(new BarcodeType[]{BarcodeType.Ean8});
        barcodeFieldProcessingParams.setRegion("1800,3200,2250,3400");

        TaskInfo processBarcodeFieldTask = apiClient.processBarcodeFieldAsync(
                barcodeFieldProcessingParams,
                fileStream,
                TestFile.FIELDS,
                true
        ).get();

        checkResultTask(processBarcodeFieldTask);
    }

    @Test
    public void processCheckmarkFieldTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.FIELDS);

        CheckmarkFieldProcessingParams checkmarkFieldProcessingParams = new CheckmarkFieldProcessingParams();
        checkmarkFieldProcessingParams.setCheckmarkType(CheckmarkType.Square);
        checkmarkFieldProcessingParams.setRegion("700,930,800,1030");

        TaskInfo processCheckmarkFieldTask = apiClient.processCheckmarkFieldAsync(
                checkmarkFieldProcessingParams,
                fileStream,
                TestFile.FIELDS,
                true
        ).get();

        checkResultTask(processCheckmarkFieldTask);
    }

    @Test
    public void processFieldsTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.FIELDS_XML_CONFIG);

        TaskInfo submitImageTask = submitImageAsync(TestFile.FIELDS);

        FieldsProcessingParams fieldProcessingParams = new FieldsProcessingParams();
        fieldProcessingParams.setTaskId(submitImageTask.getTaskId());
        fieldProcessingParams.setWriteRecognitionVariants(true);

        TaskInfo processFieldsTask = apiClient.processFieldsAsync(fieldProcessingParams, fileStream, TestFile.FIELDS_XML_CONFIG, true).get();

        checkResultTask(processFieldsTask, submitImageTask.getTaskId());
    }

    @Test
    public void processMrzTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.MRZ);

        MrzProcessingParams mrzProcessingParams = new MrzProcessingParams();
        mrzProcessingParams.setDescription("Task description: MRZ processing.");
        mrzProcessingParams.setPdfPassword("test");

        TaskInfo processMrzTask = apiClient.processMrzAsync(
                mrzProcessingParams,
                fileStream,
                TestFile.FIELDS,
                true
        ).get();

        checkResultTask(processMrzTask);
    }

    @Test
    public void processReceiptTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.IMAGE);

        ReceiptProccessingParams receiptProccessingParams = new ReceiptProccessingParams();
        receiptProccessingParams.setCountries(new ReceiptRecognizingCountry[]{ReceiptRecognizingCountry.Russia});

        TaskInfo processReceiptTask = apiClient.processReceiptAsync(
                receiptProccessingParams,
                fileStream,
                TestFile.FIELDS,
                true
        ).get();

        checkResultTask(processReceiptTask);
    }

    @Test
    public void getTaskStatusTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        TaskInfo submitImageTask = submitImageAsync(TestFile.IMAGE);
        TaskInfo resultTask = apiClient.getTaskStatusAsync(submitImageTask.getTaskId()).get();

        Assert.assertNotNull(resultTask);
        Assert.assertEquals(submitImageTask.getTaskId(), resultTask.getTaskId());
        Assert.assertEquals(TaskStatus.Submitted, resultTask.getStatus());
    }

    @Test
    public void deleteTaskTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        TaskInfo submitImageTask = submitImageAsync(TestFile.IMAGE);
        TaskInfo deletedTask = apiClient.deleteTaskAsync(submitImageTask.getTaskId()).get();
        TaskInfo resultTask = apiClient.getTaskStatusAsync(deletedTask.getTaskId()).get();

        Assert.assertNotNull(deletedTask);
        Assert.assertNotNull(resultTask);

        Assert.assertEquals(submitImageTask.getTaskId(), deletedTask.getTaskId());
        Assert.assertEquals(deletedTask.getTaskId(), resultTask.getTaskId());

        Assert.assertEquals(TaskStatus.Submitted, submitImageTask.getStatus());
        Assert.assertEquals(TaskStatus.Deleted, deletedTask.getStatus());
        Assert.assertEquals(TaskStatus.Deleted, resultTask.getStatus());
    }

    @Test
    public void listTasksTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        TaskInfo submitImageTask = submitImageAsync(TestFile.IMAGE);

        TasksListingParams tasksListingParams = new TasksListingParams();
        tasksListingParams.setExcludeDeleted(true);

        TaskList taskList = apiClient.listTasksAsync(tasksListingParams).get();

        Assert.assertNotNull(taskList);
        Assert.assertTrue(taskList.getTasks().size() > 0);
        Assert.assertTrue(taskList.getTasks().stream()
                .anyMatch(task -> task.getTaskId().equals(submitImageTask.getTaskId())));


    }

    @Test
    public void listFinishedTasksTest() throws InterruptedException, ExecutionException, FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(TestFile.IMAGE);

        TaskInfo submitImageTask = submitImageAsync(TestFile.IMAGE);

        TaskInfo processImageTask = apiClient.processImageAsync(
                new ImageProcessingParams(),
                fileStream,
                TestFile.IMAGE,
                true
        ).get();

        TaskList listFinishedTasks = apiClient.listFinishedTaskAsync().get();

        Assert.assertNotNull(listFinishedTasks);

        Assert.assertTrue(listFinishedTasks.getTasks().size() > 0);
        Assert.assertTrue(listFinishedTasks.getTasks().size() <= 100);

        Assert.assertFalse(listFinishedTasks.getTasks().stream().anyMatch(task -> task.getTaskId().equals(submitImageTask.getTaskId())));
        Assert.assertTrue(listFinishedTasks.getTasks().stream().anyMatch(task -> task.getTaskId().equals(processImageTask.getTaskId())));

        TasksListingParams tasksListingParams = new TasksListingParams();
        tasksListingParams.setExcludeDeleted(true);

        TaskList taskList = apiClient.listTasksAsync(tasksListingParams).get();

        Assert.assertNotNull(taskList);
        Assert.assertTrue(taskList.getTasks().size() > 0);
        Assert.assertTrue(taskList.getTasks().stream()
                .anyMatch(task -> task.getTaskId().equals(submitImageTask.getTaskId())));

    }

    @Test
    public void getApplicationInfoTest() throws InterruptedException, ExecutionException {
        Application application = apiClient.getApplicationInfoAsync().get();

        Assert.assertNotNull(application);
        Assert.assertEquals(testConfig.getApplicationId(), application.getId());
    }

    @Test
    public void invalidTaskIdTest() throws Throwable {
        // Due to Java async-call mechanism, any exception, thrown during asynchronous method is wrapped with ExecutionException
        // So, to obtain original exception, one has to get cause of ExecutionException instance
        try {
            try {
                UUID invalidTaskId = UUID.randomUUID();
                apiClient.getTaskStatusAsync(invalidTaskId).get();

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
                apiClient.getTaskStatusAsync(null).get();

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
                FileInputStream fileStream = new FileInputStream(TestFile.FIELDS);

                BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();
                barcodeFieldProcessingParams.setRegion("some_invalid_region");

                apiClient.processBarcodeFieldAsync(
                        barcodeFieldProcessingParams,
                        fileStream,
                        TestFile.FIELDS,
                        true
                ).get();

                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.InvalidArgument, ValidationErrorCode.InvalidParameterValue, ErrorTarget.Region);
        }
    }

    @Test
    public void unsupportedFormatTest() throws Throwable {
        try {
            try {
                FileInputStream fileStream = new FileInputStream(TestFile.UNSUPPORTED_FORMAT);

                BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();

                apiClient.processBarcodeFieldAsync(
                        barcodeFieldProcessingParams,
                        fileStream,
                        TestFile.UNSUPPORTED_FORMAT,
                        true
                ).get();

                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.FileFormatUnsupported);
        }
    }

    @Test
    public void noImageTest() throws Throwable {
        try {
            try {
                BarcodeFieldProcessingParams barcodeFieldProcessingParams = new BarcodeFieldProcessingParams();

                apiClient.processBarcodeFieldAsync(
                        barcodeFieldProcessingParams,
                        null,
                        null,
                        true
                ).get();

                Assert.fail("ApiException expected");
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } catch (ApiException e) {
            ShouldHelper.checkException(e, ErrorCode.InvalidArgument, ValidationErrorCode.MissingArgument, ErrorTarget.File);
        }
    }

    private TaskInfo submitImageAsync(String fileName) throws
            FileNotFoundException, InterruptedException, ExecutionException {
        return submitImageAsync(fileName, null);
    }

    private TaskInfo submitImageAsync(String fileName, UUID taskId) throws
            FileNotFoundException, InterruptedException, ExecutionException {
        FileInputStream fileStream = new FileInputStream(fileName);

        ImageSubmittingParams imageSubmittingParams = new ImageSubmittingParams();
        imageSubmittingParams.setTaskId(taskId);

        TaskInfo submitImageTask = apiClient.submitImageAsync(imageSubmittingParams, fileStream, fileName).get();
        Assert.assertNotNull(submitImageTask);
        Assert.assertNotEquals(new UUID(0L, 0L), submitImageTask.getTaskId());
        Assert.assertEquals(TaskStatus.Submitted, submitImageTask.getStatus());

        return submitImageTask;
    }

    private static void checkResultTask(TaskInfo taskInfo) {
        checkResultTask(taskInfo, null, 1, TaskStatus.Completed);
    }

    private static void checkResultTask(TaskInfo taskInfo, UUID taskId) {
        checkResultTask(taskInfo, taskId, 1, TaskStatus.Completed);
    }

    private static void checkResultTask(TaskInfo taskInfo, UUID taskId, int resultUrls) {
        checkResultTask(taskInfo, taskId, resultUrls, TaskStatus.Completed);
    }

    private static void checkResultTask(TaskInfo taskInfo, UUID taskId, int resultUrls, TaskStatus taskStatus) {
        Assert.assertNotNull(taskInfo);
        Assert.assertNotEquals(new UUID(0L, 0L), taskId);

        if (taskId != null) {
            Assert.assertEquals(taskId, taskInfo.getTaskId());
        }

        Assert.assertEquals(taskStatus, taskInfo.getStatus());
        Assert.assertEquals(resultUrls, taskInfo.getResultUrls().size());
    }
}
