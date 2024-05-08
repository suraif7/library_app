package com.example.loginsqllite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class memberDetailsActivity extends AppCompatActivity {

    EditText editTextCardNo, editTextName, editTextAddress, editTextPhoneNumber, editTextUnpaidDueNumber;
    TextView textViewTypedTexts;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_details_activity);

        // Initialize database
        db = openOrCreateDatabase("LibraryDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS UserDetails(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CardNo TEXT, " +
                "Name TEXT, " +
                "Address TEXT, " +
                "PhoneNumber TEXT, " +
                "UnpaidDueNumber INTEGER);");

        // Initialize EditText fields and TextView
        editTextCardNo = findViewById(R.id.editTextCardNo);
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextUnpaidDueNumber = findViewById(R.id.editTextUnpaidDueNumber);
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

        Button buttonMemberDetail = findViewById(R.id.buttonMemberDetail);
        buttonMemberDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to show member details
                // For example, start a new activity to show member details
            }
        });
    }

    private void saveData() {
        // Implementation remains the same as before
    }

    private void displayTypedTexts() {
        // Implementation remains the same as before
    }
}
