package com.example.project_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mondayClick(View view) {
        Intent intent = new Intent(this, Monday.class);
        startActivity(intent);
    }
    public void tuesdayClick(View view) {
        Intent intent = new Intent(this, Tuesday.class);
        startActivity(intent);
    }
    public void wednesdayClick(View view) {
        Intent intent = new Intent(this, Wednesday.class);
        startActivity(intent);
    }
    public void thursdayClick(View view) {
        Intent intent = new Intent(this, Thursday.class);
        startActivity(intent);
    }
    public void fridayClick(View view) {
        Intent intent = new Intent(this, Friday.class);
        startActivity(intent);
    }
    public void saturdayClick(View view) {
        Intent intent = new Intent(this, Saturdsay.class);
        startActivity(intent);
    }
}