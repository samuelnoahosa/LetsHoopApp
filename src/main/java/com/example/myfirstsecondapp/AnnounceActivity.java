package com.example.myfirstsecondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AnnounceActivity extends AppCompatActivity {


    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        // lstView = findViewById(R.id.eventList);

        ArrayList<String>  list   = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_announce,list );
        lstView.setAdapter(adapter);
        //Retrieving data from firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("hoopusers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot2 : snapshot.getChildren()) {
                    list.add(snapshot2.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

