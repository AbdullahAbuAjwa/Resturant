package com.jmaelagha.pc.androidcourseapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmaelagha.pc.androidcourseapp.Listeners.OnMenuClickListener;
import com.jmaelagha.pc.androidcourseapp.Models.Menu;
import com.jmaelagha.pc.androidcourseapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenusViewHolder> {
    ArrayList<Menu> menus;
    OnMenuClickListener listener;

    public MenuAdapter(ArrayList<Menu> menus, OnMenuClickListener listener) {
        this.menus = menus;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_item_layout, parent, false);
        MenusViewHolder holder = new MenusViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenusViewHolder holder, int position) {
        Menu menu = menus.get(position);
        holder.bind(menu);
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    class MenusViewHolder extends RecyclerView.ViewHolder {
        ImageView iv, iv_plus, iv_minus;
        TextView tv_title, tv_desc, tv_price, tv_count;
        Menu menu;

        public MenusViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.custom_menu_item_iv);
            tv_title = itemView.findViewById(R.id.custom_menu_item_tv_title);
            tv_desc = itemView.findViewById(R.id.custom_menu_item_tv_desc);
            tv_price = itemView.findViewById(R.id.custom_menu_item_tv_price);
            tv_count = itemView.findViewById(R.id.custom_menu_item_tv_number);
            iv_plus = itemView.findViewById(R.id.custom_menu_item_iv_plus);
            iv_minus = itemView.findViewById(R.id.custom_menu_item_iv_minus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentNumber = Integer.parseInt(tv_count.getText().toString());
                    listener.onMenuClick(menu, currentNumber);
                }
            });

            iv_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentNumber = Integer.parseInt(tv_count.getText().toString());
                    tv_count.setText(String.valueOf(currentNumber + 1));
                }
            });
            iv_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentNumber = Integer.parseInt(tv_count.getText().toString());
                    if (currentNumber != 0)
                        tv_count.setText(String.valueOf(currentNumber - 1));
                }
            });
        }

        public void bind(Menu menu) {
            this.menu = menu;
            tv_title.setText(menu.getItem_title());
            tv_desc.setText(menu.getItem_description());
            tv_price.setText(String.valueOf(menu.getItem_price()));

            if (menu.getItem_image() != null) {
                Picasso.get().load(menu.getItem_image()).into(iv);
            }
        }
    }

}

