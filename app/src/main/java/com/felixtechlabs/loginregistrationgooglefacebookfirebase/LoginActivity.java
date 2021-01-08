package com.felixtechlabs.loginregistrationgooglefacebookfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText uMobile;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    Spinner spinner_countries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uMobile = findViewById(R.id.uMobile);
        btnLogin = findViewById(R.id.btnLogin);
        spinner_countries = findViewById(R.id.spinner_countries);

        //setting up adapter to spinner
        spinner_countries.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        firebaseAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userMobile = uMobile.getText().toString();
                final String code = CountryData.countryAreaCodes[spinner_countries.getSelectedItemPosition()];

                //after successful data insertion in the database it will be navigate to another activity where  it will check
                //otp is correct or not
                String phone_number = "+" + code + userMobile;
                Intent intent = new Intent(LoginActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phone_number", phone_number);
                Log.e(phone_number, "onClick:************* ");
                startActivity(intent);
            }
        });

    }

    public void registerUser(View view) {

        startActivity(new Intent(LoginActivity.this, RegisterUser.class));
    }
}
