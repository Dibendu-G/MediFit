package com.example.medifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class phoneoremail extends AppCompatActivity {

    LinearLayout email_choosen,phone_choosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneoremail);

        email_choosen=findViewById(R.id.chooseemail);
        phone_choosen=findViewById(R.id.choicePhone);

        phone_choosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phoneoremail.this,entermobilenumberone.class);
                startActivity(intent);
            }
        });
        email_choosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phoneoremail.this,enteremailid.class);
                startActivity(intent);
            }
        });
    }
}