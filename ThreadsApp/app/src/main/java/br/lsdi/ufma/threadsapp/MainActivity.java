package br.lsdi.ufma.threadsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button noThreadButton= findViewById(R.id.noThreadButton);
        noThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoThreadActivity.class));
            }
        });


        final Button threadSimplesButton = findViewById(R.id.threadSimplesButton);
        threadSimplesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThreadSimplesActivity.class));
            }
        });

        final Button viewPostButton = findViewById(R.id.viewPostButton);
        viewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewPostActivity.class));
            }
        });

        final Button asyncTaskButton = findViewById(R.id.asyncTaskButton);
        asyncTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
            }
        });

        final Button handlerPostButton = findViewById(R.id.handlerPostButton);
        handlerPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HandlerPostActivity.class));
            }
        });

        final Button handlerMessageButton = findViewById(R.id.handlerMessageButton);
        handlerMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HandlerMessageActivity.class));
            }
        });

        final Button eventBusButton = findViewById(R.id.eventBusButton);
        eventBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
            }
        });

    }
}
