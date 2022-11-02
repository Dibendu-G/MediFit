package com.example.medifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {

    Button backbutton,logoutbtn;
    RelativeLayout medreminder,healthtracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        backbutton=findViewById(R.id.backbtn);
        logoutbtn=findViewById(R.id.btlogout);
        medreminder=findViewById(R.id.medbtn);
        healthtracker=findViewById(R.id.trackerbtn);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im = new Intent(dashboard.this,MainActivity.class);
                startActivity(im);
            }
        });

        medreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(dashboard.this,medicinelistadd.class);
                startActivity(in);
            }
        });

        healthtracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(dashboard.this,stepstracker.class);
                startActivity(ic);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"LogOut Successfully! ",Toast.LENGTH_SHORT).show();
            }
        });
    }
}