package com.example.luigi.spesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private ListRecyclerAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    public static LayoutManagerType mCurrentLayoutManagerType;
    public Lista lista;
    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        myAdapter = new ListRecyclerAdapter(getApplicationContext());
        myLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context thisContext = MainActivity.this;
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        final EditText input = new EditText(MainActivity.this);
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setTitle("AGGIUNGI LISTA");
                        builder.setView(input);
                        builder.setPositiveButton("CREA", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int id = SharedPreferenceUtility.readIdFromSharedPreference(getApplicationContext());
                                ListDatabaseManager listDatabaseManager = new ListDatabaseManager(getApplicationContext());
                                listDatabaseManager.open();
                                Long cursor = listDatabaseManager.createList(String.valueOf(input.getText()),id);
                                myAdapter.updateList(getApplicationContext());

                                dialog.cancel();
                            }
                        });
                        builder.setNegativeButton("ANNULLA", new DialogInterface.OnClickListener(){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_change: {

                switch (mCurrentLayoutManagerType) {
                    case GRID_LAYOUT_MANAGER:
                        setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
                        break;
                    case LINEAR_LAYOUT_MANAGER:
                        setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER);
                        break;
                    default:
                        setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
                }


                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }


    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (myRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) myRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                myLayoutManager = new GridLayoutManager(this, 2);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                myLayoutManager = new LinearLayoutManager(this);
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                myLayoutManager = new LinearLayoutManager(this);
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        myAdapter = new ListRecyclerAdapter(getApplicationContext());
        myRecyclerView.setAdapter(myAdapter);

        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.scrollToPosition(scrollPosition);
    }
}