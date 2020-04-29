package com.ocrsdk.abbyy.v2.client;

import com.ocrsdk.abbyy.v2.client.models.Error;
import com.ocrsdk.abbyy.v2.client.models.ErrorData;
import org.junit.Assert;

public class ShouldHelper {
    public static void checkException(ApiException exception, String expectedCode) {
        checkException(exception, expectedCode, null, null);
    }

    public static void checkException(ApiException exception, String expectedCode, String expectedValidationErrorCode) {
        checkException(exception, expectedCode, expectedValidationErrorCode, null);
    }

    public static void checkException(ApiException exception, String expectedCode,
                                      String expectedValidationErrorCode, String expectedValidationErrorTarget) {

        Error error = exception.getError();
        ErrorData errorData = error.getErrorData();
        Assert.assertEquals(expectedCode, errorData.getCode());

        if ((expectedValidationErrorCode == null) || (expectedValidationErrorCode.isEmpty())) {
            return;
        }

        ErrorData[] details = errorData.getDetails();
        Assert.assertNotNull(details);
        Assert.assertEquals(1, details.length);
        Assert.assertNotNull(details[0]);
        Assert.assertEquals(expectedValidationErrorCode, details[0].getCode());

        if ((expectedValidationErrorTarget == null) || (expectedValidationErrorTarget.isEmpty())) {
            return;
        }

        Assert.assertEquals(expectedValidationErrorTarget, details[0].getTarget());
    }
}
