package com.example.medifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout signEmail,signName,signPhoneno,signpswd;
    TextView backtolgin;
    Button signbtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backtolgin = findViewById(R.id.backtologin);
        signEmail=findViewById(R.id.singupemail);
        signName=findViewById(R.id.fullname);
        signPhoneno=findViewById(R.id.phonenumber);
        signpswd=findViewById(R.id.signpswd);
        signbtn=findViewById(R.id.btnsignup);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                String name = signName.getEditText().getText().toString();
                String email = signEmail.getEditText().getText().toString();
                String phoneNo = signPhoneno.getEditText().getText().toString();
                String password = signpswd.getEditText().getText().toString();
                userHelper helperClass = new userHelper(name, email, phoneNo,password);
                reference.child(phoneNo).setValue(helperClass);
                Intent a = new Intent(SignUp.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        });

        backtolgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(SignUp.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        });
    }
}