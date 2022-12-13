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

public class Wednesday extends AppCompatActivity {
    private Adapter pAdapter;
    ListView listView;
    public static int keyID;
    private List<Mask> listProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday);
        ListView ivProducts = findViewById(R.id.timetable);//Находим лист в который будем класть наши объекты
        pAdapter = new Adapter(Wednesday.this, listProduct); //Создаем объект нашего адаптера
        ivProducts.setAdapter(pAdapter); //Cвязывает подготовленный список с адаптером
        listView = findViewById(R.id.timetable);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyID = (int) id;
                Go();
            }
        });
        new GetProducts().execute();
    }
    private class GetProducts extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5001/NGKNN/зеленцовдр/api/Wednesdays");//Строка подключения к нашей API
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //вызываем нашу API

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString(); //значит только пермиссия на интернет нужна была!

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
                    Mask tempProduct = new Mask(
                            productJson.getInt("Id_lesson"),
                            productJson.getString("Lesson"),
                            productJson.getString("Classroom"),
                            productJson.getString("Teacher")

                    );

                    listProduct.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();
                }
            } catch (Exception ignored) {

            }
        }
    }
    public void Go() {
        startActivity(new Intent(this, Edit_wednesday.class));
    }

    public void backClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}