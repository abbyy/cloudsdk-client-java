#Cloud OCR SDK
This Java library is intended to simplify the development of image recognition solutions with ABBYY Cloud OCR SDK API. 
This repo contains the client library enabling simple access to high-quality recognition technologies. 
For more information about the product visit [ABBYY Cloud OCR SDK website](https://www.ocrsdk.com/).

##Build
To simplify project building, Maven wrapper is used. One just needs to run
```
mvnw.cmd clean package
```
for Windows systems, and
```
./mvnw clean package
```
for Unix.
This procedure will also run set of tests.
Most of them are deactivated until names of input files are specified in `ClientTest` class.
However, test method `applicationInfoTest()` will run anyway.
To complete this test successfully, one should enable option **Allow getting application info** in application settings.

Running tests can take up to several minutes, so tests can be disabled by adding key **-DskipTests**:
```
mvnw.cmd clean package -DskipTests
```

The build result is located in **target/releases**.

##Usage
The following sample demonstrates how to process image with the specified parameters and export the result to TXT and DOCX 
using the ABBYY Cloud OCR SDK client library. 
Start your developement with the following steps.

Provide your authentication info and the HTTP server URL address, used for API calls (see Data processing location for details):
```
AuthInfo authInfo = new AuthInfo(host, applicationId, password);
```

Create the main `OcrClient` object:
```
OcrClient client = new OcrClient(authInfo)
```

Set the processing and result options (you can find all the settings 
[here](https://www.ocrsdk.com/documentation/api-reference/process-image-method/):
```
ImageProcessingParams imageProcessingParams = new ImageProcessingParams();
imageProcessingParams.setExportFormats(new ExportFormat[]{ExportFormat.Docx, ExportFormat.Txt});
imageProcessingParams.setLanguage("English,French");
```

Open file as input stream:
```
FileInputStream fileStream = new FileInputStream(new File(filePath))
```

Pass the parameters to a new task. 
Set `waitTaskFinished` flag to **true** to wait for the results in current method. 
If `waitTaskFinished` is **false**, the method will not wait for the task finish.
Method `processImageAsync` of class  `OcrClient` creates asynchronous method, its result can be obtained via `get()`:
```
CompletableFuture<TaskInfo> asyncMethod = ocrClient.processImageAsync(imageProcessingParams, fileStream, file.getName(), true);
TaskInfo taskInfo = asyncMethod.get();
```
Obtaining result of asynchronous method can cause InterruptedException and ExecutionException, 
so one should process method `get()` appropriately.

Display the URL addresses of the result documents:
```
for (String resultUrl : taskInfo.getResultUrls()) {
    System.out.println(resultUrl)
}
```

See more examples in test class `ClientTest`.