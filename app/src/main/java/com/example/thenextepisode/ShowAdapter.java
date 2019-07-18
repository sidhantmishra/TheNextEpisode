package com.example.thenextepisode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {

    private List<Show> showList;

    public ShowAdapter(List<Show> showList) {
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
    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Show show = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        TextView showName = convertView.findViewById(R.id.show_name);
        showName.setText(show.getShowName());
        return convertView;
    }*/

    public class ShowViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;

        public ShowViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.show_title);
        }

    }
}

