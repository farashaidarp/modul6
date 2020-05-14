package com.example.modul6.API;//api untuk request data

import com.example.modul6.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel>ardCreateData(
            @Field("nama") String nama,
            @Field("jurusan") String jurusan,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel>ardDeleteData(
            @Field("id") int id
    );
}
