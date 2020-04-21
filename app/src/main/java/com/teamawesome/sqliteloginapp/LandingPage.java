package com.teamawesome.sqliteloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {
            Button camerabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        camerabtn = (Button) findViewById(R.id.camera);

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, CameraPage.class);
                startActivity(intent);

            }
        });


    }

    public void Editbtn(View v){
        Intent intent= new Intent(LandingPage.this, EditPage.class);
        startActivity(intent);
    }
}
