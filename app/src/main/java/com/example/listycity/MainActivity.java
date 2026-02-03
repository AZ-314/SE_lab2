package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> dataList;
    ListView cityList ;
    ArrayAdapter<String> cityAdapter ;
    Button addButton ;
    Button deleteButton ;
    Button confirmButton ;
    EditText cityInput ;
    int selectedIndex = -1  ;
    boolean isAdding = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button) ;
        deleteButton = findViewById(R.id.delete_city);
        confirmButton = findViewById(R.id.confirm_button) ;
        cityInput = findViewById(R.id.city_input) ;

        String []cities = {"Lahore", "Kasur", "Faisalabad"} ;
        dataList = new ArrayList<>() ;
        dataList.addAll(Arrays.asList(cities)) ;
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList) ;
        cityList.setAdapter(cityAdapter) ;

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });


        deleteButton.setOnClickListener(v -> {
            if (selectedIndex >= 0 && selectedIndex < dataList.size()) {
                dataList.remove(selectedIndex);
                cityAdapter.notifyDataSetChanged();
                selectedIndex = -1;
            }
        });

        addButton.setOnClickListener(v -> {
            isAdding = true;

            cityInput.setText("");
            cityInput.setEnabled(true);
            cityInput.requestFocus();
        });


        confirmButton.setOnClickListener(v -> {

            if (!isAdding) {
                return;
            }

            String cityName = cityInput.getText().toString().trim();

            if (cityName.isEmpty()) {
                return;
            }

            dataList.add(cityName);
            cityAdapter.notifyDataSetChanged();

            cityInput.setText("");
            cityInput.setEnabled(false);
            isAdding = false;
        });


    }
}