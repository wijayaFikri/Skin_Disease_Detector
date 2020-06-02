package com.jassen.skindiseasedetector.retrofit;

import com.google.gson.internal.LinkedHashTreeMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SddApi {

    @FormUrlEncoded
    @POST("/predict")
    Call<LinkedHashTreeMap> sendImage(@Field("image") String base64);
}
