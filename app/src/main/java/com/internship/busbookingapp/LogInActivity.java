package com.internship.busbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class LogInActivity extends AppCompatActivity {
    TextView toSignUpPage;
    Button logIn;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        toSignUpPage = findViewById(R.id.signUpLink);
//        get the user inputs here
        username = findViewById(R.id.userInputName);
        password = findViewById(R.id.userInputPassword);

        toSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this,Sign_up_actvity.class));
            }
        });
        logIn = findViewById(R.id.btn_Login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();

            }
        });


    }
//    create a methods for validating
    public void onLogin(){
        if(onValidate()){
            onSucess();
        }else {
            onFailure("Something went wrong please try again");
        }

    }
//    validate the user information
    public boolean onValidate(){
        boolean valid = true;
        String u_name = username.getText().toString();
        String u_pass = password.getText().toString();
        if(u_name.isEmpty() || u_name.length() < 3){
            username.setError("User name must longer than three characters");
            valid = false;
        }else {
            valid = true;
        }
//        validate the password
        if(u_pass.isEmpty() || u_pass.length() < 4){
            password.setError("Password must be longer than six letters");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
//    create an on login success method
    public void onSucess(){
        String name = username.getText().toString();
        String pass = password.getText().toString();
//        create a hash map for sending data to the Api
        HashMap hashMap = new HashMap();
        hashMap.put("name",name);
        hashMap.put("password",pass);
//        make aconnection to the server
        PostResponseAsyncTask task = new PostResponseAsyncTask(LogInActivity.this, hashMap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s.contains("success")){
                    startActivity(new Intent(LogInActivity.this,Payment_method_activity.class));
                }else {
                    if(s.contains("failed")){
                        onFailure("Invalid log in details please try again");
                    }
                }
            }
        });
//        locate the server which holds the api
        task.execute("http://10.0.2.2/busAppApi/select/logInUser.php");

    }
//    create the login error (failure)
    public void onFailure(String message){
        Toast.makeText(LogInActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
