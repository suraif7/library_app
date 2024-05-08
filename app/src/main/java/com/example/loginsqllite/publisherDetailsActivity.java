package com.example.loginsqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class publisherDetailsActivity extends AppCompatActivity {

    EditText editTextName, editTextAddress, editTextPhoneNumber;
    TextView textViewTypedTexts;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database
        db = openOrCreateDatabase("MyDatabase", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Address TEXT, " +
                "PhoneNumber TEXT);");

        // Initialize EditText fields and TextView
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        textViewTypedTexts = findViewById(R.id.textViewTypedTexts);

        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonRead = findViewById(R.id.buttonRead);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        // Save operation
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        // Read operation
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTypedTexts();
            }
        });

        // Update operation
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement update operation
            }
        });

        // Delete operation
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement delete operation
            }
        });
    }

    private void saveData() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        // Check if any field is empty
        if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save details to SQLite database
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Address", address);
        values.put("PhoneNumber", phoneNumber);

        long result = db.insert("MyTable", null, values);
        if (result != -1) {
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            displayTypedTexts();
        } else {
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
        }

        // Clear EditText fields after saving
        editTextName.setText("");
        editTextAddress.setText("");
        editTextPhoneNumber.setText("");
    }

    private void displayTypedTexts() {
        Cursor res = db.rawQuery("SELECT * FROM MyTable", null);
        if (res.getCount() == 0) {
            textViewTypedTexts.setText("No data found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Name: ").append(res.getString(1)).append("\n");
            buffer.append("Address: ").append(res.getString(2)).append("\n");
            buffer.append("Phone Number: ").append(res.getString(3)).append("\n\n");
        }
        textViewTypedTexts.setText(buffer.toString());
    }
}
