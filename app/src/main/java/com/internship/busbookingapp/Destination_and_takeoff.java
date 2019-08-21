package com.internship.busbookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_and_takeoff);

        spinner = findViewById(R.id.spinner_one);
        spinner1 = findViewById(R.id.spinner_two);
        intent = new Intent(Destination_and_takeoff.this,LogInActivity.class);

        String[] loc = {"Choose a location","gaba","kampala","mbarara"};
        // list for available distinations
        String[] dist = {"Choose a destination","Kenya","Rwanda","Jinja","Mbarara","DR Congo"};

        /* Copy this code here */
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
                intent.putExtra("loc",selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        /*another one*/
        List<String> disList = new ArrayList<>(Arrays.asList(dist));

        ArrayAdapter<String> distAdapter = new ArrayAdapter<String>(this,R.layout.dest_and_take,disList){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        locAdapter.setDropDownViewResource(R.layout.dest_and_take);
        spinner1.setAdapter(distAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem1 = (String) adapterView.getItemAtPosition(i);
                intent.putExtra("dist",selectedItem1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void goNext(View view){
        startActivity(intent);
    }
}
