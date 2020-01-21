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

package com.ocrsdk.abbyy.v2.client;

import com.ocrsdk.abbyy.v2.client.http.HttpAsyncClient;
import com.ocrsdk.abbyy.v2.client.http.HttpAsyncRequest;
import com.ocrsdk.abbyy.v2.client.http.HttpAsyncResponse;
import com.ocrsdk.abbyy.v2.client.http.HttpRequestMethod;
import com.ocrsdk.abbyy.v2.client.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocrsdk.abbyy.v2.client.models.Error;
import com.ocrsdk.abbyy.v2.client.models.requestparams.*;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class OcrClient implements IOcrClient {

    private HttpAsyncClient httpAsyncClient;

    public OcrClient(AuthInfo authInfo) {
        this.httpAsyncClient = new HttpAsyncClient(authInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processImageAsync(ImageProcessingParams parameters, InputStream fileStream, String fileName,
                                                         boolean waitTaskFinished){
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessImage, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> submitImageAsync(ImageSubmittingParams parameters, InputStream fileStream, String fileName) {
        return makeRequestAsync(HttpRequestMethod.Post, Urls.SubmitImage, parameters, fileStream, fileName, parameters.getResponseClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processDocumentAsync(DocumentProcessingParams parameters, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessDocument, parameters, null, null, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processBusinessCardAsync(BusinessCardProcessingParams parameters, InputStream fileStream,
                                                                String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessBusinessCard, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processTextFieldAsync(TextFieldProcessingParams parameters, InputStream fileStream,
                                                             String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessTextField, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processBarcodeFieldAsync(BarcodeFieldProcessingParams parameters, InputStream fileStream,
                                                         String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessBarcodeField, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processCheckmarkFieldAsync(CheckmarkFieldProcessingParams parameters, InputStream fileStream,
                                                                  String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessCheckmarkField, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processFieldsAsync(FieldsProcessingParams parameters, InputStream fileStream,
                                                   String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessFields, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processMrzAsync(MrzProcessingParams parameters, InputStream fileStream,
                                                       String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessMrz, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> processReceiptAsync(ReceiptProccessingParams parameters, InputStream fileStream,
                                                    String fileName, boolean waitTaskFinished) {
        return startTaskAsync(HttpRequestMethod.Post, Urls.ProcessReceipt, parameters, fileStream, fileName, waitTaskFinished);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> getTaskStatusAsync(UUID taskId) {
        return makeRequestAsync(HttpRequestMethod.Get, Urls.GetTaskStatus, taskId == null ? null : new Task(taskId), null, null, TaskInfo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskInfo> deleteTaskAsync(UUID taskId) {
        return makeRequestAsync(HttpRequestMethod.Post, Urls.DeleteTask, taskId == null ? null : new Task(taskId), null, null, TaskInfo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskList> listTasksAsync(TasksListingParams parameters) {
        return makeRequestAsync(HttpRequestMethod.Get, Urls.ListTasks, parameters, null, null, parameters.getResponseClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<TaskList> listFinishedTaskAsync() {
        return makeRequestAsync(HttpRequestMethod.Get, Urls.ListFinishedTasks, null, null, null, TaskList.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<Application> getApplicationInfoAsync() {
        return makeRequestAsync(HttpRequestMethod.Get, Urls.GetApplicationInfo, null, null, null, Application.class);
    }

    private CompletableFuture<TaskInfo> startTaskAsync(
            HttpRequestMethod method,
            String requestUrl,
            RequestParams<TaskInfo> params,
            InputStream fileStream,
            String filename,
            boolean waitTaskFinished
    ) {

        CompletableFuture<TaskInfo> makeRequestTask = makeRequestAsync(method, requestUrl, params, fileStream, filename, params.getResponseClass());
        if (!waitTaskFinished) {
            return makeRequestTask;
        }
        return makeRequestTask.thenCompose(this::waitForTask);
    }

    private <T> CompletableFuture<T> makeRequestAsync(
            HttpRequestMethod method,
            String requestUrl,
            Object params,
            InputStream fileStream,
            String fileName,
            Class<T> responseClass
    ) {
        HttpAsyncRequest<T> request = HttpAsyncRequest.buildRequest(method, requestUrl, params, fileStream, fileName);
        CompletableFuture<HttpAsyncResponse<T>> sendRequestTask = httpAsyncClient.sendRequest(request);
        return sendRequestTask.thenApplyAsync(response -> processResponse(response, responseClass));
    }

    private <T> T processResponse(HttpAsyncResponse<T> response, Class<T> responseClass) {
        String responseData = response.getContent();
        if (response.getStatusCode() == HttpAsyncResponse.STATUS_CODE_OK) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseData, responseClass);
            } catch (Exception exception) {
                throw new ApiException(
                        "Could not deserialize the response body.",
                        response.getStatusCode(),
                        Error.fromText(responseData),
                        response.getHeaders(),
                        exception);
            }
        }
        throw new ApiException(
                "Server responded with " + response.getStatusCode() + " status code.",
                response.getStatusCode(),
                tryDeserializeError(responseData),
                response.getHeaders());
    }

    private CompletableFuture<TaskInfo> waitForTask(TaskInfo taskInfo) {
        if (TaskExtensions.isInProcess(taskInfo)) {
            try {
                Thread.sleep(taskInfo.getRequestStatusDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getTaskStatusAsync(taskInfo.getTaskId()).thenComposeAsync(this::waitForTask);
        }
        return CompletableFuture.completedFuture(taskInfo);
    }

    private Error tryDeserializeError(String responseData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseData, Error.class);
        }
        catch (Exception e) {
            return Error.fromText(responseData);
        }

    }

    static class Task {
        private String taskId;

        Task(UUID taskId) {
            this.taskId = taskId.toString();
        }
    }
}
