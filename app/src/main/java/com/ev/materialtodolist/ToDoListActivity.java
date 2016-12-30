package com.ev.materialtodolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

    /*
        Create a material Theme Compatible with Older Devices and Localize the application.
        Also making use of the FAB.
    */

public class ToDoListActivity extends AppCompatActivity {

    private EditText item;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item = (EditText) findViewById(R.id.itemEditText);
        ListView dynamicListView = (ListView) findViewById(R.id.itemsListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String todoItem = item.getText().toString();
                if (todoItem.length() > 0)
                {
                    // Add editText Value to the list
                    list.add(todoItem);

                    // Apply changes on the adapter to refresh the listView
                    adapter.notifyDataSetChanged();

                    // Clear the editText for the new Item
                    item.setText("");
                }
            }
        });



        // Initialize the list and add item
        list = new ArrayList<>();
        list.add("Hataki Kakashi");

        // Initialize the arrayAdapter
        adapter = new ArrayAdapter<>(ToDoListActivity.this,
                android.R.layout.simple_list_item_1, list);

        // Setting the adapter to the listView
        dynamicListView.setAdapter(adapter);


        // Delete item on the long click on the item
        dynamicListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){

                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3)
                    {
                        // Remove the item from list
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
