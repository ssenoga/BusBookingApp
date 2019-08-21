package com.internship.busbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Payment_method_activity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] methods = {"Airtel Mobile Money","Mtn Mobile Money","PayPal","Credit Card"};
    String loc, dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_activity);
        listView = findViewById(R.id.view_payment);
        textView = findViewById(R.id.payment_methods);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.payment_m_resource,R.id.payment_methods,methods);
        listView.setAdapter(arrayAdapter);



    }
}
