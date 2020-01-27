package com.jmaelagha.pc.androidcourseapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmaelagha.pc.androidcourseapp.Listeners.OnTableClickListener;
import com.jmaelagha.pc.androidcourseapp.Models.Tab;
import com.jmaelagha.pc.androidcourseapp.Models.Table;
import com.jmaelagha.pc.androidcourseapp.R;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TablesViewHolder> {
    ArrayList<Table> tables ;
    OnTableClickListener listener;

    public TableAdapter(ArrayList<Table> tables, OnTableClickListener listener) {
        this.tables = tables;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_table_item_layout,null);
        TablesViewHolder holder = new TablesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TablesViewHolder holder, int position) {
        Table table = tables.get(position);
        holder.bind(table);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    class TablesViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_status;
        TextView tv_tableNumber;
        Table table;
        public TablesViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_status = itemView.findViewById(R.id.custom_table_item_iv_status);
            tv_tableNumber = itemView.findViewById(R.id.custom_table_item_tv_tableNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTableClick(table);
                }
            });
        }

        public void bind(Table table){
            this.table = table;
            tv_tableNumber.setText(String.valueOf(table.getTable_id()));
            if(table.isTable_status()){
                iv_status.setImageResource(R.drawable.custom_table_item_status_available);
            }
            else{
                iv_status.setImageResource(R.drawable.custom_table_item_status_unavailable);
            }
        }
    }

}

