package com.epraneeth.assignmentfeb2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.adapters.EntryListAdapter;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;

import java.util.List;

public class ViewEntryListFragment extends Fragment {

    RecyclerView recyclerView;
    EntryListAdapter entryListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        entryListAdapter = new EntryListAdapter(this.getContext());

        AppDatabase db = AppDatabase.getDbInstance(this.getContext());
        List<Entry> entryList = db.entryDao().getAllEntries();
        entryListAdapter.setEntryList(entryList);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(entryListAdapter);
    }

}