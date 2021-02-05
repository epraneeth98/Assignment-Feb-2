package com.epraneeth.assignmentfeb2.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
//import android.widget.SearchView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.activities.MainActivity;
import com.epraneeth.assignmentfeb2.adapters.EntryListsAdapter;
import com.epraneeth.assignmentfeb2.db.AppDatabase;
import com.epraneeth.assignmentfeb2.db.Entry;
import com.epraneeth.assignmentfeb2.viewholders.EntryListsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class EntryListsFragment extends Fragment{

    public boolean isContexualModeEnabled = false;
    RecyclerView recyclerView;
    EntryListsAdapter entryListAdapter;
    AppDatabase db;
    List<Entry> entryList;
    Toolbar toolbar;

    TextView itemCounter;
    List<Entry> selectedList;
    int counter=0;

    public void makeSelection(View itemView, int position) {
        if(((CheckBox)itemView).isChecked()){
            selectedList.add(entryList.get(position));
            counter++;
        }else{
            selectedList.remove(entryList.get(position));
            counter--;
        }
        updateCounter();
    }

    public void updateCounter(){
        Log.d("abc","updateCounter---->"+ (String) itemCounter.getText());
        itemCounter.setText(String.valueOf(counter)+" -->Item seleccted");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDbInstance(this.getContext());
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        selectedList = new ArrayList<>();
//        function_multiple_delete(view);

//        itemCounter = toolbar.findViewById(R.id.item_count);
//        Log.d("abc","---->"+ (String) itemCounter.getText());

        db = AppDatabase.getDbInstance(this.getContext());
        entryList = db.entryDao().getAllEntries();

        Context currentContext = this.getContext();
        entryListAdapter = new EntryListsAdapter(currentContext, isContexualModeEnabled, this);
        entryListAdapter.setEntryList(entryList);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(currentContext, DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(entryListAdapter);

        MainActivity.hideKeyboard((Activity) currentContext);
    }

    private void function_multiple_delete(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.my_tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (direction == ItemTouchHelper.RIGHT) {
                entryListAdapter.removeEntry(viewHolder.getAdapterPosition());
                entryListAdapter.notifyDataSetChanged();
            }
        }

    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }


    void filter(String text) {
        List<Entry> temp = new ArrayList<>();
        List<Entry> entryList = db.entryDao().getAllEntries();
        for (Entry d : entryList) {
            if (d.getName().toLowerCase().contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        //update recyclerview
        entryListAdapter.setEntryList(temp);
    }

    public boolean onLongClick(View view) {
        isContexualModeEnabled = true;
        entryListAdapter.setContexualMode(true);
        //toolbar.getMenu().clear();
        //toolbar.inflateMenu(R.menu.contexual_menu);
        //toolbar.setBackgroundColor(getResources().getColor(R.color.dark_green));
        return true;
    }
}