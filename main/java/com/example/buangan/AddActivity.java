package com.example.buangan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    EditText inpJudul, inpDesk;
    Context context;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        inpJudul = findViewById(R.id.tmbh_judul);
        inpDesk = findViewById(R.id.tmbh_desk);
        btnSubmit = findViewById(R.id.tambah);
        btnSubmit.setOnClickListener(this);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        if (inpJudul.getText().toString().length() == 0 || inpDesk.getText().toString().length() == 0) {
            inpJudul.setError("Title is required!");
            inpDesk.setError("Description is required!");
        } else {
            if (v.getId() == R.id.tambah) {
                DatabaseHelper db = new DatabaseHelper(this);
                ItemBean currentPost = new ItemBean();
                String btnStatus = btnSubmit.getText().toString();
                if (btnStatus.equals("Tambah")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date date = new Date();
                    currentPost.setJudul(inpJudul.getText().toString());
                    currentPost.setDesc(inpDesk.getText().toString());
                    db.insert(currentPost);
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                btnSubmit.setText("Tambah");
            }
        }
    }
}
