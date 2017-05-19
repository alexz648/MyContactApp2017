package com.example.zhanga7141.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    EditText searchName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editPhone = (EditText) findViewById(R.id.editText_phone);
        editEmail = (EditText) findViewById(R.id.editText_email);
        searchName = (EditText) findViewById(R.id.editText_search);


    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());

        if (isInserted == true) {
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "Data insertion successful", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MyContact", "Data insertion NOT successful");
            //Create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "Data insertion unsuccessful", Toast.LENGTH_SHORT).show();

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

        while(res.moveToNext())
        {
            for (int col = 1; col <= 3; col++)
            {
                buffer.append(res.getString(col));
                buffer.append("\n");
            }

            buffer.append("\n");
        }

        showMessage("CONTACTS", buffer.toString());

    }

    public String searchContact() {
        String search = searchName.getText().toString();
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            Log.d("My Contact", "No Data found in database");

            int duration = Toast.LENGTH_SHORT;
            Toast noData = Toast.makeText(this, "No data found in database", duration);
            noData.show();
        }

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            if (res.getString(1).toUpperCase().equals(search.toUpperCase())) {
                return ("Name: " + res.getString(1)
                        + "    Email: " + res.getString(2)
                        + "    Phone Number: " + res.getString(3));
            }
        }
        return "not found";
    }

        public void searchContact (View v){
        showMessage("Result", searchContact());
        Log.d("Result", searchContact());

     //   showMessage("Contacts with the same name", buffer);


    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}