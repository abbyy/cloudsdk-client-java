package abbyy.cloudsdk.v2.client.http;

import java.io.*;
import java.util.UUID;

public class HttpAsyncFileWrapper {
    private static final String lineSeparator = "\r\n";
    private static final String boundaryPrefix = "--";

    private FileInputStream fileStream;
    private String fileName;

    private String boundary;

    public HttpAsyncFileWrapper(FileInputStream fileStream, String fileName) {
        this.fileStream = fileStream;
        this.fileName = fileName;
        this.boundary = UUID.randomUUID().toString();
    }

    public void transferFileTo(OutputStream outputStream) throws IOException {
            DataOutputStream request = new DataOutputStream(outputStream);

            request.writeBytes(boundaryPrefix + boundary + lineSeparator);
            request.writeBytes("Content-Disposition: form-data;" +
                                    "name=\"" + fileName + "\";" +
                                    "filename=\"" + fileName + "\"" + lineSeparator);
            request.writeBytes(lineSeparator);

            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileStream.read(dataBuffer)) != -1) {
                request.write(dataBuffer, 0, bytesRead);
            }

            request.writeBytes(lineSeparator);
            request.writeBytes(boundaryPrefix + boundary + boundaryPrefix + lineSeparator);

            request.flush();
            request.close();
    }

    public String getBoundary() {
        return boundary;
    }
}
