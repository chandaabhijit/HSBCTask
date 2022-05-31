package com.hsbc.core.throttler.data;

import com.hsbc.core.throttler.Throttler;

public class RollingWindowThrottlerLimit {

    private final int maxRate;
    private final int timeWindowInMillis;
    private int currentCounter = 0;
    private long lastUpdateTime = System.currentTimeMillis();

    public RollingWindowThrottlerLimit(int maxRate, int timeWindowInMillis) {
        this.maxRate = maxRate;
        this.timeWindowInMillis = timeWindowInMillis;
        reset();
    }

    public void reset() {
        this.currentCounter = this.maxRate;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public Throttler.ThrottleResult shouldProceed() {
        long remainingTime = getRemainingTime();
        if (remainingTime <= 0) {
            //if the last update was before currentTime - timeWindowInMillis
            //we can reset the counter and proceed
            reset();
        }
        remainingTime = getRemainingTime();
        if (remainingTime > 0 && currentCounter > 0) {

            currentCounter--;
            return Throttler.ThrottleResult.PROCEED;
        } else {
            return Throttler.ThrottleResult.DO_NOT_PROCEED;
        }
    }

    public long getRemainingTime() {
        long currentTime = System.currentTimeMillis();
        return lastUpdateTime + timeWindowInMillis - currentTime;
    }

    public void incrementCurrentCounter() {
        currentCounter++;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public int getTimeWindowInMillis() {
        return timeWindowInMillis;
    }

    public int getCurrentCounter() {
        return currentCounter;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
}
