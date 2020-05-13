package com.example.modul6.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    //membuat koneksi ke server melalui retrofit
    private static final String baseURL = "http://10.0.2.2/mahasiswa/";
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro==null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())//convert data ke josn
                    .build();
        }

        return retro;
    }
}
