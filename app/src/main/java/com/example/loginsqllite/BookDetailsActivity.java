package com.example.loginsqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    EditText bookTitleEditText, bookAuthorEditText, bookIsbnEditText;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // Initialize database
        db = openOrCreateDatabase("BooksDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS books(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, author VARCHAR, isbn VARCHAR);");

        // Initialize EditText fields
        bookTitleEditText = findViewById(R.id.book_title_edit_text);
        bookAuthorEditText = findViewById(R.id.book_author_edit_text);
        bookIsbnEditText = findViewById(R.id.book_isbn_edit_text);

        Button createButton = findViewById(R.id.create_button);
        Button readButton = findViewById(R.id.read_button);
        Button updateButton = findViewById(R.id.update_button);
        Button deleteButton = findViewById(R.id.delete_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBook();
                readBooks(); // After inserting, display all books
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readBooks();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBook();
                readBooks(); // After updating, display all books
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook();
                readBooks(); // After deleting, display all books
            }
        });
    }

    // Method to insert a book into the database
    private void insertBook() {
        String title = bookTitleEditText.getText().toString();
        String author = bookAuthorEditText.getText().toString();
        String isbn = bookIsbnEditText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("isbn", isbn);

        long result = db.insert("books", null, values);
        if (result != -1) {
            Toast.makeText(this, "Book inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to insert book", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to read all books from the database
    private void readBooks() {
        // Re-fetch the cursor to ensure it's up-to-date
        Cursor cursor = db.query("books", null, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No books found", Toast.LENGTH_SHORT).show();
            return;
        }

        LinearLayout linearLayout = findViewById(R.id.book_list_layout);
        linearLayout.removeAllViews(); // Clear previous views

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String isbn = cursor.getString(cursor.getColumnIndex("isbn"));

            TextView textView = new TextView(this);
            textView.setText("Title: " + title + ", Author: " + author + ", ISBN: " + isbn);
            linearLayout.addView(textView);
        }
    }

    // Method to update a book in the database
    private void updateBook() {
        String title = bookTitleEditText.getText().toString();
        String author = bookAuthorEditText.getText().toString();
        String isbn = bookIsbnEditText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("author", author);
        values.put("isbn", isbn);

        int rowsAffected = db.update("books", values, "title = ?", new String[]{title});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Book updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update book", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to delete a book from the database
    private void deleteBook() {
        String title = bookTitleEditText.getText().toString();

        int rowsAffected = db.delete("books", "title = ?", new String[]{title});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete book", Toast.LENGTH_SHORT).show();
        }
    }
}
