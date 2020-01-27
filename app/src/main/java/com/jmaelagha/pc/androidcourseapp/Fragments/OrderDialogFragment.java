package com.jmaelagha.pc.androidcourseapp.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jmaelagha.pc.androidcourseapp.Adapters.OrderItemsAdapter;
import com.jmaelagha.pc.androidcourseapp.Listeners.OnOrderItemClickListener;
import com.jmaelagha.pc.androidcourseapp.Models.Menu;
import com.jmaelagha.pc.androidcourseapp.Models.Order;
import com.jmaelagha.pc.androidcourseapp.Models.OrderItem;
import com.jmaelagha.pc.androidcourseapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDialogFragment extends DialogFragment {

    ArrayList<OrderItem> items;

    public OrderDialogFragment() {
        // Required empty public constructor
    }

    public OrderDialogFragment(ArrayList<OrderItem> items) {
        this.items = items;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order_dialog, null);
        RecyclerView rv = v.findViewById(R.id.fragment_order_rv);
        final OrderItemsAdapter adapter = new OrderItemsAdapter(items, new OnOrderItemClickListener() {
            @Override
            public void onOrderItemDelete(Menu menu) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getOrder_item().getItem_id() == menu.getItem_id()) {
                        items.remove(i);
                        Toast.makeText(getActivity(), "Item remover successfully", Toast.LENGTH_SHORT).show();

                        break;
                    }
                }
            }
        });

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Order list")
                .setView(v)
                .setPositiveButton("Make order", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        Order order = new Order(0,auth.getCurrentUser().getUid(),items);
                        db.collection("Orders").add(order).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                            }
                        });
                    }
                })
                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Order removed", Toast.LENGTH_SHORT).show();
                        items.clear();
                        adapter.notifyDataSetChanged();
                    }
                }).create();
    }

}