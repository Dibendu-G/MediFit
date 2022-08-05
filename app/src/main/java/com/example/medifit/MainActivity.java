package com.example.medifit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

        GoogleSignInOptions gso;
        GoogleSignInClient gsc;
        ImageView googlebutton;
        TextView frgtpassword,newregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googlebutton=findViewById(R.id.btgoogle);
        frgtpassword=findViewById(R.id.forgetpassword);
        newregister=findViewById(R.id.newregister);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

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