package com.internship.busbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Bus_for_given_dest extends AppCompatActivity {
    ListView desc_buses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_for_given_dest);

        desc_buses = findViewById(R.id.desc_buses);

        //Get data from database
        getJSON("http://10.0.2.2/BusAppApi/select/desc_loc.php");
    }


    //    creating a private method called getJSON
    private void getJSON(final String urlWebService){
//        create  a private class
        class GetJSON extends AsyncTask<Void,Void, String> {

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
        desc_buses.setAdapter(arrayAdapter);
    }
}
