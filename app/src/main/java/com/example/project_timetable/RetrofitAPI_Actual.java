package com.example.project_timetable;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI_Actual {

    @GET("Actual_timetable/{id}")
    Call<DataModal_Actual> getDATA(@Path("id") int id);

    @POST("Actual_timetable")
    Call<DataModal_Actual> createPost(@Body DataModal_Actual dataModal);

    @PUT("Actual_timetable/{id}")
    Call<DataModal_Actual> updateData(@Query("id") int id, @Body DataModal_Actual dataModal);

    @DELETE("Actual_timetable/{id}")
    Call<Void> deleteData(@Path("id") int id);
}

