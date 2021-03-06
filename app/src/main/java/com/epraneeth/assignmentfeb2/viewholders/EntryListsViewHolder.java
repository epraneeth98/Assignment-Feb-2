package com.epraneeth.assignmentfeb2.viewholders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epraneeth.assignmentfeb2.R;
import com.epraneeth.assignmentfeb2.activities.MainActivity;
import com.epraneeth.assignmentfeb2.activities.ShowDetailsActivity;
import com.epraneeth.assignmentfeb2.db.Entry;
import com.epraneeth.assignmentfeb2.fragments.EntryListsFragment;

public class EntryListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textView;
    public ImageView imageView;
    public Entry entry;
    public CheckBox checkBox;
    public Context mContext;

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public EntryListsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_view);
        textView = itemView.findViewById(R.id.text_view);
        checkBox = itemView.findViewById(R.id.checkbox);


        checkBox.setOnClickListener(this);
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(mContext, ShowDetailsActivity.class);
            i.putExtra("uid", String.valueOf(entry.getUid()));
            mContext.startActivity(i);
        });
    }

    @Override
    public void onClick(View v) {
    }
}
