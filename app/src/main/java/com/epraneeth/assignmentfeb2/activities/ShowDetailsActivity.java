package com.epraneeth.assignmentfeb2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;

public class ShowDetailsActivity extends AppCompatActivity {
    TextView textViewName, textViewDOB, textViewMobile;
    Button buttonUpdate, buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        init();
    }

    private void init() {
        textViewName = findViewById(R.id.show_name);
        textViewDOB = findViewById(R.id.show_dob);
        textViewMobile = findViewById(R.id.show_mobile);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate = findViewById(R.id.button_edit);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");

        AppDatabase db = AppDatabase.getDbInstance(this);
        Log.d("abc", "uid: "+uid);
        Entry entry = db.entryDao().getEntry(Long.parseLong(uid));
        textViewName.setText(entry.getName());
        textViewDOB.setText(entry.getDob());
        textViewMobile.setText(entry.getMobile());


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.entryDao().delete(entry);
                ShowDetailsActivity.this.startActivity(new Intent(ShowDetailsActivity.this, MainActivity.class));
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDetailsActivity.this, EditActivity.class);
                i.putExtra("entry", entry);
                ShowDetailsActivity.this.startActivity(i);
            }
        });
    }
}