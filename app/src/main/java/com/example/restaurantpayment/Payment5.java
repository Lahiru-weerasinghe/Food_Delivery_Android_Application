package com.example.restaurantpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Payment5 extends AppCompatActivity {

    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment5);
        OnclickButtonLister5();
    }
    public void OnclickButtonLister5(){
        button5= (Button)findViewById(R.id.p5_bttn1);
        button5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent5 = new Intent(Payment5.this, Payment6.class);
                        startActivity(intent5);
                    }
                }
        );
    }
}