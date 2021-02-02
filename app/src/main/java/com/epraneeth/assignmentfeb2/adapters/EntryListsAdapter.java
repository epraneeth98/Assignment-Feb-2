package com.epraneeth.assignmentfeb2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.db.Entry;
import com.epraneeth.assignmentfeb2.viewholders.EntryViewHolder;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class EntryListsAdapter extends RecyclerView.Adapter<EntryViewHolder> {
    Context mContext;
    private List<Entry> entryList= new ArrayList<>();

    public EntryListsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setEntryList(List<Entry> entryList){
        this.entryList = entryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.entry_row, parent, false);
        return new EntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
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
