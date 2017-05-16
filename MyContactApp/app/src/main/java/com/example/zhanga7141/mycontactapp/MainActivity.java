package com.example.zhanga7141.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editEmail;
    EditText editPhone;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editName = (EditText) findViewById(R.id.editText_phone);
        editName = (EditText) findViewById(R.id.editText_email);
    }

    public void addData(View v){
         boolean isInserted = myDb.insertData(editName.getText().toString());

        if(isInserted == true) {
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT);
        }

        else {
            Log.d("MyContact", "Data insertion NOT successful");
            //Create toast message to user indicating data inserted incorrectly

        }
    }
}
