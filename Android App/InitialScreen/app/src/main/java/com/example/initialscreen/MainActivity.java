package com.example.initialscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListenerXX {

    public ArrayList<String> cardid = new ArrayList<String>();
    public ArrayList<String> cardname = new ArrayList<>();
    public ArrayList<String> cardurl = new ArrayList<>();

    public static final String cardresid = "cardid";
    public static final String cardresname = "cardname";

    ImageView reserve;

    ArrayList<String> suggestion = new ArrayList<String>();
    String geo_api = "AIzaSyBLZmOejZR198CAWqDNlk87ZLcSxfRL8Ys";

    RecyclerView busRecyclerView;
    RecycleAdapter busRecycleAdapter;
    ArrayList<TabItem> busresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        busRecyclerView = findViewById(R.id.recyclerview);
        busRecyclerView.setHasFixedSize(true);
        busRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reserve = findViewById(R.id.imageView3);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookIntent = new Intent(MainActivity.this, Reservations.class);
                startActivity(bookIntent);
            }
        });

        busresList = new ArrayList<>();

        Spinner cat = findViewById(R.id.spinner);
        ArrayList<String> catlist = new ArrayList<>();
        catlist.add("Default");
        catlist.add("Arts and Entertainment");
        catlist.add("Health and Medical");
        catlist.add("Hotels and Travel");
        catlist.add("Food");
        catlist.add("Professional Services");
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catlist);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(arrAdapter);

        Button submit = (Button) findViewById(R.id.button);
        Button clear = (Button) findViewById(R.id.button2);
        AutoCompleteTextView keyword = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        EditText distance = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText location = (EditText) findViewById(R.id.editTextTextPersonName2);
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox);

        keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0) {
                    String keyw = keyword.getText().toString();
                    String autocomurl = "https://homework8-react.wl.r.appspot.com/suggestion?keyword="+keyw;
                    Log.d("autourl", autocomurl);
                    StringRequest suggestionRequest = new StringRequest(Request.Method.GET, autocomurl,
                            response_autocom -> {
                                try {
                                    //Create a JSON object containing information from the API.
                                    JSONObject resautocom = new JSONObject(response_autocom);
                                    JSONArray categ = (JSONArray) resautocom.get("categories");
                                    JSONArray terms = (JSONArray) resautocom.get("terms");
                                    JSONObject catarr = null;
                                    JSONObject termarr = null;
//                                    busresList.clear();

                                    suggestion.clear();
                                    for (int x = 0; x < categ.length(); x++) {
                                        catarr = categ.getJSONObject(x);
                                        String autocomcat = catarr.getString("title");
                                        suggestion.add(autocomcat);
                                        Log.d("category", autocomcat);
//                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));
                                    }
                                    for (int y = 0; y < terms.length(); y++) {
                                        termarr = terms.getJSONObject(y);
                                        String autocomterms = termarr.getString("text");
                                        suggestion.add(autocomterms);
                                        Log.d("terms", autocomterms);
//                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));
                                    }
                                    Log.d("suggestion", String.valueOf(suggestion));
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            },
                            volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                    RequestQueue requestQueueautocom = Volley.newRequestQueue(MainActivity.this);
                    requestQueueautocom.add(suggestionRequest);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (MainActivity.this, android.R.layout.simple_list_item_1, suggestion);

                    keyword.setThreshold(1);
                    keyword.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox.isChecked()){
                    location.setVisibility(v.INVISIBLE);
                    location.setText("");
                }
                else{
                    location.setVisibility(v.VISIBLE);
                    location.setText("");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                keyword.setText("");
                distance.setText("");
                location.setText("");
                cat.setSelection(0);
                checkbox.setChecked(false);
                location.setVisibility(v.VISIBLE);
                keyword.setError(null);
                distance.setError(null);
                location.setError(null);
                busRecyclerView.setVisibility(v.INVISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                busRecyclerView.setVisibility(view.VISIBLE);

//                Toast.makeText(MainActivity.this, "HH", Toast.LENGTH_SHORT).show();
                String key = keyword.getText().toString();
                String dist_str = distance.getText().toString();
//                Integer dist = 0;
//                if(dist_str.isEmpty() == false) {
//                    dist = Integer.parseInt(dist_str);
//                }
                String category = cat.getSelectedItem().toString();
                if(category == "Default") {
                    category = "all";
                }
                if(category == "Arts and Entertainment") {
                    category = "arts";
                }
                if(category == "Health and Medical") {
                    category = "health";
                }
                if(category == "Hotels and Travel") {
                    category = "hotelstravel";
                }
                if(category == "Food") {
                    category = "food";
                }
                if(category == "Professional Services") {
                    category = "professional";
                }

                String loc = location.getText().toString();

                if(keyword.length() == 0){
                    keyword.setError("This field is required");
                    distance.setError(null);
                    location.setError(null);
                }
                if(keyword.length() != 0 && distance.length() == 0){
                    distance.setError("This field is required");
                    keyword.setError(null);
                    location.setError(null);
                }

                if(checkbox.isChecked() == false) {
                    if (distance.length() != 0 && location.length() == 0 && keyword.length() != 0) {
                        location.setError("This field is required");
                        keyword.setError(null);
                        distance.setError(null);
                    }
                }


                if(checkbox.isChecked() && keyword.length() != 0 && distance.length() != 0){

                    String ipUrl = "https://ipinfo.io/?token=4ab812bd3b5835";
                    Log.d("Hello", "Working");
                    String finalCategory = category;
                    StringRequest ipRequest = new StringRequest(Request.Method.GET, ipUrl,
                            response -> {
                                try{
                                    //Create a JSON object containing information from the API.
                                    JSONObject iploc = new JSONObject(response);
                                    String locdis = (String) iploc.get("loc");
                                    String[] iploct = locdis.split(",");
                                    Float lat = Float.parseFloat(iploct[0]);
                                    Float lon = Float.parseFloat(iploct[1]);


                                    String searchurl = "https://homework8-react.wl.r.appspot.com/search?keyword="+key+"&latitude="+lat+"&longitude="+lon+"&categories="+ finalCategory +"&radius="+dist_str+"&limit=10";
                                    Log.d("serurl", searchurl);
                                    StringRequest serRequest = new StringRequest(Request.Method.GET, searchurl,
                                            response_ser -> {
                                                try {
                                                    //Create a JSON object containing information from the API.
                                                    JSONObject tabres = new JSONObject(response_ser);
                                                    Log.d("Faalu", String.valueOf(tabres));

                                                    JSONArray business = (JSONArray) tabres.get("businesses");
                                                    if(business.length() == 0){
                                                        TextView tv = (TextView) findViewById(R.id.textView28);
                                                        tv.setText("No Results Found");
                                                    }
                                                    JSONObject tabrow = null;
                                                    busresList.clear();
                                                    int count = 0;
                                                    for (int i = 0; i < business.length(); i++) {
                                                        tabrow = business.getJSONObject(i);
                                                        count += 1;
                                                        String busid = tabrow.getString("id");
                                                        String bus_img_url = tabrow.getString("image_url");
                                                        String bus_name = tabrow.getString("name");
                                                        String url = tabrow.getString("url");
                                                        double ratings  = tabrow.getDouble("rating");
                                                        String str_ratings = String.valueOf(ratings);
                                                        double distance = tabrow.getDouble("distance");
                                                        distance = distance / 1609;

                                                        DecimalFormat df = new DecimalFormat("#.00");
                                                        String str_dist = df.format(distance);

                                                        cardname.add(bus_name);
                                                        cardurl.add(url);

                                                        cardid.add(busid);
                                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));

                                                        Log.d("busname", bus_name);
                                                    }
                                                    Log.d("cardid", String.valueOf(cardid));

//                                                    Log.d("search", String.valueOf(business));
//                                                    Log.d("tabrow", String.valueOf(tabrow));


                                                    busRecycleAdapter = new RecycleAdapter(MainActivity.this, busresList, MainActivity.this);
                                                    Log.d("check1", "recadapworking");
                                                    busRecyclerView.setAdapter(busRecycleAdapter);
                                                    Log.d("check2", "recadapset");

                                                }

                                                catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            },
                                            volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
                                    );
                                    RequestQueue requestQueueser = Volley.newRequestQueue(MainActivity.this);
                                    requestQueueser.add(serRequest);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            },
                            volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(ipRequest);
                }


                else if(checkbox.isChecked()==false && keyword.length() != 0 && distance.length() != 0){

                    String ipUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+loc+"&key="+geo_api;
                    Log.d("Hello", "Working");
                    String finalCategory = category;
                    StringRequest ipRequest = new StringRequest(Request.Method.GET, ipUrl,
                            response -> {
                                try{
                                    Log.d("url", ipUrl);
                                    //Create a JSON object containing information from the API.
                                    JSONObject iploc = new JSONObject(response);
                                    JSONArray result = (JSONArray) iploc.get("results");
                                    Log.d("res", String.valueOf(result));
                                    JSONObject container = result.getJSONObject(0);
                                    JSONObject geometry = (JSONObject) container.get("geometry");
                                    Log.d("Geo", String.valueOf(geometry));
                                    JSONObject loct = (JSONObject) geometry.get("location");
                                    Log.d("loct", String.valueOf(loct));
                                    Double lat = (Double) loct.getDouble("lat");
                                    Double lon = (Double) loct.getDouble("lng");

                                    Log.d("lat", String.valueOf(lat));
                                    Log.d("lon", String.valueOf(lon));


                                    String searchurl = "https://homework8-react.wl.r.appspot.com/search?keyword="+key+"&latitude="+lat+"&longitude="+lon+"&categories="+ finalCategory +"&radius="+dist_str+"&limit=10";
                                    Log.d("serurl", searchurl);
                                    StringRequest serRequest = new StringRequest(Request.Method.GET, searchurl,
                                            response_ser -> {
                                                try {
                                                    //Create a JSON object containing information from the API.
                                                    JSONObject tabres = new JSONObject(response_ser);
                                                    JSONArray business = (JSONArray) tabres.get("businesses");
                                                    JSONObject tabrow = null;
                                                    busresList.clear();
                                                    int count = 0;
                                                    for (int i = 0; i < business.length(); i++) {
                                                        tabrow = business.getJSONObject(i);
                                                        count += 1;
                                                        String busid = tabrow.getString("id");
                                                        String bus_img_url = tabrow.getString("image_url");
                                                        String bus_name = tabrow.getString("name");
                                                        String url = tabrow.getString("url");
                                                        double ratings  = tabrow.getDouble("rating");
                                                        String str_ratings = String.valueOf(ratings);
                                                        double distance = tabrow.getDouble("distance");
                                                        distance = distance / 1609;

                                                        DecimalFormat df = new DecimalFormat("#.00");
                                                        String str_dist = df.format(distance);

                                                        cardname.add(bus_name);
                                                        cardurl.add(url);

                                                        cardid.add(busid);
                                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));

                                                        Log.d("busname", bus_name);
                                                    }

//                                                    Log.d("search", String.valueOf(business));
//                                                    Log.d("tabrow", String.valueOf(tabrow));


                                                    busRecycleAdapter = new RecycleAdapter(MainActivity.this, busresList, MainActivity.this);
                                                    Log.d("check1", "recadapworking");
                                                    busRecyclerView.setAdapter(busRecycleAdapter);
                                                    Log.d("check2", "recadapset");

                                                }

                                                catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            },
                                            volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
                                    );
                                    RequestQueue requestQueueser = Volley.newRequestQueue(MainActivity.this);
                                    requestQueueser.add(serRequest);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            },
                            volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(ipRequest);
                }
//                if (key.isEmpty() || dist_str.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Enter the Data", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.d("cat1", category);
//                    Toast.makeText(MainActivity.this, "Keyword -  " + keyword.getText().toString() + " \n" + "Distance -  " + distance.getText().toString()
//                            + " \n" + "Category -  " + category + " \n" + "Location -  " + location.getText().toString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, "Category -  " + category, Toast.LENGTH_SHORT).show();
//                }
            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Log.d("SA cardId", String.valueOf(cardid));
        Log.d("SA position: ", String.valueOf(position));
        Intent cardIntent = new Intent(MainActivity.this, BusinessCard.class);

        //ExampleItem clickedItem = mExampleList.get(position);
//        cardIntent.putExtra(cardresid, cardid.get(position));
        cardIntent.putExtra("CARDID", cardid.get(position));
        cardIntent.putExtra("CardName",  cardname.get(position));
        cardIntent.putExtra("Cardurl", cardurl.get(position));
//        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());
//        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikeCount());

        startActivity(cardIntent);
    }
}