package com.example.luigi.spesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
    Articolo articolo;
    AdapterActivity adapter;
    MyRecyclerAdapter myRecyclerAdapter;
    List<Articolo> itemList= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail_layout);

        Intent fromAdapter = getIntent();
        String name = fromAdapter.getStringExtra("nome");

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(name);

        adapter=new AdapterActivity(getApplicationContext(),itemList );
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
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

                                int id_list = SharedPreferenceUtility.readIdFromSharedPreference(getApplicationContext());
                                ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(getApplicationContext());
                                itemDatabaseManager.open();
                                Long cursor = itemDatabaseManager.createItem(String.valueOf(name.getText()), id_list, Integer.parseInt(String.valueOf(value.getText())));
                                articolo = new Articolo(String.valueOf(name.getText()), 0, id_list, Integer.parseInt(String.valueOf(value.getText())));
                                Log.d("cursor",cursor.toString());
                                adapter.updateItem(getApplicationContext());

                                adapter.setValues();

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

