package com.jassen.skindiseasedetector.retrofit;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Part;

public class SddRetrofit {
    private Retrofit retrofit;

    public SddRetrofit() {
        String BASE_URL = "http://192.168.0.109:8000/";
        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public void processImage(String base64, final CallApi<String> callApi) {
        SddApi sddApi = retrofit.create(SddApi.class);
        Call<LinkedHashTreeMap> processImageCall = sddApi.sendImage(base64);
        processImageCall.enqueue(new Callback<LinkedHashTreeMap>() {
            @Override
            public void onResponse(Call<LinkedHashTreeMap> call, Response<LinkedHashTreeMap> response) {
                LinkedHashTreeMap map = response.body();
                String result = null;
                if (map != null) {
                    result = (String) map.get("result");
                }
                callApi.onSuccess(result);
            }

            @Override
            public void onFailure(Call<LinkedHashTreeMap> call, Throwable t) {
                t.printStackTrace();
                callApi.onFailed("Something wrong occurred , please try again.");
            }
        });
    }
}
