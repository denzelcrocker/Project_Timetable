package com.example.project_timetable;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Edit_actual_timetable extends AppCompatActivity implements View.OnClickListener{

    EditText Lesson;
    EditText Classroom;
    EditText Subgroup;
    EditText Count;
    Button Change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_actual_timetable);
        Lesson = findViewById(R.id.lessonEdit);
        Classroom = findViewById(R.id.classroomEdit);
        Subgroup = findViewById(R.id.subgroupEdit);
        Count = findViewById(R.id.countEdit);
        Change = findViewById(R.id.Change);
        Change.setOnClickListener(this);
        showAll();
    }
    private void showAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/ngknn/зеленцовдр/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI_Actual retrofitAPI_actual = retrofit.create(RetrofitAPI_Actual.class);
        Call<DataModal_Actual> call = retrofitAPI_actual.getDATA(MainActivity.keyID);
        call.enqueue(new Callback<DataModal_Actual>() {

            @Override
            public void onResponse(Call<DataModal_Actual> call, Response<DataModal_Actual> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Edit_actual_timetable.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
                }
                Lesson.setText(response.body().getLesson());
                Classroom.setText(response.body().getClassroom());
                Subgroup.setText(response.body().getSubgroup());
                Count.setText(response.body().getCount());
            }

            @Override
            public void onFailure(Call<DataModal_Actual> call, Throwable t) {
                Toast.makeText(Edit_actual_timetable.this, "При выводе данных возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    private void getChangeRow(String Lesson, String Classroom, String Subgroup, String Count) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/зеленцовдр/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI_Actual retrofitAPI_actual = retrofit.create(RetrofitAPI_Actual.class);

        DataModal_Actual modal = new DataModal_Actual(Lesson, Classroom, Subgroup, Count);
        Call<DataModal_Actual> call = retrofitAPI_actual.updateData(MainActivity.keyID, modal);
        call.enqueue(new Callback<DataModal_Actual>() {
            @Override
            public void onResponse(Call<DataModal_Actual> call, Response<DataModal_Actual> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(Edit_actual_timetable.this, "В процессе изменения данных произошла ошибка", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Edit_actual_timetable.this, "Данные успешно изменены", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<DataModal_Actual> call, Throwable t) {
                Toast.makeText(Edit_actual_timetable.this, "При изменении записи возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Change: {

                String lesson = Lesson.getText().toString();
                String classroom = Classroom.getText().toString();
                String subgroup = Subgroup.getText().toString();
                String count = Count.getText().toString();

                getChangeRow(lesson, classroom, subgroup, count);
                new Handler().postDelayed(() -> startActivity(
                        new Intent(Edit_actual_timetable.this, MainActivity.class)), 1000);


            }
            break;

        }
    }
}