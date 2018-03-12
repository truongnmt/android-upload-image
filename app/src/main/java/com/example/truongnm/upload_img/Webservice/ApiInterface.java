package com.example.truongnm.upload_img.Webservice;

/**
 * Created by truongnm on 3/12/18.
 */

import com.example.truongnm.upload_img.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sontbv on 3/7/18.
 */

public interface ApiInterface {
    @GET("upload-image/getImages.php")
    Call<List<Image>> getImages();

}