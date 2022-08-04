package com.example.medifit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SecondAcrivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acrivity);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        signout=findViewById(R.id.signoutBtn);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null)
        {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signout.setOnClickListener(view -> signOut());

            }

            void signOut()
            {
                gsc.signOut().addOnCompleteListener(task -> {
                        finish();
                        startActivity(new Intent(SecondAcrivity.this,MainActivity.class));
                });
            }
}