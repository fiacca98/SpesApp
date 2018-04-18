package com.example.luigi.spesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corsista on 16/04/2018.
 */

public class ListDetailActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private ItemRecyclerAdapter itemRecyclerAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    public static ListDetailActivity.LayoutManagerType mCurrentLayoutManagerType;
    public Articolo articolo;

    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail_layout);

        Intent fromAdapter = getIntent();
        String nameList = fromAdapter.getStringExtra("nome");

        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerDetail);
        itemRecyclerAdapter = new ItemRecyclerAdapter(getApplicationContext());
        myLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = ListDetailActivity.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(itemRecyclerAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(nameList);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ListDetailActivity.super.onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context thisContext = ListDetailActivity.this;
                ListDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(ListDetailActivity.this);
                        final EditText name = new EditText(ListDetailActivity.this);
                        final EditText value = new EditText(ListDetailActivity.this);
                        name.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setTitle("Aggiungi Articolo");

                        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                        linearLayout.setMinimumWidth(1000);
                        linearLayout.addView(name);
                        linearLayout.addView(value);
                        linearLayout.setOrientation(linearLayout.VERTICAL);
                        name.setHint("Nome");
                        value.setHint("Quantità");
                        name.setWidth(500);
                        value.setWidth(500);
                        builder.setView(linearLayout);
                        builder.setPositiveButton("Aggiungi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ListDatabaseManager listDatabaseManager = new ListDatabaseManager(getApplicationContext());
                                listDatabaseManager.open();
                                Cursor listCursor =  listDatabaseManager.getListsByName(nameList);
                                listCursor.moveToFirst();
                                int id_list=listCursor.getInt(listCursor.getColumnIndex(listDatabaseManager.KEY_ID));

                                ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(getApplicationContext());
                                itemDatabaseManager.open();
                                Long cursor = itemDatabaseManager.createItem(String.valueOf(name.getText()), id_list, Integer.parseInt(String.valueOf(value.getText())));
                                Log.d("cursor",cursor.toString());
                                itemRecyclerAdapter.updateList(getApplicationContext());

                                dialog.cancel();
                            }
                        });
                        builder.setNegativeButton("ANNULLA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                });
            }
        });
    }

}

