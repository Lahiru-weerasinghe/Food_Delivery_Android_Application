package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    MainAdapter mainAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView)findViewById(R.id.rv);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //get databasequery data to model
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        //To Querying the data
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ResDetails"), MainModel.class)
                        .build();


        mainAdapter = new MainAdapter(options);
        recyclerview.setAdapter(mainAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert page
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });

    }

    @Override
    //visble to customer
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    //no visible
    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}
