package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Set the layout

        // Button references
        Button bookDetailsButton = findViewById(R.id.button1);
        Button bookCopyDetailsButton = findViewById(R.id.button2);
        Button memberDetailsButton = findViewById(R.id.button3);
        Button publisherDetailsButton = findViewById(R.id.button4);
        Button authorDetailsButton = findViewById(R.id.button5);
        Button lendingDetailsButton = findViewById(R.id.button6);
        Button branchDetailsButton = findViewById(R.id.button7);

        // Button click listeners
        bookDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookDetailsActivity.class);
                startActivity(intent);
            }
        });

        bookCopyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookCopyDetailsActivity.class);
                startActivity(intent);
            }
        });

        // ... similar logic for other buttons

        memberDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, memberDetailsActivity.class);
                startActivity(intent);
            }
        });

        publisherDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,publisherDetailsActivity.class); // Corrected class name
                startActivity(intent);
            }
        });

        authorDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, authorDetailsActivity.class); // Corrected class name
                startActivity(intent);
            }
        });

        lendingDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, lendingDetailsActivity.class); // Corrected class name
                startActivity(intent);
            }
        });

        branchDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BranchDetailsActivity.class); // Corrected class name
                startActivity(intent);
            }
        });
    }
}
