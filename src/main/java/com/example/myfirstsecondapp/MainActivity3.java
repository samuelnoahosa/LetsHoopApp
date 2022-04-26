package com.example.myfirstsecondapp;


import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity3 extends AppCompatActivity
{

    EditText DateEdt, LocationEdt, TimeEdt;
    Button SaveBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EventInfo eventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DateEdt = findViewById(R.id.EdtDate);
        LocationEdt = findViewById(R.id.EdtLocation);
        TimeEdt = findViewById(R.id.EdtTime);
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Where the data will be saved the database
        databaseReference = firebaseDatabase.getReference("EventInfo");

        eventInfo =  new EventInfo();// my own class that i created

        SaveBtn = findViewById(R.id.btnSave);

        // Saves data about event into firebase
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = DateEdt.getText().toString();
                String location = LocationEdt.getText().toString();
                String time = TimeEdt.getText().toString();

                if (TextUtils.isEmpty(date) || TextUtils.isEmpty(location) || TextUtils.isEmpty(time)){//User cannot leave any field empty
                    Toast.makeText(MainActivity3.this, "Please add some Event info to fields..", Toast.LENGTH_SHORT).show();
                } else {
                    addDataToFirebase(date, location, time);

                    startActivity(new Intent(MainActivity3.this, AnnounceActivity.class));
                    finish();
                }
            }
        });



        Button btn = findViewById(R.id.btnBack);// find the button by its ID


        btn.setOnClickListener(new View.OnClickListener() {// takes us back in to the previous activity
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
                finish();

            }
        });


    }
//Adds our data to the firebase
    private void addDataToFirebase(String date, String location, String time){
//calling our getters and setters that we created in the class
        eventInfo.setDate(date);
        eventInfo.setLocation(location);
        eventInfo.setTime(time);

//Destination
                //databaseReference.setValue(eventInfo);
                databaseReference.child("Events").push().setValue(eventInfo);
                Toast.makeText(MainActivity3.this, "Data Added!", Toast.LENGTH_SHORT).show();//lets us know when it is done

               // Toast.makeText(MainActivity3.this, "Failed attempt to add data" + error, Toast.LENGTH_SHORT).show();

    }

}