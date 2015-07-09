package com.example.myka.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Myka on 7/5/2015.
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.MyViewHolder> {

    List<Information> data = Collections.emptyList();
    private LayoutInflater inflater;
    //private Context context;
    private ClickListener clickListener;

    public InformationAdapter (Context context, List<Information> data) {
        //this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    public void setClickListener (ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }


        @Override
        public void onClick(View v) {
            //This is the first way but not a very good implementation
            //context.startActivity(new Intent(context, SubActivity.class));

            if (clickListener != null) {
                clickListener.itemClick(v, getPosition());
            }
        }
    }

    //Second solution that is better
    public interface ClickListener {
        public void itemClick (View view, int position);

    }
}
