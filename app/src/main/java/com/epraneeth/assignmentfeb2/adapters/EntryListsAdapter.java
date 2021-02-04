package com.epraneeth.assignmentfeb2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.db.Entry;
import com.epraneeth.assignmentfeb2.viewholders.EntryListsViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntryListsAdapter extends RecyclerView.Adapter<EntryListsViewHolder> {
    Context mContext;
    private List<Entry> entryList= new ArrayList<>();

    public EntryListsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setEntryList(List<Entry> entryList){
        this.entryList = entryList;
        notifyDataSetChanged();
    }

    public void removeEntry(int position){
        this.entryList.remove(position);
        notifyDataSetChanged();
    }

    public  void editEntry(int position){

    }

    @NonNull
    @Override
    public EntryListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.entry_row, parent, false);
        return new EntryListsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryListsViewHolder holder, int position) {
        holder.textView.setText(entryList.get(position).name);
        Entry entry  = entryList.get(position);
        holder.setContext(mContext);
        holder.setEntry(entry);
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

}
