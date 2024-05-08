package com.example.loginsqllite;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BranchDetailsActivity extends AppCompatActivity {

    EditText branchIdEditText, addressEditText, branchNameEditText;
    Button saveButton, viewOptionButton, createButton, readButton, updateButton, deleteButton;
    LinearLayout scrollViewLayout;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_details);

        // Initialize database
        db = openOrCreateDatabase("LibraryDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Branch_Details(" +
                "BRANCH_ID VARCHAR(5) PRIMARY KEY, " +
                "ADDRESS VARCHAR(50), " +
                "BRANCH_NAME VARCHAR(50));");

        // Initialize EditText fields, Save button, and View Option button
        branchIdEditText = findViewById(R.id.branch_id_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        branchNameEditText = findViewById(R.id.branch_name_edit_text);
        saveButton = findViewById(R.id.save_button);
        viewOptionButton = findViewById(R.id.button_view_option);
        createButton = findViewById(R.id.create_button);
        readButton = findViewById(R.id.read_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);
        scrollViewLayout = findViewById(R.id.scroll_view_layout);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBranchDetails();
            }
        });

        viewOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display branch details
                displayBranchDetails();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new branch details
                saveBranchDetails();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read branch details from database
                displayBranchDetails();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update branch details
                // Implement update functionality as needed
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete branch details
                // Implement delete functionality as needed
            }
        });
    }

    private void saveBranchDetails() {
        String branchId = branchIdEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String branchName = branchNameEditText.getText().toString();

        // Check if any field is empty
        if (branchId.isEmpty() || address.isEmpty() || branchName.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save details to SQLite database
        ContentValues values = new ContentValues();
        values.put("BRANCH_ID", branchId);
        values.put("ADDRESS", address);
        values.put("BRANCH_NAME", branchName);

        long result = db.insert("Branch_Details", null, values);
        if (result != -1) {
            Toast.makeText(this, "Branch details saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save branch details", Toast.LENGTH_SHORT).show();
        }

        // Clear EditText fields after saving
        branchIdEditText.setText("");
        addressEditText.setText("");
        branchNameEditText.setText("");
    }

    private void displayBranchDetails() {
        // Retrieve and display branch details from the database
        scrollViewLayout.removeAllViews();

        Cursor cursor = db.rawQuery("SELECT * FROM Branch_Details", null);
        if (cursor.moveToFirst()) {
            do {
                String branchId = cursor.getString(cursor.getColumnIndex("BRANCH_ID"));
                String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
                String branchName = cursor.getString(cursor.getColumnIndex("BRANCH_NAME"));

                TextView textView = new TextView(this);
                textView.setText("Branch ID: " + branchId + "\nAddress: " + address + "\nBranch Name: " + branchName);
                scrollViewLayout.addView(textView);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
