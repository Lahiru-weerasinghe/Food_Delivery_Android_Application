package com.example.divinedines;

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

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    //initialize variables
    EditText name,email,comment,fdurl;
    Button btnAdd,btnBack;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //assign variables
        name = (EditText)findViewById(R.id.txtName);
        email = (EditText)findViewById(R.id.txtEmail);
        comment = (EditText)findViewById(R.id.txtComment);
        fdurl = (EditText)findViewById(R.id.txtImageUrl);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //add validation for name
        awesomeValidation.addValidation(this,R.id.txtName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        //for email
        awesomeValidation.addValidation(this,R.id.txtEmail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        //for url
        awesomeValidation.addValidation(this,R.id.txtImageUrl,
                Patterns.WEB_URL,R.string.invalid_url);

        //for comment
        awesomeValidation.addValidation(this,R.id.txtComment,
                RegexTemplate.NOT_EMPTY,R.string.invalid_comment);





        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validation
                if (awesomeValidation.validate()) {

                    //on success
                    Toast.makeText(getApplicationContext()
                            , "Data Validate Successfully...", Toast.LENGTH_SHORT).show();
                    insertData();
                    clearAll();

                }

                else {
                    Toast.makeText(getApplicationContext()
                            , "Validation Faild",Toast.LENGTH_SHORT).show();
                }




                //insertData();
                //clearAll();

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
        map.put("email",email.getText().toString());
        map.put("comment",comment.getText().toString());
        map.put("fdurl",fdurl.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("feedback_details").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivity.this,"Error while Insertion.", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    private  void  clearAll()
    {
        name.setText("");
        email.setText("");
        comment.setText("");
        fdurl.setText("");

    }
}