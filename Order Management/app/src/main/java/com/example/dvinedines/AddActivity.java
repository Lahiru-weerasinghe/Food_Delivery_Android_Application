package com.example.dvinedines;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    //Initialize Variable
    EditText name,menucode,address,phone,email,no_of_oders,date,time;
    Button btnAdd,btnBack;

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //Assign Variable
        name = (EditText)findViewById(R.id.txtName);
        menucode = (EditText)findViewById(R.id.txtMenuCode);
        address = (EditText)findViewById(R.id.txtAddress);
        phone = (EditText)findViewById(R.id.txtMobile);
        email = (EditText)findViewById(R.id.txtEmail);
        no_of_oders = (EditText)findViewById(R.id.txtOrders);
        date = (EditText)findViewById(R.id.txtDate);
        time = (EditText)findViewById(R.id.txtTime);

        btnAdd = (Button)findViewById(R.id.btnSave);
        btnBack = (Button)findViewById(R.id.btnBack);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Name
        awesomeValidation.addValidation(this,R.id.txtName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //For Mobile
       // awesomeValidation.addValidation(this,R.id.txtMobile,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);
        //For Email
        awesomeValidation.addValidation(this,R.id.txtEmail,Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //Add required
        awesomeValidation.addValidation(this,R.id.txtMenuCode,RegexTemplate.NOT_EMPTY,R.string.invalid_menu);
        awesomeValidation.addValidation(this,R.id.txtAddress,RegexTemplate.NOT_EMPTY,R.string.invalid_address);
        awesomeValidation.addValidation(this,R.id.txtDate,RegexTemplate.NOT_EMPTY,R.string.invalid_date);
        awesomeValidation.addValidation(this,R.id.txtTime,RegexTemplate.NOT_EMPTY,R.string.invalid_time);
        awesomeValidation.addValidation(this,R.id.txtOrders,RegexTemplate.NOT_EMPTY,R.string.invalid_orders);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()){
                    //Toast.makeText(getApplicationContext()
                           // ,"Validation Success",Toast.LENGTH_SHORT).show();
                    insertData();
                    clearAll();
                }else {
                    Toast.makeText(getApplicationContext()
                            ,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("menucode",menucode.getText().toString());
        map.put("address",address.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("email",email.getText().toString());
        map.put("no_of_oders",no_of_oders.getText().toString());
        map.put("date",date.getText().toString());
        map.put("time",time.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("orders").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivity.this,"Error While Insertion", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll()
    {
        name.setText("");
        menucode.setText("");
        address.setText("");
        phone.setText("");
        email.setText("");
        no_of_oders.setText("");
        date.setText("");
        time.setText("");
    }
}