package com.jmaelagha.pc.androidcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jmaelagha.pc.androidcourseapp.Adapters.TableAdapter;
import com.jmaelagha.pc.androidcourseapp.Listeners.OnTableClickListener;
import com.jmaelagha.pc.androidcourseapp.Models.Tab;
import com.jmaelagha.pc.androidcourseapp.Models.Table;
import com.jmaelagha.pc.androidcourseapp.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class TablesActivity extends AppCompatActivity {
    RecyclerView rv_tables;
    FirebaseFirestore db;

    public static final String TABLE_NUMBER_KEY = "table_number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);



        db = FirebaseFirestore.getInstance();
        rv_tables = findViewById(R.id.tables_rv_tables);
        db.collection("Tables").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<Table> tables = new ArrayList<>();
                if(queryDocumentSnapshots != null && queryDocumentSnapshots.size()>0){
                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                        Table table = doc.toObject(Table.class);
                        tables.add(table);
                    }
                    fillRecyclerViewWithTables(tables);
                }
            }
        });
    }

    private void fillRecyclerViewWithTables(final ArrayList<Table> tables) {
        TableAdapter adapter = new TableAdapter(tables, new OnTableClickListener() {
            @Override
            public void onTableClick(Table table) {
                Intent intent = new Intent(TablesActivity.this,CategoriesActivity.class);
                intent.putExtra(TABLE_NUMBER_KEY,table.getTable_id());
                startActivity(intent);
            }
        });
        rv_tables.setAdapter(adapter);
        rv_tables.setLayoutManager(new GridLayoutManager(this,3));
        rv_tables.setHasFixedSize(true);
    }
}
