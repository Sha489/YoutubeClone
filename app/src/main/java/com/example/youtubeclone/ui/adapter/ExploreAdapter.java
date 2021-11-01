package com.example.youtubeclone.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone.R;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> exploreArrayList;

    public ExploreAdapter(Context context, ArrayList<String> exploreArrayList) {
        this.context = context;
        this.exploreArrayList = exploreArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_explore, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.exploreText.setText(exploreArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return exploreArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView exploreText;
        public ViewHolder(View itemView) {
            super(itemView);
            exploreText = itemView.findViewById(R.id.exploreText);
        }
    }
}
