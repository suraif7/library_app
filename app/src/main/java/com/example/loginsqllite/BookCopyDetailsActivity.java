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

public class BookCopyDetailsActivity extends AppCompatActivity {

    EditText bookIdEditText, branchIdEditText, accessNoEditText;
    Button saveButton, viewOptionButton;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_copy_details);

        // Initialize database
        db = openOrCreateDatabase("LibraryDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Book_Copy(" +
                "BOOK_ID VARCHAR(13), " +
                "BRANCH_ID VARCHAR(5), " +
                "ACCESS_NO VARCHAR(5), " +
                "PRIMARY KEY(ACCESS_NO, BRANCH_ID), " +
                "FOREIGN KEY(BOOK_ID) REFERENCES Book, " +
                "FOREIGN KEY(BRANCH_ID) REFERENCES Branch);");

        // Initialize EditText fields, Save button, and View Option button
        bookIdEditText = findViewById(R.id.book_id_edit_text);
        branchIdEditText = findViewById(R.id.branch_id_edit_text);
        accessNoEditText = findViewById(R.id.access_no_edit_text);
        saveButton = findViewById(R.id.save_button);
        viewOptionButton = findViewById(R.id.button_view_option);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookCopyDetails();
            }
        });

        viewOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display book copy details
                displayBookCopyDetails();
            }
        });
    }

    private void saveBookCopyDetails() {
        String bookId = bookIdEditText.getText().toString();
        String branchId = branchIdEditText.getText().toString();
        String accessNo = accessNoEditText.getText().toString();

        // Check if any field is empty
        if (bookId.isEmpty() || branchId.isEmpty() || accessNo.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save details to SQLite database
        ContentValues values = new ContentValues();
        values.put("BOOK_ID", bookId);
        values.put("BRANCH_ID", branchId);
        values.put("ACCESS_NO", accessNo);

        long result = db.insert("Book_Copy", null, values);
        if (result != -1) {
            Toast.makeText(this, "Book copy details saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save book copy details", Toast.LENGTH_SHORT).show();
        }

        // Clear EditText fields after saving
        bookIdEditText.setText("");
        branchIdEditText.setText("");
        accessNoEditText.setText("");
    }

    private void displayBookCopyDetails() {
        // Clear existing views inside the ScrollView
        LinearLayout scrollViewLayout = findViewById(R.id.scroll_view_layout);
        scrollViewLayout.removeAllViews();

        // Retrieve book copy details from the database
        Cursor cursor = db.rawQuery("SELECT * FROM Book_Copy", null);

        // Iterate through the cursor to retrieve data
        while (cursor.moveToNext()) {
            // Extract data from the cursor
            String bookId = cursor.getString(cursor.getColumnIndex("BOOK_ID"));
            String branchId = cursor.getString(cursor.getColumnIndex("BRANCH_ID"));
            String accessNo = cursor.getString(cursor.getColumnIndex("ACCESS_NO"));

            // Create a TextView to display the book copy details
            TextView textView = new TextView(this);
            textView.setText("Book ID: " + bookId + "\nBranch ID: " + branchId + "\nAccess No: " + accessNo);
            textView.setTextSize(16);
            textView.setPadding(0, 0, 0, 16); // Add padding between TextViews
            scrollViewLayout.addView(textView);
        }

        // Close the cursor
        cursor.close();
    }
}
