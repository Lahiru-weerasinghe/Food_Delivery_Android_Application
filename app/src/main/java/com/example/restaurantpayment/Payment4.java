package com.example.restaurantpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment4 extends AppCompatActivity {
    Button button4_1;
    Button button4_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment4);
        OnclickButtonLister4();
        OnclickButtonLister5();
    }
    public void OnclickButtonLister4(){
        button4_1= (Button)findViewById(R.id.p4_bttn1);
        button4_1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent4_1 = new Intent(Payment4.this, Payment3.class);
                        startActivity(intent4_1);


                    }
                }
        );
    }

    public void OnclickButtonLister5(){
        button4_2= (Button)findViewById(R.id.p4_bttn2);
        button4_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent4_2 = new Intent(Payment4.this, Payment5.class);
                        startActivity(intent4_2);


                    }
                }
        );
    }
}