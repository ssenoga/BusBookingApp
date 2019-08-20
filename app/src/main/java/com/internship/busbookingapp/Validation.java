package com.internship.busbookingapp;


import android.widget.EditText;
import android.widget.Toast;

public  class Validation {

    public boolean loginValidate(EditText name, EditText password){
        boolean valid;
        String u_name = name.getText().toString();
        String _u_pass = password.getText().toString();
        if(u_name.isEmpty()||name.length()<3){
            name.setError("Usernama must be greater than 3 characters");
            valid = false;
        }else {
           valid = true;
        }
//        password validation
        if(_u_pass.isEmpty() || _u_pass.length() < 6){
            password.setError("Password must be greater than 6 characters");
            valid = false;
        }else{
            valid = true;
        }

        return valid;
    }

//    failure methods

}