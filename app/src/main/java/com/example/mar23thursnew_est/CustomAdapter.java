package com.example.mar23thursnew_est;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] localDataSet;
    private String[] yearDataSet;
    View.OnClickListener listener;

    public CustomAdapter(String[] dataSet, String[] yearSet) {localDataSet = dataSet; yearDataSet = yearSet;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView yearView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.textView);
            yearView = (TextView) view.findViewById(R.id.yearView);
            textView.setTag(this);

            textView.setOnClickListener(listener);
        }

        public TextView getTextView() {
            return textView;
        }
        public TextView getYearView() {return yearView; }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thing, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet[position]);
        viewHolder.getYearView().setText(yearDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public void setOnClickListener(View.OnClickListener listenThingie){
        listener = listenThingie;

    }
}
