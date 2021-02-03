package com.epraneeth.assignmentfeb2.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.activities.MainActivity;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CreateEntryFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText textName, textDOB, textMobile;
    Button buttonSave;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_entry, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        textName = view.findViewById(R.id.tv_name);
        textDOB = view.findViewById(R.id.tv_dob);
        textDOB.setFocusable(false);
        textMobile = view.findViewById(R.id.tv_mobile);
        buttonSave = view.findViewById(R.id.save_button);
        AppDatabase db = AppDatabase.getDbInstance(this.getContext());
        viewPager = getActivity().findViewById(R.id.view_pager);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entry entry = new Entry(textName.getText().toString(),
                        textDOB.getText().toString(),
                        textMobile.getText().toString());
                db.entryDao().insertEntry(entry);

                viewPager.setCurrentItem(0);
            }
        });

        textDOB.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String month_text = new DateFormatSymbols().getMonths()[month];
        String date = dayOfMonth+"/"+ month_text +"/"+year;
        Log.d("abc","date: "+date);
        textDOB.setText(date);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        textName.setText("");
        textDOB.setText("");
        textMobile.setText("");
    }
}