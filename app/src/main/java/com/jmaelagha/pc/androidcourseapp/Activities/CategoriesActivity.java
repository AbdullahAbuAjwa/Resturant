package com.jmaelagha.pc.androidcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmaelagha.pc.androidcourseapp.Adapters.TabAdapter;
import com.jmaelagha.pc.androidcourseapp.Fragments.MenuFragment;
import com.jmaelagha.pc.androidcourseapp.Fragments.OrderDialogFragment;
import com.jmaelagha.pc.androidcourseapp.Models.Category;
import com.jmaelagha.pc.androidcourseapp.Models.Order;
import com.jmaelagha.pc.androidcourseapp.Models.OrderItem;
import com.jmaelagha.pc.androidcourseapp.Models.Tab;
import com.jmaelagha.pc.androidcourseapp.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class CategoriesActivity extends AppCompatActivity implements MenuFragment.MenuItemSelected {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    int tableNumber;
    FirebaseFirestore db;
    ArrayList<OrderItem> orderItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        if(intent !=null){
            tableNumber = intent.getIntExtra(TablesActivity.TABLE_NUMBER_KEY,-1);
        }

        toolbar = findViewById(R.id.categories_toolbar);
        tabLayout = findViewById(R.id.categories_tabLayout);
        viewPager = findViewById(R.id.categories_viewPager);

        setupToolbar();
        setupViewPager();

    }

    private void setupViewPager() {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        addTabs(adapter);
    }

    private void addTabs(final TabAdapter adapter) {
        db.collection("Categories").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots != null && queryDocumentSnapshots.size()>0){
                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                        Category category = doc.toObject(Category.class);
                        adapter.addTab(new Tab(category.getCategory_title(),new MenuFragment(category.getCategory_id())));
                    }
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }
        });
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.categories_menu_notification:
                OrderDialogFragment dialogFragment = new OrderDialogFragment(orderItems);
                dialogFragment.show(getSupportFragmentManager(),"");
                return true;
            case R.id.categories_menu_exit:
                finishAffinity();
                return true;
        }
        return false;
    }

    @Override
    public void onMenuItemSelected(com.jmaelagha.pc.androidcourseapp.Models.Menu item, int number) {
        orderItems.add(new OrderItem(item,number));
    }
}
