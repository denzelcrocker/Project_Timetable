package com.example.project_timetable;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("Mondays/{id}")
    Call<DataModal> getDATA(@Path("id") int id);

    @POST("Mondays")
    Call<DataModal> createPost(@Body DataModal dataModal);

    @PUT("Mondays/{id}")
    Call<DataModal> updateData(@Query("id") int id, @Body DataModal dataModal);



}

