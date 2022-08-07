package com.example.medifit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout signEmail,signName,signPhoneno,signpswd;
    TextView backtolgin;
    Button signbtn;
    FirebaseAuth fAuth;
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
        fAuth = FirebaseAuth.getInstance();

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                String name = signName.getEditText().getText().toString().trim();
                String email = signEmail.getEditText().getText().toString().trim();
                String phoneNo = signPhoneno.getEditText().getText().toString().trim();
                String password = signpswd.getEditText().getText().toString().trim();

                if(fAuth.getCurrentUser() != null)
                {
                    Intent a = new Intent(SignUp.this,MainActivity.class);
                    finish();
                }

                if(TextUtils.isEmpty(email))
                {
                    signEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    signpswd.setError("Password is Reuired");
                    return;
                }
                if(password.length()<6)
                {
                    signpswd.setError("Password is too short");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(SignUp.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                userHelper helperClass = new userHelper(name, email, phoneNo,password);
                reference.child(phoneNo).setValue(helperClass);
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