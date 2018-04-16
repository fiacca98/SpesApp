package com.example.luigi.spesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by corsista on 16/04/2018.
 */

public class ListDetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail_layout);

        TextView textView = (TextView)findViewById(R.id.listName);

        Intent fromAdapter=getIntent();
        String name=fromAdapter.getStringExtra("nome");

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(name);

    }
}
