package com.felixtechlabs.loginregistrationgooglefacebookfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterUser extends AppCompatActivity {

    private EditText uFullName, uMobile, uPasswordOne, uPasswordTwo;
    Spinner spinner_countries;

    private Button btnRegister;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        uFullName = findViewById(R.id.uFullName);
        uMobile = findViewById(R.id.uMobile);
        spinner_countries = findViewById(R.id.spinner_countries);
        uPasswordOne = findViewById(R.id.uPasswordOne);
        uPasswordTwo = findViewById(R.id.uPasswordTwo);
        btnRegister = findViewById(R.id.btnRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("clients");

        //setting up adapter to spinner
        spinner_countries.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userFullName = uFullName.getText().toString();
                final String userPasswordOne = uPasswordOne.getText().toString();
                final String userPasswordTwo = uPasswordTwo.getText().toString();
                final String userMobile = uMobile.getText().toString().trim();
                final String code = CountryData.countryAreaCodes[spinner_countries.getSelectedItemPosition()];

                if (userFullName.isEmpty()) {
                    uFullName.setError("Please Enter Full Name");
                }
                if (userPasswordOne.isEmpty()) {
                    uPasswordOne.setError("Please password");
                }
                if (userPasswordTwo.isEmpty()) {
                    uPasswordTwo.setError("Please password");
                }
                if (userMobile.isEmpty()) {
                    uMobile.setError("Please email");
                }
                //check wheather passwords are not equal
                if (!userPasswordOne.equals(userPasswordTwo)) {
                    uPasswordTwo.setError("Password didint match");
                    getCurrentFocus();
                }
                String id = firebaseAuth.getUid();

                databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            //it means user already registered
                            //Add code to show your prompt
                            Toast.makeText(RegisterUser.this, "already registered", Toast.LENGTH_SHORT).show();
                        } else {
                            //It is new users
                            //write an entry to your user table
                            //writeUserEntryToDB();
                            //after successful data insertion in the database it will be navigate to another activity where  it will check
                            //otp is correct or not
                            String phone_number = "+" + code + userMobile;
                            Intent intent = new Intent(RegisterUser.this, VerifyPhoneActivity.class);
                            intent.putExtra("fullname", userFullName);
                            intent.putExtra("userpassword", userPasswordTwo);
                            intent.putExtra("usermobile", userMobile);

                            Log.e(phone_number, "onClick:************* ");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}
