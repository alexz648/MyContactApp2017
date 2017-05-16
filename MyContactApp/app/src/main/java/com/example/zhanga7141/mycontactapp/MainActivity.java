package com.example.zhanga7141.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        editPhone = (EditText) findViewById(R.id.editText_phone);
        editEmail = (EditText) findViewById(R.id.editText_email);
    }

    public void addData(View v){
         boolean isInserted = myDb.insertData(editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());

        if(isInserted == true) {
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_SHORT).show();
        }

        else {
            Log.d("MyContact", "Data insertion NOT successful");
            //Create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "unsuccessful", Toast.LENGTH_SHORT).show();

        }
    }

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //put a Log.d message and toast
            Log.d("MyDatabase", "No data found in database");
            Toast.makeText(getApplicationContext(), "No data found in database", Toast.LENGTH_SHORT);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup loop with moveToNext method
        //      append each COL (method append in string buffer class) to buffer
        //      use getString method

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {

    }
}
