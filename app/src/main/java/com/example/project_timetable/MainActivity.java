package com.example.project_timetable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Adapder_actual pAdapter;
    ListView listView;
    public static int keyID;
    String a;
    private List<mask_actual> listProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView ivProducts = findViewById(R.id.listActual);//Находим лист в который будем класть наши объекты
        pAdapter = new Adapder_actual(MainActivity.this, listProduct); //Создаем объект нашего адаптера
        ivProducts.setAdapter(pAdapter); //Cвязывает подготовленный список с адаптером
        listView = findViewById(R.id.listActual);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyID = (int) id;
                a= String.valueOf(id);
                Go();
            }
        });
        new GetProducts().execute();
    }
    private class GetProducts extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5001/NGKNN/зеленцовдр/api/Actual_timetable");//Строка подключения к нашей API
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //вызываем нашу API

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();

            } catch (Exception exception) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray tempArray = new JSONArray(s);//преоброзование строки в json массив
                for (int i = 0; i < tempArray.length(); i++) {

                    JSONObject productJson = tempArray.getJSONObject(i);//Преобразование json объекта в нашу структуру
                    mask_actual tempProduct = new mask_actual(
                            productJson.getInt("Id_lesson"),
                            productJson.getString("Lesson"),
                            productJson.getString("Classroom"),
                            productJson.getString("Subgroup"),
                            productJson.getString("Count")

                    );
                    listProduct.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();
                }
            } catch (Exception ignored) {

            }
        }
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
    public void Go() {
        Intent intent = new Intent(this, Edit_actual_timetable.class);
        intent.putExtra("actual_timetable",a);
        startActivity(intent);
    }
}