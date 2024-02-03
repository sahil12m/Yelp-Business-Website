package com.example.initialscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Reservations extends AppCompatActivity{
    RecyclerView bookRecyclerView;
    BookingsAdapter bookingsAdapterAdapter;
    ArrayList<BookItem> bookList;
    ConstraintLayout coordinatorLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        Toolbar toolbar2 = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar2);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coordinatorLayout = findViewById(R.id.clayout);


        bookRecyclerView = findViewById(R.id.recyclerview2);
        bookRecyclerView.setHasFixedSize(true);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();

         sharedPreferences = getApplication().getSharedPreferences("reservation", Context.MODE_PRIVATE);
         Log.d("SHARED PREF", String.valueOf(sharedPreferences.getAll()));
        String reservationbooking = sharedPreferences.getString("reservation", null);
        Gson gson = new Gson();


        Set<String> it = sharedPreferences.getAll().keySet();
        Log.d("SAA Set keys:", String.valueOf(it));
        Type type1 = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        for(String key: it){
            List<Map<String, String>> resermap = gson.fromJson(sharedPreferences.getString(key,""), type1);
//            HashMap<String, String> el = gson.fromJson(sharedPreferences.getString(key,""), type1);
            Map<String, String> el = (Map<String, String>) resermap.get(0);
            String date = el.get("date");
            String time = el.get("time");
            String email = el.get("email");
            String name = el.get("name");
            Log.d("Saahil date", date);
            Log.d("Saahil name", name);
            bookList.add(new BookItem(1, name, date, time, email));

        }
        Log.d("BOOK LIST SIXE", String.valueOf(bookList.size()));
        bookingsAdapterAdapter = new BookingsAdapter(this, bookList);
        Log.d("check1", "recadapworking");
        bookRecyclerView.setAdapter(bookingsAdapterAdapter);


/*
        if (reservationbooking != null) {
            String storedresstr = sharedPreferences.getString("reservation", null);
//            Type type = new TypeToken<List<Map<String, String>>>() {
//            }.getType();
//            List<Map<String, String>> resermap = gson.fromJson(storedresstr, type);
            Set<String> it = sharedPreferences.getAll().keySet();
            Log.d("SAA Set keys:", String.valueOf(it));
//            String resermapstr = gson.toJson(resermap);
//            Log.d("Sharedpref", resermapstr);
            int count = 0;
//            for(int i = 0;i<resermap.size();i++){
//                Map<String, String> el = resermap.get(i);
//                String date = el.get("date");
//                String time = el.get("time");
//                String email = el.get("email");
//                String name = el.get("name");
//                bookList.add(new BookItem(1, name, date, time, email));
//
//            }
            Type type1 = new TypeToken<Map<String, String>>() {
            }.getType();
            for(String key: it){
//                Map<String, String> el = resermap.get(i);

                Map<String, String> el = gson.fromJson(sharedPreferences.getString(key,""), type1);

                String date = el.get("date");
                String time = el.get("time");
                String email = el.get("email");
                String name = el.get("name");
                Log.d("Saahil date", date);
                Log.d("Saahil name", name);
                bookList.add(new BookItem(1, name, date, time, email));

            }
            Log.d("BOOK LIST SIXE", String.valueOf(bookList.size()));
            bookingsAdapterAdapter = new BookingsAdapter(this, bookList);
            Log.d("check1", "recadapworking");
            bookRecyclerView.setAdapter(bookingsAdapterAdapter);


//            for (Map<String, String> map: resermap) {
//                count += 1;
//                String tempbname = map.get("name");
//                String tempbdate = map.get("date");
//                String temptime = map.get("time");
//                String tempemail = map.get("email");
////                Log.d("tempbname::", tempbname);
//
//            }

        }
        */

        enableSwipeToDeleteAndUndo();

        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                String bNameRemove="";
                final String item = String.valueOf(bookingsAdapterAdapter.getData().get(position));
                bNameRemove = bookingsAdapterAdapter.getData().get(position).getbname();

                bookingsAdapterAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        bookingsAdapterAdapter.restoreItem(item, position);
                        bookRecyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                Log.d("SAA shared [refall B", String.valueOf(sharedPreferences.getAll()));
                Log.d("BUS NAME: ", bNameRemove);
                editor.remove(bNameRemove);
                editor.commit();
                Log.d("SAA shared [refall A", String.valueOf(sharedPreferences.getAll()));


            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(bookRecyclerView);

    }


}