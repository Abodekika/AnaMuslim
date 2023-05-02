package com.example.anamuslim.download.download_quan_image;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface DownloadQuranInterface {

//    @GET("o/quran_images.zip?alt=media&token=171d1bd3-7601-4963-8001-1d606ce84a64")
//    Call<ResponseBody> download();

//    @Streaming
//    @GET("dictionary.pdf")
//    Call<ResponseBody> download();

//    @Streaming
//    @GET("/audio/ar/75.mp3")
//    Call<ResponseBody> download();
//"https://www.hisnmuslim.com/audio/ar/1.mp3"
//    @Streaming
//    @GET("o/quran_images.zip?alt=media&token=a5883b3d-cceb-4515-abbf-84879c8d14e0")
//    Call<ResponseBody> download();
    @Streaming
    @GET("o/quran_images.zip?alt=media&token=914afbff-4207-4c6d-ad2f-a79d93dfabae")
    Call<ResponseBody> download();

//    @Streaming
//    @GET("o/quran_images.zip?alt=media&token=8065442b-1f35-4076-966b-4aede8ef09ce")
//    Observable<ResponseBody> download();
}



