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

import com.ocrsdk.abbyy.v2.client.models.Application;
import com.ocrsdk.abbyy.v2.client.models.TaskInfo;
import com.ocrsdk.abbyy.v2.client.models.TaskList;
import com.ocrsdk.abbyy.v2.client.models.enums.ProcessingProfile;
import com.ocrsdk.abbyy.v2.client.models.enums.TaskStatus;
import com.ocrsdk.abbyy.v2.client.models.requestparams.*;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Client for the API of ABBYY Cloud OCR SDK
 */
public interface IOcrClient {
    /**
     * The method loads the image, creates a processing task for the image with the specified parameters, and passes the task for processing.
     *
     * <b>Note:</b> This method allows you to specify up to three file formats for the result, in which case the server response
     * for the completed task will contain several result URLs. If there is not enough money on your account,
     * the task will be created, but will be suspended with {@link TaskStatus#NotEnoughCredits}
     * status. You can pass this task for processing using the {@link #processDocumentAsync(DocumentProcessingParams, boolean)}
     * method after you have topped up your account. The task will not be created, if you exceed the limit of uploaded images.
     *
     * @param parameters Image processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processImageAsync(ImageProcessingParams parameters, InputStream fileStream, String fileName,
                                                  boolean waitTaskFinished);

    /**
     * The method adds the image to the existing task or creates a new task for the image. This task is not passed for processing until
     * the {@link #processDocumentAsync(DocumentProcessingParams, boolean)} or
     * {@link #processFieldsAsync(FieldsProcessingParams, InputStream, String, boolean)} method is called for it.
     * Several images can be uploaded to one task
     * @param parameters Image submitting parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> submitImageAsync(ImageSubmittingParams parameters, InputStream fileStream, String fileName);


    /**
     * The method starts the processing task with the specified parameters.
     *
     * <b>Note:</b> This method allows you to process several images using the same settings and obtain recognition
     * result as a multi-page document. You can upload several images to one task using {@link #submitImageAsync(ImageSubmittingParams, InputStream, String)} method.
     * It is also possible to specify up to three file formats for the result, in which case the server response for the completed
     * task will contain several result URLs. Only the task with {@link TaskStatus#Submitted},
     * {@link TaskStatus#Completed} or {@link TaskStatus#NotEnoughCredits}
     * status can be started using this method.
     *
     * @param parameters Document processing parameters
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processDocumentAsync(DocumentProcessingParams parameters, boolean waitTaskFinished);

    /**
     * The method allows you to recognize a business card on an image. The method loads the image, creates a processing task for the image
     * with the specified parameters, and passes the task for processing.
     * @param parameters Business card processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processBusinessCardAsync(BusinessCardProcessingParams parameters, InputStream fileStream,
                                                         String fileName, boolean waitTaskFinished);

    /**
     * The method allows you to extract the value of a text field on an image. The method loads the image, creates a processing task for the image
     * with the specified parameters, and passes the task for processing. The result of recognition is returned in XML format.
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/quick-start-guide/text-fields-recognition/">How to Recognize Text Fields</a>
     *  to know how to tune the parameters.
     * <a href="https://www.ocrsdk.com/documentation/specifications/processing-profiles/"/>
     *
     * @param parameters Text field processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processTextFieldAsync(TextFieldProcessingParams parameters, InputStream fileStream,
                                           String fileName, boolean waitTaskFinished);

    /**
     * The method allows you to extract the value of a barcode on an image. The method loads the image, creates a processing task for
     * the image with the specified parameters, and passes the task for processing. The result of recognition is returned in XML format.
     * Binary data is returned in Base64 encoding.
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/quick-start-guide/barcode-ocr-sdk/">How to Recognize Barcodes</a>
     * to know another way of barcode recognition. {@link ProcessingProfile#BarcodeRecognition}
     * profile is used for processing. Information about processing profiles
     * <a href="https://www.ocrsdk.com/documentation/specifications/processing-profiles/"/>
     *
     * @param parameters Barcode field processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processBarcodeFieldAsync(BarcodeFieldProcessingParams parameters, InputStream fileStream,
                                           String fileName, boolean waitTaskFinished);


    /**
     * The method allows you to extract the value of a checkmark on an image. The method loads the image, creates a processing task for
     * the image with the specified parameters, and passes the task for processing. The result of recognition is returned in XML format.
     * The values of checkmarks are "checked", "unchecked", or "corrected".
     *
     * <b>Note:</b> See <a href="https://www.ocrsdk.com/documentation/specifications/processing-profiles/"/>
     *
     * @param parameters Checkmark field processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processCheckmarkFieldAsync(CheckmarkFieldProcessingParams parameters, InputStream fileStream,
                                              String fileName, boolean waitTaskFinished);

    /**
     * The method allows you to recognize several fields in a document. The method starts the processing task with the parameters of processing
     * specified in an XML file. Image files can be uploaded to the task using <see cref="SubmitImageAsync"/> method. The result of recognition is
     * returned in XML format. Binary data is returned in Base64 encoding.
     *
     * <b>Note:</b> You can use the <a href="https://www.ocrsdk.com/schema/taskDescription-1.0.xsd">XSD schema</a>
     * of the XML file to create the file with necessary settings. See also the description of the tags and
     * several examples of XML files with settings in
     * <a href="https://www.ocrsdk.com/documentation/specifications/xml-scheme-field-settings/">XML Parameters of Field Recognition</a>.
     * Only the task with {@link TaskStatus#Submitted}, {@link TaskStatus#Completed}
     * or {@link TaskStatus#NotEnoughCredits} status can be started using this method.
     * Note that this method is most convenient when you process a large number of fields on one page: in this case the price of recognition
     * of all fields on one page does not exceed the price of recognition of a page of A4 size.
     *
     * @param parameters Fields processing parameters
     * @param fileStream XML File describing fields recognition settings
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processFieldsAsync(FieldsProcessingParams parameters, InputStream fileStream,
                                                String fileName, boolean waitTaskFinished);

    /**
     * This method finds a machine-readable zone on the image and extracts data from it. Machine-readable zone(MRZ) is typically found on
     * official travel or identity documents of many countries.It can have 2 lines or 3 lines of machine-readable data. This method allows to
     * process MRZ written in accordance with ICAO Document 9303 (endorsed by the International Organization for Standardization and the
     * International Electrotechnical Commission as ISO/IEC 7501-1)). The result of recognition is returned in XML format.
     *
     * <b>Note:</b> <a href="https://en.wikipedia.org/wiki/ICAO"/>
     * <a href="https://en.wikipedia.org/wiki/International_Organization_for_Standardization"/>
     * <a href="https://en.wikipedia.org/wiki/International_Electrotechnical_Commission"/>
     *
     * @param parameters Mrz processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processMrzAsync(MrzProcessingParams parameters, InputStream fileStream,
                                        String fileName, boolean waitTaskFinished);

    /**
     * Important: the technology fully supports the receipts issued in USA and France, other countries are currently supported in beta mode.
     * The method allows you to recognize the image of a receipt.The method loads the image, creates a processing task for the image with the
     * specified parameters, and passes the task for processing. The result is returned in XML format.
     *
     * <b>Note:</b> The elements and attributes of the resulting file are described in
     * <a href="https://en.wikipedia.org/wiki/ICAO"/>
     * For a step-by-step guide, see <a href="https://www.ocrsdk.com/documentation/quick-start-guide/receipt-recognition/">How to Recognize
     * Receipts.</a> The recommendations on preparing the input images can be found in
     * <a href="https://www.ocrsdk.com/documentation/hints-tips/photograph-scan-receipts/">Photographing and Scanning Receipts</a>.
     *
     * @param parameters Receipt processing parameters
     * @param fileStream Stream of the file with the image to recognize
     * @param fileName Name of the file with the image
     * @param waitTaskFinished Indicates whether to wait until task is finished.
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> processReceiptAsync(ReceiptProccessingParams parameters, InputStream fileStream,
                                     String fileName, boolean waitTaskFinished);

    /**
     * The method returns the current status of the task and the URL of the result of processing for completed tasks.
     * Please note that the task status is not changed momentarily. Do not call this method more frequently than once in 2 or 3 seconds.
     * @param taskId Id of the task
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> getTaskStatusAsync(UUID taskId);

    /**
     * The method deletes the task and the images associated with this task from the ABBYY Cloud OCR SDK storage.
     * Only the tasks that have the status other than {@link TaskStatus#InProgress}
     * or {@link TaskStatus#Deleted} can be deleted.
     *
     * <b>Note:</b> If you try to delete the task that has already been deleted, the successful response is returned.
     * If you submit the same image to different tasks, to delete the image from the Cloud OCR SDK storage, you will need to call
     * this method for each task, which contains the image.
     *
     * @param taskId Id of the task
     * @return {@link TaskInfo}
     */
    CompletableFuture<TaskInfo> deleteTaskAsync(UUID taskId);

    /**
     * The method returns the list of tasks created by your application. By default, the {@link TaskStatus#Deleted}
     * tasks are included, but you can filter them out. This method may be useful if you need to compile an application usage
     * log for some period of time.
     *
     * <b>Note:</b> The tasks are ordered by the date of the last action with the task. This method can list up to 1000 tasks. If you process
     * a large number of tasks, call this method for shorter time periods.
     *
     * @param parameters Parameters for listing tasks
     * @return {@link TaskList}
     */
    CompletableFuture<TaskList> listTasksAsync(TasksListingParams parameters);

    /**
     * The method returns the list of finished tasks. A task is finished if it has one of the following statuses:
     * {@link TaskStatus#Completed}, {@link TaskStatus#ProcessingFailed},
     * {@link TaskStatus#NotEnoughCredits}.
     *
     * <b>Note:</b> The tasks are ordered by the time of the end of processing. No more than 100 tasks can be returned at one method call. To
     * obtain more tasks, delete some finished tasks using the {@link #deleteTaskAsync(UUID)} method and then call this
     * method again.
     * The method may be useful if you work with a large number of tasks simultaneously. But there is no sense in calling this
     * method if your application does not have any incomplete tasks sent for the processing.
     * Please note that the task status is not changed momentarily. Do not call this method more frequently than once in 2 or
     *  seconds.
     * @return {@link TaskList}
     */
    CompletableFuture<TaskList> listFinishedTaskAsync();

    /**
     * This method allows you to receive information about the application type, its current balance and expiration date. You may
     * find it helpful for keeping the usage records.
     *
     * <b>Note:</b> The application is identified by its authentication information.
     * By default the call to this method is disabled for all applications. To enable getting the application information using
     * this method: 1) go to <a href="https://cloud.ocrsdk.com/"/> and log in; 2) click Settings under your application's name;
     * 3) on the next screen, click enable:
     * @return  {@link Application}
     */
    CompletableFuture<Application> getApplicationInfoAsync();

}
