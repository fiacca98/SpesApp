package com.example.luigi.spesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    public static LayoutManagerType mCurrentLayoutManagerType;

    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        myAdapter = new MyRecyclerAdapter(getApplicationContext());
        myLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
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

        myAdapter = new MyRecyclerAdapter(getApplicationContext());
        myRecyclerView.setAdapter(myAdapter);

        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.scrollToPosition(scrollPosition);
    }
}