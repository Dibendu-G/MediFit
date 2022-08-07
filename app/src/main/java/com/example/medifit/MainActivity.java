package com.example.medifit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

        GoogleSignInOptions gso;
        GoogleSignInClient gsc;
        ImageView googlebutton;
        Button loginbtn;
        FirebaseAuth fAuth;
        TextInputLayout lemail,lpassword;
        TextView frgtpassword,newregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn=findViewById(R.id.btlogin);
        googlebutton=findViewById(R.id.btgoogle);
        frgtpassword=findViewById(R.id.forgetpassword);
        lemail=findViewById(R.id.email);
        lpassword=findViewById(R.id.password);
        newregister=findViewById(R.id.newregister);
        fAuth = FirebaseAuth.getInstance();

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = lemail.getEditText().getText().toString().trim();
                String passwd = lpassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    lemail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(passwd))
                {
                    lpassword.setError("Password is Reuired");
                    return;
                }
                if(passwd.length()<6)
                {
                    lpassword.setError("Password is too short");
                    return;
                }

//                Authenticate
                fAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,SecondAcrivity.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,SignUp.class);
                startActivity(a);
                finish();
            }
        });

        frgtpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,entermobilenumberone.class);
                startActivity(i);
                finish();
            }
        });

        googlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
    }

        void signin(){
            Intent signInInetent = gsc.getSignInIntent();
            startActivityForResult(signInInetent,1000);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"Something went Wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }
    void navigateToSecondActivity()
    {
        finish();
        Intent intent = new Intent(MainActivity.this,SecondAcrivity.class);
        startActivity(intent);
    }
}