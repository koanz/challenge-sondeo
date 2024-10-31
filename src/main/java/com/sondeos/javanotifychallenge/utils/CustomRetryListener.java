package com.sondeos.javanotifychallenge.utils;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class CustomRetryListener implements RetryListener {
    @Override public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        //System.out.println("Reintento número " + context.getRetryCount() + " debido a: " + throwable.getMessage());
    }
}
