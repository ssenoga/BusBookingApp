package com.internship.busbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.HashMap;

public class Landing_PageActivity extends AppCompatActivity {
    TextView h1;
    String selectedBusCompany;

//    list view
    ListView list;
    String[] title;
    String[] info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing__page);
        list = findViewById(R.id.company_Buses);
        h1 = findViewById(R.id.bus_heading);
        selectedBusCompany = getIntent().getStringExtra("bus_company");
        h1.setText("Thank You For Choosing "+selectedBusCompany);
        Toast.makeText(this, selectedBusCompany, Toast.LENGTH_SHORT).show();

        getCompanyBuses("http://10.0.2.2/BusAppApi/select/selectCompanyBuses.php");

//        add an event listner to the list item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBus = (String) adapterView.getItemAtPosition(i);
//                create an intent
                Intent intent = new Intent(Landing_PageActivity.this,Payment_method_activity.class);
                intent.putExtra("selected_bus",selectedBus);
                startActivity(intent);
            }
        });

    }



//    get all json values of the buses
    private void getCompanyBuses(final String webLink){
//        a class to extend the asnyc tasks
        class GetCompanyBuses extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPostExecute(String s) {
                try {

                    addToUi(s);
//                    sendData();
                } catch (JSONException ex){
                    ex.printStackTrace();
                }
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try{
//                    sendData(selectedBusCompany);
                    URL url = new URL(webLink);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = reader.readLine()) != null){
                        sb.append(json+'\n');
                    }
                    return sb.toString().trim();
                }catch (Exception e){}
                return null;
            }
        }//end of class
        GetCompanyBuses buses = new GetCompanyBuses();
        buses.execute();
    }//end of method

//    add the bus to the list view
    private void addToUi(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] responseH1 = new String[jsonArray.length()];
//        String responseBody[] = new String[jsonArray.length()];
        for(int i =0;i<jsonArray.length();i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            responseH1[i] = obj.getString("bus_name");
        }
//        set the adapter
        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.bus_details,R.id.busHeading,responseH1);
        list.setAdapter(adapter);
    }

//    private void sendData(String data){
//        //    send the selected bus
//        HashMap hashMap = new HashMap();
//        hashMap.put("selectedBusCompany",data);
//        PostResponseAsyncTask task = new PostResponseAsyncTask(Landing_PageActivity.this, hashMap, new AsyncResponse() {
//            @Override
//            public void processFinish(String s) {
//                if(s.contains("failed")){
//                    Toast.makeText(Landing_PageActivity.this, "Please select a bus company", Toast.LENGTH_SHORT).show();
//                }else {
//                    if(s.contains("sucess")){
//                        Toast.makeText(Landing_PageActivity.this, "Abus is selected", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//        });
//        task.execute("http://10.0.2.2/BusAppApi/select/selectCompanyBuses.php");
//
//    }


}
