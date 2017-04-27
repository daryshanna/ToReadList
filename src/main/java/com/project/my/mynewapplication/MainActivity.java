package com.project.my.mynewapplication;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.TextView;
import android.view.View;

import android.content.Intent;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;


public class MainActivity extends AppCompatActivity {


    @Override
    public void setTheme(@StyleRes int theme) {
        currentTheme = theme;
        super.setTheme(theme);
    }
    private int currentTheme = 0;

    Toolbar toolbar;

    private ArrayList<String> items = null;
    private ArrayAdapter<String> itemsAdapter = null;
    private ListView lvItems = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = getArrayVal(getApplicationContext());
        //items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        Collections.addAll(items, "Invisible Monsters", "Veronica decides to die");
        //items.add("Invisible Monsters");
        //items.add("Veronica decides to die");

        storeArrayVal(items, getApplicationContext());

        setupListViewListener();

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("BookName", lvItems.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

    }


    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, final int pos, long id) {
                        // Remove the item within array at position
                       // items.remove(pos);
                        // Refresh the adapter
                       // itemsAdapter.notifyDataSetChanged();
                        String selectedItem = ((TextView) item).getText().toString();
                        if (selectedItem.trim().equals(items.get(pos).trim())) {
                            removeElement(selectedItem, pos);
                        } else {
                            Toast.makeText(getApplicationContext(),"Error Removing Element", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {

        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        storeArrayVal(items, getApplicationContext());



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean itemSelected = super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.settings_id:
                Intent intent = new Intent(this, preference.class);
                this.startActivity(intent);
                break;
            default:
                return itemSelected;
        }
        return true;
    }
    public static void storeArrayVal( ArrayList<String> inArrayList, Context context)
    {
        Set<String> WhatToWrite = new HashSet<String>(inArrayList);
        SharedPreferences WordSearchPutPrefs = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = WordSearchPutPrefs.edit();
        prefEditor.putStringSet("myArray", WhatToWrite);
        prefEditor.commit();
    }

    public static ArrayList getArrayVal( Context dan)
    {
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues",Activity.MODE_PRIVATE);
        Set<String> tempSet = new HashSet<String>();
        tempSet = WordSearchGetPrefs.getStringSet("myArray", tempSet);
        return new ArrayList<String>(tempSet);
    }

    public void removeElement(String selectedItem, final int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                items.remove(pos);
                Collections.sort(items);
                storeArrayVal(items, getApplicationContext());
                lvItems.setAdapter(itemsAdapter);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }



}


