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

package com.ocrsdk.abbyy.v2.client.http;

import java.io.*;
import java.util.UUID;

public class HttpAsyncFileWrapper {
    private static final String lineSeparator = "\r\n";
    private static final String boundaryPrefix = "--";

    private InputStream fileStream;
    private String fileName;

    private String boundary;

    public HttpAsyncFileWrapper(InputStream fileStream, String fileName) {
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
