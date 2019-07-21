package com.example.thenextepisode;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {

    private List<Show> showList;

    ShowAdapter(List<Show> showList) {
        this.showList = showList;
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    @Override
    public void onBindViewHolder(ShowViewHolder showViewHolder, int i) {
        Show show = showList.get(i);
        showViewHolder.vName.setText(show.getShowName());
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.cardview, viewGroup, false);
        return new ShowViewHolder(itemView);
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        TextView vName;

        ShowViewHolder(View v) {
            super(v);
            vName = v.findViewById(R.id.show_title);
        }

    }
}

