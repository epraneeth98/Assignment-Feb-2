package com.epraneeth.assignmentfeb2.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.activities.MainActivity;
import com.epraneeth.assignmentfeb2.adapters.EntryListsAdapter;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;

import java.util.List;

public class EntryListsFragment extends Fragment {

    RecyclerView recyclerView;
    EntryListsAdapter entryListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        Context currentContext = this.getContext();
        entryListAdapter = new EntryListsAdapter(currentContext);

        AppDatabase db = AppDatabase.getDbInstance(currentContext);
        List<Entry> entryList = db.entryDao().getAllEntries();
        entryListAdapter.setEntryList(entryList);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(currentContext, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(entryListAdapter);

        MainActivity.hideKeyboard((Activity) currentContext);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}