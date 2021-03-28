package com.tarun.internassignment.Retrofits;

import com.tarun.internassignment.Models.CrewDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("v4/crew")
    Call<List<CrewDataModel>> getAllData();
}
