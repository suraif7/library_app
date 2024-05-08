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

public class authorDetailsActivity extends AppCompatActivity {

    EditText editTextBookId, editTextAuthorName;
    TextView textViewTypedTexts;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_details); // Corrected layout

        // Initialize database
        db = openOrCreateDatabase("LibraryDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Book(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "BookID VARCHAR(13), " +
                "AuthorName VARCHAR(50));");

        // Initialize EditText fields and TextView
        editTextBookId = findViewById(R.id.editTextBookId);
        editTextAuthorName = findViewById(R.id.editTextAuthorName);
        textViewTypedTexts = findViewById(R.id.textViewTypedTexts);

        Button buttonCreate = findViewById(R.id.buttonCreate);
        Button buttonRead = findViewById(R.id.buttonRead);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        // Create operation
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = editTextBookId.getText().toString();
                String authorName = editTextAuthorName.getText().toString();
                boolean isInserted = insertData(bookId, authorName);
                if (isInserted)
                    showMessage("Success", "Data Inserted");
                else
                    showMessage("Error", "Data not Inserted");
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
                // Update operation
            }
        });

        // Delete operation
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete operation
            }
        });
    }

    private boolean insertData(String bookId, String authorName) {
        ContentValues values = new ContentValues();
        values.put("BookID", bookId);
        values.put("AuthorName", authorName);
        long result = db.insert("Book", null, values);
        return result != -1;
    }

    private void displayTypedTexts() {
        Cursor res = db.rawQuery("SELECT * FROM Book", null);
        if (res.getCount() == 0) {
            showMessage("Error", "No data found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Book ID: ").append(res.getString(1)).append("\n");
            buffer.append("Author Name: ").append(res.getString(2)).append("\n\n");
        }
        textViewTypedTexts.setText(buffer.toString());
    }

    private void showMessage(String title, String message) {
        Toast.makeText(this, title + ": " + message, Toast.LENGTH_LONG).show();
    }
}
