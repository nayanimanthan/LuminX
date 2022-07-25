package com.example.luminx.ui.report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luminx.R;
import com.example.luminx.model.PollutionReport;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    private ArrayList<PollutionReport> movielist;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public ReportListAdapter(Context context, ArrayList<PollutionReport> movielist) {
        this.mInflater = LayoutInflater.from(context);
        this.movielist = movielist;
    }

    public void setMovielist(ArrayList<PollutionReport> movielist){
        this.movielist = movielist;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_report_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_brightness.setText(movielist.get(position).getBrightness());
    //    holder.tv_date.setText(movielist.get(position).getDate());
        holder.tv_email.setText(movielist.get(position).getEmail());
        holder.tv_light_source.setText(movielist.get(position).getLight_source());
        holder.tv_light_color.setText(movielist.get(position).getLight_color());
    //    holder.tv_location.setText(movielist.get(position).getLocation());
        holder.tv_style.setText(movielist.get(position).getStyle());
        holder.tv_name.setText(movielist.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tv_brightness, tv_date, tv_email, tv_light_source, tv_light_color, tv_location,tv_style,tv_name;

        ViewHolder(View itemView) {
            super(itemView);

            tv_brightness = itemView.findViewById(R.id.tv_brightness);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_light_color = itemView.findViewById(R.id.tv_light_color);
            tv_light_source = itemView.findViewById(R.id.tv_light_source);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_style = itemView.findViewById(R.id.tv_style);
        }


    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}