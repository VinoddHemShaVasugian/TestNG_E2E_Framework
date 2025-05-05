package com.framework.library;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * The RetryAnalyzer class implements the IRetryAnalyzer interface from TestNG.
 * It is used to retry failed test cases a specified number of times.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    /**
     * Counter to keep track of the current retry attempt.
     */
    int counter = 0;

    /**
     * The maximum number of retry attempts allowed.
     */
    int retryLimit = 2;

    /**
     * This method determines whether a failed test should be retried.
     *
     * @param result The result of the test method that just ran.
     * @return true if the test should be retried, false otherwise.
     */
    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}
