package com.example.project_timetable;

import static java.lang.Integer.parseInt;

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
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    EditText Code;
    Button Change;
    Connection connection;
    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_actual_timetable);
        Lesson = findViewById(R.id.lessonEdit);
        Classroom = findViewById(R.id.classroomEdit);
        Subgroup = findViewById(R.id.subgroupEdit);
        Count = findViewById(R.id.countEdit);
        Change = findViewById(R.id.Change);
        Code = findViewById(R.id.code);
        Change.setOnClickListener(this);
        Intent intent = getIntent();

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

        try {
            ConectionHellper conectionHellper = new ConectionHellper();
            connection = conectionHellper.connectionClass();
            Intent intent = getIntent();
            String id = (intent.getStringExtra("actual_timetable"));
            String a=id;
            if (connection != null) {
                String query11 = "select Id_lesson from Actual_timetable where Count = '" + id + "'";
                Statement statement11 = connection.createStatement();
                ResultSet resultSet11 = statement11.executeQuery(query11);
                int i = 0;
                while (resultSet11.next())
                {
                    i=resultSet11.getInt(1);
                }
                String query12 = "update Actual_timetable set Lesson = '" + Lesson + "', Classroom ='" + Classroom + "',Subgroup ='" + Subgroup + "', Count = '"+ Count +"' where Id_lesson = "+id+"";
                Statement statement12 = connection.createStatement();
                statement12.execute(query12);
                finish();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Данные успешно изменены", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public void Back_bt(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Change: {
                if (Code.getText().toString().equals("1234")) {
                    String lesson = Lesson.getText().toString();
                    String classroom = Classroom.getText().toString();
                    String subgroup = Subgroup.getText().toString();
                    String count = Count.getText().toString();

                    getChangeRow(lesson, classroom, subgroup, count);
                    new Handler().postDelayed(() -> startActivity(
                            new Intent(Edit_actual_timetable.this, MainActivity.class)), 1000);
                }
                else
                {
                    Toast.makeText(Edit_actual_timetable.this, "Введен неверный год старосты", Toast.LENGTH_SHORT).show();                }

            }
            break;

        }
    }
}