package com.internship.busbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class Sign_up_actvity extends AppCompatActivity {
    Button btn_signUp;
    TextView goToLogin;
//    USER SIGN UP INPUTS
    EditText fname,lname,email,location,password;
//    gender button
    RadioGroup gender;
    RadioButton user_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_actvity);
        btn_signUp = findViewById(R.id.btn_signUp);
        goToLogin = findViewById(R.id.goLogIn);
//        user details
        fname = findViewById(R.id.signUpFName);
        lname = findViewById(R.id.signUpLName);
        location = findViewById(R.id.signUpLocation);
        email = findViewById(R.id.signUpEmail);
        password = findViewById(R.id.signUp_password);
        gender = findViewById(R.id.gender);



//        on click on the sign up byn
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGender;
                selectedGender = gender.getCheckedRadioButtonId();
                user_gender = findViewById(selectedGender);
                signUp();
//                Toast.makeText(Sign_up_actvity.this,user_gender.getText(),Toast.LENGTH_SHORT).show();
            }
        });
//        move to the log in page
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_up_actvity.this,LogInActivity.class));

            }
        });
    }
//    create methods for validation and signing up
    public void signUp(){
        if(validate()){
            onSucess();
        }else {
            onFailure("something happened app failed to connect to the database");
        }
    }
//    validate the user information
    public boolean validate(){
        String f_name = fname.getText().toString();
        String l_name = lname.getText().toString();
        String u_location = location.getText().toString();
        String u_email = email.getText().toString();
        String u_password = password.getText().toString();
        String u_gender = user_gender.getText().toString();

        boolean valid;
        if(f_name.isEmpty() || f_name.length() < 3){
            fname.setError("User name must not be empty");
            valid = false;
        }else{
            valid = true;
        }
//        validate last name
        if(l_name.isEmpty() || f_name.length() < 3){
            fname.setError("User last name must not be empty");
            valid = false;
        }else{
            valid = true;
        }
//        validate the location field
        if(u_location.isEmpty() || f_name.length() < 3){
            fname.setError("Please provide a valid location");
            valid = false;
        }else{
            valid = true;
        }
//        validate email
        if(u_email.isEmpty() || f_name.length() < 7){
            fname.setError("Please provide a valid email address");
            valid = false;
        }else{
            valid = true;
        }
//        validate the password feild
        if(u_password.isEmpty() || f_name.length() < 6){
            fname.setError("The password must be greater than 6 characters");
            valid = false;
        }else{
            valid = true;
        }
//        validate the gender
        if(u_gender.isEmpty()){
            fname.setError("Please select a your gender");
            valid = false;
        }else{
            valid = true;
        }

        return valid;
    }

//    create a method to login user if information is validated

    public void onSucess(){
        String f_name = fname.getText().toString();
        String l_name = lname.getText().toString();
        String u_location = location.getText().toString();
        String u_email = email.getText().toString();
        String u_password = password.getText().toString();
        String u_gender = user_gender.getText().toString();
        HashMap hashMap = new HashMap();
        hashMap.put("fname",f_name);
        hashMap.put("lname",l_name);
        hashMap.put("location",u_location);
        hashMap.put("email",u_email);
        hashMap.put("password",u_password);
        hashMap.put("gender",u_gender);

        PostResponseAsyncTask task = new PostResponseAsyncTask(Sign_up_actvity.this, hashMap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s.contains("success")){
                    onFailure("user successfully register");
                    startActivity(new Intent(Sign_up_actvity.this,LogInActivity.class));
                }else {
                    if(s.contains("failed")){
                        onFailure("Failed to register please try again");
                    }
                }
            }
        });
        task.execute("http://10.0.2.2/busAppApi/insert/signUp.php");
    }
//    create a method if the sign up fails
    public void onFailure(String message){
        Toast.makeText(Sign_up_actvity.this,message,Toast.LENGTH_SHORT).show();
    }
}
