package com.example.medifit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class enteremailid extends AppCompatActivity {


    private TextInputEditText txtEmail;
    private Button forgetBtn;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enteremailid);

        auth= FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.input_email_id);
        forgetBtn = findViewById(R.id.buttongetlink);

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    private void validateData()
    {
        email = txtEmail.getText().toString();
        if(email.isEmpty())
        {
            txtEmail.setError("Required");
        }
        else {
            forgetpass();
        }
    }

    private void forgetpass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(enteremailid.this,"Check Your Email",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(enteremailid.this,passwordResetMessage.class));
                    finish();
                }
                else {
                    Toast.makeText(enteremailid.this,"Error : "+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}