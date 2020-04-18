package com.teamawesome.sqliteloginapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
        SQLiteDatabase db;
        SQLiteOpenHelper openHelper;
        EditText email, password;
        Button loginBtn;
        Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper  = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        email = (EditText)  findViewById(R.id.email);
        password = (EditText)  findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String emailInfo= email.getText().toString();
                    String passInfo= password.getText().toString();
                    cursor = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_EMAIL+
                            "=? AND " + DatabaseHelper.COL_PASSWORD+ "=?", new String[]{emailInfo, passInfo} );
                    if(cursor!=null){
                        if(cursor.getCount()>0){
                            Toast.makeText(getApplicationContext(), "login successfull", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, LandingPage.class );
                            startActivity(intent);
                        } else{
                            Toast.makeText(getApplicationContext(), "Not able to Login", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }



}
