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

public class lendingDetailsActivity extends AppCompatActivity {

    EditText editTextCardNo, editTextDateOut, editTextDateDue, editTextDateReturned;
    TextView textViewTypedTexts;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending_details);

        // Initialize database
        db = openOrCreateDatabase("LibraryDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS BookDetails(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CardNo VARCHAR(10), " +
                "DateOut VARCHAR(20), " +
                "DateDue VARCHAR(20), " +
                "DateReturned VARCHAR(20));");

        // Initialize EditText fields and TextView
        editTextCardNo = findViewById(R.id.editTextCardNo);
        editTextDateOut = findViewById(R.id.editTextDateOut);
        editTextDateDue = findViewById(R.id.editTextDateDue);
        editTextDateReturned = findViewById(R.id.editTextDateReturned);
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
        String cardNo = editTextCardNo.getText().toString().trim();
        String dateOut = editTextDateOut.getText().toString().trim();
        String dateDue = editTextDateDue.getText().toString().trim();
        String dateReturned = editTextDateReturned.getText().toString().trim();

        // Check if any field is empty
        if (cardNo.isEmpty() || dateOut.isEmpty() || dateDue.isEmpty() || dateReturned.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save details to SQLite database
        ContentValues values = new ContentValues();
        values.put("CardNo", cardNo);
        values.put("DateOut", dateOut);
        values.put("DateDue", dateDue);
        values.put("DateReturned", dateReturned);

        long result = db.insert("BookDetails", null, values);
        if (result != -1) {
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            displayTypedTexts();
        } else {
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
        }

        // Clear EditText fields after saving
        editTextCardNo.setText("");
        editTextDateOut.setText("");
        editTextDateDue.setText("");
        editTextDateReturned.setText("");
    }

    private void displayTypedTexts() {
        Cursor res = db.rawQuery("SELECT * FROM BookDetails", null);
        if (res.getCount() == 0) {
            textViewTypedTexts.setText("No data found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Card No: ").append(res.getString(1)).append("\n");
            buffer.append("Date Out: ").append(res.getString(2)).append("\n");
            buffer.append("Date Due: ").append(res.getString(3)).append("\n");
            buffer.append("Date Returned: ").append(res.getString(4)).append("\n\n");
        }
        textViewTypedTexts.setText(buffer.toString());
    }
}
