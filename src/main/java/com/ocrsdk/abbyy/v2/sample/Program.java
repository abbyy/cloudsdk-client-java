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

package com.ocrsdk.abbyy.v2.sample;

import com.ocrsdk.abbyy.v2.client.IOcrClient;
import com.ocrsdk.abbyy.v2.client.OcrClient;
import com.ocrsdk.abbyy.v2.client.models.AuthInfo;
import com.ocrsdk.abbyy.v2.client.models.TaskInfo;
import com.ocrsdk.abbyy.v2.client.models.enums.ExportFormat;
import com.ocrsdk.abbyy.v2.client.models.requestparams.ImageProcessingParams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Program {
    private static final String HOST = "https://cloud-eu.ocrsdk.com";
    private static final String APPLICATION_ID = "PASTE_APPLICATION_ID";
    private static final String PASSWORD = "PASTE_APPLICATION_PASSWORD";

    private static final String FILEPATH = "PASTE_IMAGE_FILEPATH";


    private static IOcrClient ocrClient;

    public static void main(String[] args) throws InterruptedException, ExecutionException, FileNotFoundException {
        AuthInfo authInfo = new AuthInfo(HOST, APPLICATION_ID, PASSWORD);
        ocrClient = new OcrClient(authInfo);

        TaskInfo taskInfo = processImageAsync().get();
        for (String resultUrl : taskInfo.getResultUrls()) {
            System.out.println(resultUrl);
        }
    }

    private static CompletableFuture<TaskInfo> processImageAsync() throws FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(FILEPATH);

        ImageProcessingParams imageProcessingParams = new ImageProcessingParams();
        imageProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.Docx, ExportFormat.Txt});
        imageProcessingParams.setLanguage("English,French");

        return ocrClient.processImageAsync(imageProcessingParams, fileStream, FILEPATH, true);
    }
}
