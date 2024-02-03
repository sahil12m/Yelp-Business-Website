package com.example.initialscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

//import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.initialscreen.ui.main.SectionsPagerAdapter;
import com.example.initialscreen.databinding.ActivityBusinessCardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BusinessCard extends AppCompatActivity {
    String address = "";
    String price = "";
    String phoneno = "";
    String status = "";
    String category = "";
    String link = "";
    String latitude = "";
    String longitude = "";
    String rev_name1 = "";
    String rev_name2 = "";
    String rev_name3 = "";
    String rating1 = "";
    String rating2 = "";
    String rating3 = "";
    String text1 = "";
    String text2 = "";
    String text3 = "";
    String time1 = "";
    String time2 = "";
    String time3 = "";
    String name = "";
    Businessdetails busdetailsfrag = new Businessdetails();
    Modalcustomdialog dialogfrag = new Modalcustomdialog();
    Maplocation maploc = new Maplocation();
    Reviews1 revfrag = new Reviews1();

    private ActivityBusinessCardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card);


        binding = ActivityBusinessCardBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        //ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        //TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //FloatingActionButton fab = binding.fab;

        Intent intent = getIntent();
        String cardid = intent.getStringExtra("CARDID");
        String cardname = intent.getStringExtra("CardName");
        String cardurl = intent.getStringExtra("Cardurl");
        Log.d("cardidno", cardid);
        Log.d("cardname", cardname);
        Log.d("cardurl", cardurl);

        // Adding back fn to toolbar
        Toolbar toolbar1 = findViewById(R.id.toolbarcard);
        toolbar1.setTitle(cardname);
        setSupportActionBar(toolbar1);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String twitter_url = "https://twitter.com/intent/tweet?text=Check "+ cardname + " " + cardurl;
        ImageButton tweetbtn = (ImageButton) findViewById(R.id.twitter);
        tweetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent twitterintent = new Intent(Intent.ACTION_VIEW);
                twitterintent.setData(Uri.parse(twitter_url));
                startActivity(twitterintent);
            }
        });

        String fb_url = "https://facebook.com/sharer/sharer.php?display=page&u="+ cardurl;
        ImageButton fbbtn = (ImageButton) findViewById(R.id.facebook);
        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbintent = new Intent(Intent.ACTION_VIEW);
                fbintent.setData(Uri.parse(fb_url));
                startActivity(fbintent);
            }
        });

        String carddetailsurl = "https://homework8-react.wl.r.appspot.com/card?id="+ cardid;
            Log.d("autourl", carddetailsurl);
            StringRequest carddetailsRequest = new StringRequest(Request.Method.GET, carddetailsurl,
                    response_carddes -> {
                        try {
                            //Create a JSON object containing information from the API.
                            JSONObject rescard = new JSONObject(response_carddes);
                            JSONObject temp1 = (JSONObject) rescard.get("location");
                            Log.d("temp1", String.valueOf(temp1));
                            JSONArray addr = (JSONArray) temp1.get("display_address");

                            if(addr != null) {
                                Log.d("temp2", String.valueOf(addr));
//
                                for (int x = 0; x < addr.length(); x++) {
                                    String str1 = addr.getString(x);
                                    str1 += " ";
                                    address += str1;
                                }
                                Log.d("adr", address);

                                price = rescard.getString("price");

                                phoneno = rescard.getString("display_phone");

                                name = rescard.getString("name");

                                JSONArray temp_category = (JSONArray) rescard.get("categories");
                                JSONObject exp1;
                                String cattitle;

                                for (int x = 0; x < temp_category.length(); x++) {
                                    exp1 = temp_category.getJSONObject(x);
                                    cattitle = exp1.getString("title");
                                    category += cattitle;
                                    if(x != temp_category.length()-1) {
                                        category += " | ";
                                    }
                                    Log.d("exp1", category);
                                }

                                JSONArray temp_hours = (JSONArray) rescard.get("hours");
                                JSONObject exp2;
                                String stat1;

                                for (int x = 0; x < temp_hours.length(); x++) {
                                    exp2 = temp_hours.getJSONObject(x);
                                    status = exp2.getString("is_open_now");
                                    //Log.d("exp2", status);
                                }

                                link = rescard.getString("url");

                                JSONObject temp2 = (JSONObject) rescard.get("coordinates");
                                latitude = temp2.getString("latitude");
                                longitude = temp2.getString("longitude");

                                Log.d("lat1", latitude);
                                Log.d("lon1", longitude);


//                                Log.d("phone", phoneno);
//                                Log.d("price", price);
                            }


                            busdetailsfrag = Businessdetails.newInstance(address, price, phoneno, category, status, link, name);

                            //FragmentTransaction fragtrans = getSupportFragmentManager().beginTransaction();
                            //fragtrans.replace(R.id.cardlayout, busdetailsfrag).commit();
                            getSupportFragmentManager().beginTransaction().replace(R.id.cardlayout, busdetailsfrag).commit();

                            maploc = Maplocation.newInstance(latitude, longitude);
                            //fragtrans.replace(R.id.cardmap, maploc).commit();

                            getSupportFragmentManager().beginTransaction().replace(R.id.cardmap, maploc).commit();

                            //dialogfrag = Modalcustomdialog.newInstance(name);
                            //getSupportFragmentManager().beginTransaction().replace(R.id.busdialog, dialogfrag).commit();

                            String cardreviewsurl = "https://homework8-react.wl.r.appspot.com/review?id="+ cardid;
                            Log.d("revurl", cardreviewsurl);
                            StringRequest cardreviewsRequest = new StringRequest(Request.Method.GET, cardreviewsurl,
                                    response_cardrev -> {
                                        try {
                                            //Create a JSON object containing information from the API.
                                            JSONObject cardreviews = new JSONObject(response_cardrev);
                                            JSONArray rev = (JSONArray) cardreviews.get("reviews");
                                            Log.d("rev", String.valueOf(rev));

                                            JSONObject exp3 = rev.getJSONObject(0);
                                            JSONObject exp4 = (JSONObject) exp3.getJSONObject("user");
                                            rev_name1 = exp4.getString("name");

                                            JSONObject exp5 = rev.getJSONObject(1);
                                            JSONObject exp6 = (JSONObject) exp5.getJSONObject("user");
                                            rev_name2 = exp6.getString("name");

                                            JSONObject exp7 = rev.getJSONObject(2);
                                            JSONObject exp8 = (JSONObject) exp7.getJSONObject("user");
                                            rev_name3 = exp8.getString("name");

                                            rating1 = exp3.getString("rating");
                                            rating2 = exp5.getString("rating");
                                            rating3 = exp7.getString("rating");

                                            text1 = exp3.getString("text");
                                            text2 = exp5.getString("text");
                                            text3 = exp7.getString("text");

                                            time1 = exp3.getString("time_created");
                                            time2 = exp5.getString("time_created");
                                            time3 = exp7.getString("time_created");

                                            time1 = time1.substring(0, 10);
                                            time2 = time2.substring(0, 10);
                                            time3 = time3.substring(0, 10);

                                            Log.d("rating1", rating1);
                                            Log.d("text1", text2);
                                            Log.d("time1", time1);


                                            Log.d("B1 Before Rev Frag", "Working");
                                            revfrag = Reviews1.newInstance(rev_name1,rating1,text1,time1,rev_name2,rating2,text2,time2,rev_name3,rating3,text3,time3);
                                            //fragtrans.replace(R.id.cardreview1, revfrag).commit();

                                            //getSupportFragmentManager().beginTransaction().replace(R.id.cardreview1, revfrag).commit();
                                            Log.d("B1 After Rev Frag", "Working");
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.d("in Reviews","hello");
                                        }
                                    },
                                    volleyError -> Toast.makeText(this.getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show()
                            );

//        if(address!=null){
//            Businessdetails myfrag = new Businessdetails();
//            FragmentTransaction fragtrans = getSupportFragmentManager().beginTransaction();
//            Bundle b = new Bundle();
//            b.putString("addr1",address);
//
//            myfrag.setArguments(b);
//            fragtrans.replace(R.id.view_pager, myfrag).commit();
//        }
                            RequestQueue requestQueuecardreviews = Volley.newRequestQueue(this.getApplicationContext());
                            requestQueuecardreviews.add(cardreviewsRequest);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    volleyError -> Toast.makeText(this.getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show()
            );

//        if(address!=null){
//            Businessdetails myfrag = new Businessdetails();
//            FragmentTransaction fragtrans = getSupportFragmentManager().beginTransaction();
//            Bundle b = new Bundle();
//            b.putString("addr1",address);
//
//            myfrag.setArguments(b);
//            fragtrans.replace(R.id.view_pager, myfrag).commit();
//        }
            RequestQueue requestQueuecarddes = Volley.newRequestQueue(this.getApplicationContext());
            requestQueuecarddes.add(carddetailsRequest);

//        String cardreviewsurl = "https://homework8-react.wl.r.appspot.com/review?id="+ cardid;
//        Log.d("revurl", cardreviewsurl);
//        StringRequest cardreviewsRequest = new StringRequest(Request.Method.GET, cardreviewsurl,
//                response_cardrev -> {
//                    try {
//                        //Create a JSON object containing information from the API.
//                        JSONObject cardreviews = new JSONObject(response_cardrev);
//                        JSONArray rev = (JSONArray) cardreviews.get("reviews");
//                        Log.d("rev", String.valueOf(rev));
//
//                        JSONObject exp3 = rev.getJSONObject(0);
//                        JSONObject exp4 = (JSONObject) exp3.getJSONObject("user");
//                        rev_name1 = exp4.getString("name");
//
//                        JSONObject exp5 = rev.getJSONObject(1);
//                        JSONObject exp6 = (JSONObject) exp5.getJSONObject("user");
//                        rev_name2 = exp6.getString("name");
//
//                        JSONObject exp7 = rev.getJSONObject(2);
//                        JSONObject exp8 = (JSONObject) exp7.getJSONObject("user");
//                        rev_name3 = exp8.getString("name");
//
//                        rating1 = exp3.getString("rating");
//                        rating2 = exp5.getString("rating");
//                        rating3 = exp7.getString("rating");
//
//                        text1 = exp3.getString("text");
//                        text2 = exp5.getString("text");
//                        text3 = exp7.getString("text");
//
//                        time1 = exp3.getString("time_created");
//                        time2 = exp5.getString("time_created");
//                        time3 = exp7.getString("time_created");
//
//                        time1 = time1.substring(0, 10);
//                        time2 = time2.substring(0, 10);
//                        time3 = time3.substring(0, 10);
//
//                        Log.d("rating1", rating1);
//                        Log.d("text1", text2);
//                        Log.d("time1", time1);
//
//
////                        Businessdetails busdetailsfrag = Businessdetails.newInstance(address, price, phoneno, category, status, link);
//                        Reviews1 revfrag = Reviews1.newInstance(rev_name1,rating1,text1,time1);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.cardreview, revfrag).commit();
//
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                },
//                volleyError -> Toast.makeText(this.getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show()
//        );
//
////        if(address!=null){
////            Businessdetails myfrag = new Businessdetails();
////            FragmentTransaction fragtrans = getSupportFragmentManager().beginTransaction();
////            Bundle b = new Bundle();
////            b.putString("addr1",address);
////
////            myfrag.setArguments(b);
////            fragtrans.replace(R.id.view_pager, myfrag).commit();
////        }
//        RequestQueue requestQueuecardreviews = Volley.newRequestQueue(this.getApplicationContext());
//        requestQueuecardreviews.add(cardreviewsRequest);

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
}