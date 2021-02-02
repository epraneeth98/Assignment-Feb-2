package com.epraneeth.assignmentfeb2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;

public class EditActivity extends AppCompatActivity {

    EditText editTextName, editTextDOB, editTextMobile;
    Button buttonUpdate, buttonAddAsNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    private void init() {
        editTextName = findViewById(R.id.tv_name);
        editTextDOB = findViewById(R.id.tv_dob);
        editTextMobile = findViewById(R.id.tv_mobile);
        buttonUpdate = findViewById(R.id.button_update);
        buttonAddAsNew = findViewById(R.id.button_add_as_new);
        Intent intent = getIntent();
        Entry entry = (Entry)intent.getSerializableExtra("entry");

        editTextName.setText(entry.getName());
        editTextDOB.setText(entry.getDob());
        editTextMobile.setText(entry.getMobile());

        AppDatabase db = AppDatabase.getDbInstance(this);


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonAddAsNew.setOnClickListener(v -> {
            Entry e = new Entry(String.valueOf(editTextName.getText()), String.valueOf(editTextDOB.getText()), String.valueOf(editTextMobile.getText()));
            db.entryDao().insertEntry(e);
            EditActivity.this.startActivity(new Intent(EditActivity.this, MainActivity.class));
        });
    }
}