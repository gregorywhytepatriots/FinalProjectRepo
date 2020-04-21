package com.teamawesome.sqliteloginapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

// User info  is deleted or updated by using User id.

public class EditPage extends AppCompatActivity {
    DatabaseHelper db;
    Button  viewBtn, deleteBtn, updateInfoBtn;
    EditText editTextId, editTextName, editTextEmail, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        db = new DatabaseHelper(this);

        editTextId = (EditText) findViewById(R.id.id);
        editTextName = (EditText) findViewById(R.id.name);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        viewBtn = (Button) findViewById(R.id.viewAllBtn);
        deleteBtn = (Button) findViewById(R.id.deletebtn);
        updateInfoBtn = (Button) findViewById(R.id.updatebtn);
        updateInfo();
        viewAllBtn();
        deleteBtn();
    }

    public void logout(View view){
        Intent intent = new Intent(EditPage.this, LoginActivity.class);
        startActivity(intent);
    }

    public void updateInfo(){
        updateInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = db.updateData(editTextId.getText().toString(), editTextName.getText().toString(),
                        editTextEmail.getText().toString(), editTextPassword.getText().toString());
                if(isUpdated == true){
                    Toast.makeText(EditPage.this, "User Update", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditPage.this, "User not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deleteBtn(){
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = db.deleteData(editTextId.getText().toString());
                if(deletedRows >0){
                    Toast.makeText(EditPage.this, "User Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditPage.this, "User not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    public void viewAllBtn(){
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = db.getAllData();
                if(res.getCount() == 0){
                    showMessage("Information", "No User Registered");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Id :" + res.getString(0)+ "\n");
                        buffer.append("Name : " + res.getString(1)+ "\n");
                        buffer.append("Email : " + res.getString(2)+ "\n");
                        buffer.append("Password : " + res.getString(3)+ "\n\n");
                    }

                    //Show all data
                    showMessage("User Info", buffer.toString());
                }


            }
        });

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
