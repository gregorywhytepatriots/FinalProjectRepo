package com.teamawesome.sqliteloginapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button registerBtn, loginBtn;
    EditText email, password, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseHelper(this);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        registerBtn = (Button)findViewById(R.id.register);
        loginBtn = (Button)findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class );
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String nameInfo = name.getText().toString();
                String emailInfo = email.getText().toString();
                String passwordInfo = password.getText().toString();
                
                insertData(nameInfo, emailInfo, passwordInfo);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void insertData(String name, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME, name);
        contentValues.put(DatabaseHelper.COL_EMAIL, email);
        contentValues.put(DatabaseHelper.COL_PASSWORD, password);

        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}
