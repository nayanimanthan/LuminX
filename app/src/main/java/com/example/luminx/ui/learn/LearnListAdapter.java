package com.example.luminx.ui.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luminx.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class LearnListAdapter extends RecyclerView.Adapter<LearnListAdapter.ViewHolder> {

    private ArrayList<LearnModel> learnlist;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public LearnListAdapter(Context context, ArrayList<LearnModel> learnlist) {
        this.mInflater = LayoutInflater.from(context);
        this.learnlist = learnlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_learn_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(learnlist.get(position).getTitle());
        holder.tv_discription.setText(learnlist.get(position).getDesciption());


    }

    @Override
    public int getItemCount() {
        return learnlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_title, tv_discription;

        ViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_discription = itemView.findViewById(R.id.tv_discription);
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