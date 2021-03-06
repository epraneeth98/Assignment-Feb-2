package com.epraneeth.assignmentfeb2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    EditText editTextName, editTextDOB, editTextMobile;
    Button buttonUpdate, buttonAddAsNew;
    List<Entry> entryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    private void init() {
        editTextName = findViewById(R.id.tv_name);
        editTextDOB = findViewById(R.id.tv_dob);
        editTextDOB.setFocusable(false);
        editTextMobile = findViewById(R.id.tv_mobile);
        buttonUpdate = findViewById(R.id.button_edit);
        buttonAddAsNew = findViewById(R.id.button_add_as_new);
        Intent intent = getIntent();
        Entry entry = (Entry)intent.getSerializableExtra("entry");

        editTextName.setText(entry.getName());
        editTextDOB.setText(entry.getDob());
        editTextMobile.setText(entry.getMobile());

        AppDatabase db = AppDatabase.getDbInstance(this);
        entryList = db.entryDao().getAllEntries();

        editTextDOB.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        buttonUpdate.setOnClickListener(v -> {
            boolean entryExists = false;
            for(Entry entry2: entryList){
                if(entry2.getMobile().equals(String.valueOf(editTextMobile.getText()))){
                    entryExists = true;
                    Toast.makeText(getBaseContext(),"Entry with mobile no. already exists", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if(entryExists==false) {
                Entry entry1 = new Entry(entry.uid, String.valueOf(editTextName.getText()), String.valueOf(editTextDOB.getText()), String.valueOf(editTextMobile.getText()));
                db.entryDao().update(entry1);
                Toast.makeText(getBaseContext(), "Entry Updated", Toast.LENGTH_SHORT).show();
                EditActivity.this.startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });

        buttonAddAsNew.setOnClickListener(v -> {
            boolean entryExists = false;
            for(Entry entry2: entryList){
                if(entry2.getMobile().equals(String.valueOf(editTextMobile.getText()))){
                    entryExists = true;
                    Toast.makeText(getBaseContext(),"Entry with mobile no. already exists", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if(entryExists==false) {
                Entry e = new Entry(String.valueOf(editTextName.getText()), String.valueOf(editTextDOB.getText()), String.valueOf(editTextMobile.getText()));
                db.entryDao().insertEntry(e);
                Toast.makeText(getBaseContext(), "Added as a new Entry", Toast.LENGTH_SHORT).show();
                EditActivity.this.startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String month_text = new DateFormatSymbols().getMonths()[month];
        String date = dayOfMonth+"/"+ month_text +"/"+year;
        Log.d("abc","date: "+date);
        editTextDOB.setText(date);
    }
}