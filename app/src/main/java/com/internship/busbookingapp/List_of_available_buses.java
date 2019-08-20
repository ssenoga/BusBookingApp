package com.internship.busbookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class List_of_available_buses extends AppCompatActivity {
    ListView sample_list;
//    String buses[] = {"yy","gaga","jaguar","global"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_available_buses);

        sample_list = findViewById(R.id.sample_list);

        sample_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value =  (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(List_of_available_buses.this,Destination_and_takeoff.class);
                intent.putExtra("bus_company",value);
                startActivity(intent);

            }
        });


            //CALL THE getJSON METHOD
        getJSON("http://10.0.2.2/BusAppApi/select/displayBuses.php");



    }

    //    creating a private method called getJSON
    private void getJSON(final String urlWebService){
//        create  a private class
        class GetJSON extends AsyncTask<Void,Void, String>{

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
//                    invoking the addUserIntoListView method
                    addUsersIntoListView(s);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                super.onPostExecute(s);
            }


            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
//                    loop through all the json string object from our url
                    while((json = bufferedReader.readLine()) != null){
//                        store our object into the string builder
                        sb.append(json+'\n');
                    }
                    return sb.toString().trim();
                } catch (Exception e){}
                return null;
            }// end of doInBackground method
        }// end of GetJSON class

//        RUN THE GetJSON CLASS
        GetJSON getJSONObj = new GetJSON();
        getJSONObj.execute();

    }// end of getJSON method


    //    creating a method to load our users into the list view
    private void addUsersIntoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        String[] responseArray = new String[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++){
//            creating the JSON object to get values by column names
            JSONObject object = jsonArray.getJSONObject(i);
            responseArray[i]=object.getString("company_name");
        }
//        set the array adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.buses_available,R.id.textView,responseArray);
        sample_list.setAdapter(arrayAdapter);
    }



}
