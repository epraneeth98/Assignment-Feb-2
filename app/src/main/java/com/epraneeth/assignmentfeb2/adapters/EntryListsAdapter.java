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

public class EntryListsAdapter extends RecyclerView.Adapter<EntryListsViewHolder> {//implements Filterable {
    Context mContext;
    private List<Entry> entryList= new ArrayList<>();
    private List<Entry> allEntriesList = new ArrayList<>();

    public EntryListsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setEntryList(List<Entry> entryList){
        this.entryList = entryList;
        allEntriesList = new ArrayList<>(entryList);
        notifyDataSetChanged();
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

//    @Override
//    public Filter getFilter() {
//        return filter;
//    }
//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
//            List<Entry> filteredList = new ArrayList<>();
//            if(constraint.toString().isEmpty()){
//                filteredList.addAll(allEntriesList);
//            }else{
//                for(Entry entry: allEntriesList){
//                    if(entry.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
//                        filteredList.add(entry);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            entryList.clear();
//            entryList.addAll((Collection<? extends Entry>) results.values);
//            notifyDataSetChanged();
//        }
//    };

}
