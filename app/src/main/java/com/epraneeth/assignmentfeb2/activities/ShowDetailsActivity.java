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

public class ShowDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewName, textViewDOB, textViewMobile;
    Button buttonEdit, buttonDelete;
    Entry entry;
    AppDatabase db;
    String uid;

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
        buttonEdit = findViewById(R.id.button_edit);
        db = AppDatabase.getDbInstance(this);

        uid = getIntent().getStringExtra("uid");
        fillDetails(uid);

    }

    private void fillDetails(String uid) {
        Log.d("abc", "uid: "+uid);
        entry = db.entryDao().getEntry(Long.parseLong(uid));
        textViewName.setText(entry.getName());
        textViewDOB.setText(entry.getDob());
        textViewMobile.setText(entry.getMobile());

        buttonDelete.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== buttonEdit.getId()){
            Intent i = new Intent(ShowDetailsActivity.this, EditActivity.class);
            i.putExtra("entry", entry);
            ShowDetailsActivity.this.startActivity(i);

        }else if(v.getId()==buttonDelete.getId()){
            db.entryDao().delete(entry);
            ShowDetailsActivity.this.startActivity(new Intent(ShowDetailsActivity.this, MainActivity.class));
        }
    }
}