package com.example.restaurantpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment3 extends AppCompatActivity {
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment3);
        OnclickButtonLister3();
    }
    public void OnclickButtonLister3(){
        button3= (Button)findViewById(R.id.p3_bttn1);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3 = new Intent(Payment3.this, Payment4.class);
                        startActivity(intent3);
                    }
                }
        );
    }
}