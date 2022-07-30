package com.example.luminx.ui.suggestions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luminx.R;
import com.example.luminx.model.RecomendedPlacesList;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class SuggestionListAdapter extends RecyclerView.Adapter<SuggestionListAdapter.ViewHolder> {

    private ArrayList<RecomendedPlacesList> suggestionlist;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public SuggestionListAdapter(Context context, ArrayList<RecomendedPlacesList> suggestionlist) {
        this.mInflater = LayoutInflater.from(context);
        this.suggestionlist = suggestionlist;
    }

    public void setSuggestionlist(ArrayList<RecomendedPlacesList> suggestionlist){
        this.suggestionlist = suggestionlist;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_suggestion_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(suggestionlist.get(position).getName());
        holder.tv_category.setText(suggestionlist.get(position).getCategory());
        holder.tv_type.setText(suggestionlist.get(position).getType());
        holder.tv_city.setText(suggestionlist.get(position).getCity());
        holder.tv_continent.setText(suggestionlist.get(position).getContinent());


    }

    @Override
    public int getItemCount() {
        return suggestionlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_category, tv_type, tv_city, tv_continent,tv_name;

        ViewHolder(View itemView) {
            super(itemView);

            tv_type = itemView.findViewById(R.id.tv_type);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_continent = itemView.findViewById(R.id.tv_continent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}