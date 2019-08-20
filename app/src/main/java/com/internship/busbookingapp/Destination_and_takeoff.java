package com.internship.busbookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Destination_and_takeoff extends AppCompatActivity {
    Spinner spinner,spinner1;
    String selectedItem,selectedItem1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_and_takeoff);

        spinner = findViewById(R.id.spinner_one);

        String[] loc = {"Choose a location","gaba","kampala","mbarara"};

        List<String> locList = new ArrayList<>(Arrays.asList(loc));

        ArrayAdapter<String> locAdapter = new ArrayAdapter<String>(this,R.layout.dest_and_take,locList){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        locAdapter.setDropDownViewResource(R.layout.dest_and_take);
        spinner.setAdapter(locAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        another one
        /*
        spinner1.setAdapter(locAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem1 = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */

    }
}
