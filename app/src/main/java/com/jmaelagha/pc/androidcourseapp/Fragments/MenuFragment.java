package com.jmaelagha.pc.androidcourseapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmaelagha.pc.androidcourseapp.Adapters.MenuAdapter;
import com.jmaelagha.pc.androidcourseapp.Listeners.OnMenuClickListener;
import com.jmaelagha.pc.androidcourseapp.Models.Menu;
import com.jmaelagha.pc.androidcourseapp.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    int category_id;
    RecyclerView rv;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    MenuItemSelected listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MenuItemSelected) {
            listener = (MenuItemSelected) context;
        }
    }

    public MenuFragment() {
        // Required empty public constructor
    }

    public MenuFragment(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        rv = v.findViewById(R.id.fragment_menu_rv);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        db.collection("Menu").whereEqualTo("item_category_id", category_id).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null && queryDocumentSnapshots.size() > 0) {
                    ArrayList<Menu> menus = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Menu menu = doc.toObject(Menu.class);
                        menus.add(menu);
                    }
                    fillRecyclerViewWithMenus(menus);
                }
            }
        });
        return v;
    }

    private void fillRecyclerViewWithMenus(final ArrayList<Menu> menus) {
        MenuAdapter adapter = new MenuAdapter(menus, new OnMenuClickListener() {
            @Override
            public void onMenuClick(Menu menu, int number) {
                if (number > 0)
                    listener.onMenuItemSelected(menu, number);
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
    }

    public interface MenuItemSelected {
        void onMenuItemSelected(Menu item, int number);
    }
}
