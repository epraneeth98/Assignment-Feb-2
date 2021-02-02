package com.epraneeth.assignmentfeb2.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;
import com.google.android.material.tabs.TabLayout;

public class CreateEntryFragment extends Fragment {

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
    TabLayout tabLayout;

    public CreateEntryFragment() {
        // Required empty public constructor
    }

    public static CreateEntryFragment newInstance(String param1, String param2) {
        CreateEntryFragment fragment = new CreateEntryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(new ViewEntryListFragment())
//                        .commit();
                viewPager.setCurrentItem(0);
            }
        });

    }

}