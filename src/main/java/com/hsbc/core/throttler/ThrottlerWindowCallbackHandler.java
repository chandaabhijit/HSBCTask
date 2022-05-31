package com.hsbc.core.throttler;

/****
 * Interface which should be implemented by classes that want to receive callbacks
 * when the Throttle window is open to process new messages
 */

public interface ThrottlerWindowCallbackHandler {

    void onNotifyThrottleWindowOpen();
}
