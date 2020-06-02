package com.jassen.skindiseasedetector.retrofit;

public interface CallApi<T> {
    void onSuccess(T result);
    void onFailed(String message);
}
