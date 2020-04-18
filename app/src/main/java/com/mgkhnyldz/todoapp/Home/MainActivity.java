package com.mgkhnyldz.todoapp.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mgkhnyldz.todoapp.AddToDo.AddToDoActivity;
import com.mgkhnyldz.todoapp.utils.DataModel;
import com.mgkhnyldz.todoapp.utils.MyAdapter.MyAdapter;
import com.mgkhnyldz.todoapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab, fabFrameLayout;
    private RecyclerView recyclerView;
    private ConstraintLayout rootContainer;
    private FrameLayout recycleFrame;
    private MyAdapter adapter;
    private ArrayList<DataModel> model;
    private DataModel dataModel;
    private String header, date;
    private static final int ACTIVITY_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addToDo();
        addToDoFrame();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            boolean i = data.getBooleanExtra("kontrol", false);
            header = data.getStringExtra("header");
            date = data.getStringExtra("date");
            if (i) {

                rootContainer.setVisibility(View.GONE);
                recycleFrame.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
                model.add(new DataModel(header, date));
                adapter.notifyDataSetChanged();


                ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        adapter.deleteItem(viewHolder.getAdapterPosition());
                    }
                };
                new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
            }
        }



    }

    private void addToDoFrame() {
        fabFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivityForResult(i, ACTIVITY_CODE);
            }
        });
    }

    private void init() {
        fab = findViewById(R.id.floatinAddButton);
        fabFrameLayout = findViewById(R.id.fabFrameLayout);
        recyclerView = findViewById(R.id.recycleview);
        recycleFrame = findViewById(R.id.recycleFrame);
        rootContainer = findViewById(R.id.rootContainer);
        model = new ArrayList<>();
        adapter = new MyAdapter(this, model, this);
    }


    private void addToDo() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivityForResult(i, ACTIVITY_CODE);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.preferences:
                Toast.makeText(getApplicationContext(), "preference", Toast.LENGTH_LONG).show();
                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(), "about", Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}
