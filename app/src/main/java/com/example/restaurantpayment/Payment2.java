package com.example.restaurantpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment2 extends AppCompatActivity {

    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);
        OnclickButtonLister2();
    }
    public void OnclickButtonLister2(){
        button2= (Button)findViewById(R.id.p2_bttn);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(Payment2.this, Payment3.class);
                        startActivity(intent2);
                    }
                }
        );
    }
}