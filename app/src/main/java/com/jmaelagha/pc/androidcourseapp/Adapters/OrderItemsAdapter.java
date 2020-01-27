package com.jmaelagha.pc.androidcourseapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmaelagha.pc.androidcourseapp.Listeners.OnMenuClickListener;
import com.jmaelagha.pc.androidcourseapp.Listeners.OnOrderItemClickListener;
import com.jmaelagha.pc.androidcourseapp.Models.Menu;
import com.jmaelagha.pc.androidcourseapp.Models.OrderItem;
import com.jmaelagha.pc.androidcourseapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemViewHolder> {
    ArrayList<OrderItem> items;
    OnOrderItemClickListener listener;

    public OrderItemsAdapter(ArrayList<OrderItem> items, OnOrderItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_item_layout, parent, false);
        OrderItemViewHolder holder = new OrderItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_delete;
        TextView tv_title, tv_number, tv_price;
        OrderItem item;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_delete = itemView.findViewById(R.id.custom_order_item_iv_delete);
            tv_title = itemView.findViewById(R.id.custom_order_item_tv_title);
            tv_price = itemView.findViewById(R.id.custom_order_item_tv_price);
            tv_number = itemView.findViewById(R.id.custom_order_item_tv_number);

            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onOrderItemDelete(item.getOrder_item());
                    notifyDataSetChanged();
                }
            });
        }

        public void bind(OrderItem item) {
            this.item = item;
            tv_title.setText(item.getOrder_item().getItem_title());
            tv_price.setText(String.valueOf(item.getOrder_item().getItem_price()));
            tv_number.setText(String.valueOf(item.getNumber()));
        }
    }

}

