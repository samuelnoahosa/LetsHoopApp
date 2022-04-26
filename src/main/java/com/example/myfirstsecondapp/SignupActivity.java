package com.example.myfirstsecondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
// makes the classes of the firebase package accessible
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
//gives us a little structure to our database. we can now create parents and childs to saved data
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    //FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFullName = findViewById(R.id.FullName);
        mEmail = findViewById(R.id.txtEmail);
        mPassword = findViewById(R.id.txtWelcome);
        mPhone = findViewById(R.id.PhoneNumber);
        mRegisterBtn = findViewById(R.id.RegisterBtn);
        mLoginBtn = findViewById(R.id.LoginBtn);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);



        if (fAuth.getCurrentUser() != null) {// if a user already exists and is logged in on device launch the directly to next activity
            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String fullName = mFullName.getText().toString().trim();
// Requirements for the log in process
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be at Least 6 characters long.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);// When the buton is pressed a progress bar or circle will appear

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //registered, now save in realtime database with structure
                            HashMap<String , Object> map = new HashMap<>();
                            map.put("name" , fullName);
                            map.put("email", email);

                            FirebaseDatabase.getInstance().getReference().child("hoopusers").push().updateChildren(map);

                            Toast.makeText(SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                        } else {
                            Toast.makeText(SignupActivity.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            ;
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {// already a user? click here.
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}