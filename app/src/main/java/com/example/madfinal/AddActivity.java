package com.example.madfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

//Insert details
public class AddActivity extends AppCompatActivity {

    EditText fname,mobile,email,menu,date;
    Button btnAdd, btnBack;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        fname=(EditText)findViewById(R.id.txtName);
        mobile=(EditText)findViewById(R.id.txtMobile);
        email=(EditText)findViewById(R.id.txtEmail);
        menu=(EditText)findViewById(R.id.txtMenu);
        date=(EditText)findViewById(R.id.txtDate);
        btnAdd=(Button)findViewById(R.id.Add);
        btnBack=(Button)findViewById(R.id.Back);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //add validation

        awesomeValidation.addValidation(this,R.id.txtEmail,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.txtName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.txtMobile,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.txtMenu,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext()
                            ,"Success",Toast.LENGTH_SHORT).show();
                    inserdata();
                }else {
                    Toast.makeText(getApplicationContext()
                            ,"Failed",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            //actvity go back
            public void onClick(View v) {
                finish();
            }
        });
    }

    //insert data to database
    private void inserdata(){
        Map<String,Object> map = new HashMap<>();
        map.put("fname",fname.getText().toString());
        map.put("mobile",mobile.getText().toString());
        map.put("email",email.getText().toString());
        map.put("date",date.getText().toString());
        map.put("menu",menu.getText().toString());
        //Database
        //acces database
        FirebaseDatabase.getInstance().getReference().child("ResDetails").push()
                .setValue(map)

                //successfull
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //shpw insert successfully toast message
                        Toast.makeText(AddActivity.this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                    //if failed exc
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                            //shpw insert unsuccessfully toast message
                        Toast.makeText(AddActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}