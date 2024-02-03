package com.example.initialscreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Businessdetails extends Fragment {

    private static final String cardbusaddr = "addr";
    private static final String cardprice = "price";
    private static final String cardphone = "phone";
    private static final String cardcategory = "category";
    private static final String cardstatus = "status";
    private static final String cardlink = "link";
    private static final String cardname = "name";

    //SendMessage SM;

    TextView address_view;
    TextView price_view;
    TextView phone_view;
    TextView category_view;
    TextView status_view;
    TextView link_view;
    Button submit;
    private static String addresstxt = "";
    public static String pricetxt = "";
    public static String phonetxt = "";
    public static String categorytxt = "";
    public static String statustxt = "";
    public static String linktxt = "";
    public static String nametxt = "";

    public static Businessdetails newInstance(String address, String price, String phone, String category, String status, String link, String name) {
        Businessdetails busdetailsfrag = new Businessdetails();
        Bundle b = new Bundle();
        b.putString(cardbusaddr, addresstxt);
        b.putString(cardprice, pricetxt);
        b.putString(cardphone, phonetxt);
        b.putString(cardcategory, categorytxt);
        b.putString(cardstatus, statustxt);
        b.putString(cardlink, linktxt);
        b.putString(cardname, nametxt);

        addresstxt = address;
        pricetxt = price;
        phonetxt = phone;
        categorytxt = category;
        statustxt = status;
        linktxt = link;
        nametxt = name;


        busdetailsfrag.setArguments(b);
        return busdetailsfrag;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View busdetailsfrag = inflater.inflate(R.layout.business_details_layout, container, false);

        //        return inflater.inflate(R.layout.business_details_layout,container,false);
//        address_view = busdetailsfrag.findViewById(R.id.textView13);
//        address_view.setText("NEW TEXT");

//        Bundle data = this.getArguments();
        address_view = busdetailsfrag.findViewById(R.id.textView13);
        price_view = busdetailsfrag.findViewById(R.id.textView14);
        phone_view = busdetailsfrag.findViewById(R.id.textView16);
        category_view = busdetailsfrag.findViewById(R.id.textView20);
        status_view = busdetailsfrag.findViewById(R.id.textView18);
        link_view = busdetailsfrag.findViewById(R.id.textView22);
        submit = busdetailsfrag.findViewById(R.id.button3);
//
//        address_view.setText("Testing set text");
//        if (getArguments() != null) {
////            cardbusaddr = data.getString("addr1");
//            address = getArguments().getString(cardbusaddr);
//            price = getArguments().getString(cardprice);
//            phone = getArguments().getString(cardphone);
//            category = getArguments().getString(cardcategory);
//            status = getArguments().getString(cardstatus);
//            link = getArguments().getString(cardlink);
//            name = getArguments().getString(cardname);
//
//        }
//        Log.d("fragaddr", address);
        if (statustxt == "true") {
            status_view.setText("Open Now");
            status_view.setTextColor(Color.GREEN);

        } else if (statustxt == "false") {
            status_view.setText("Closed");
            status_view.setTextColor(Color.RED);
        }
        String linkedText = String.format("<a href=\"%s\">Business Link</a> ", linktxt);

        link_view.setText(Html.fromHtml(linkedText));
        link_view.setMovementMethod(LinkMovementMethod.getInstance());
        address_view.setText(addresstxt);
        price_view.setText(pricetxt);
        phone_view.setText(phonetxt);
        category_view.setText(categorytxt);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modalcustomdialog dialog = new Modalcustomdialog();
                //Put the value
                Bundle args = new Bundle();
                args.putString(cardname, nametxt);
                Log.d("Testaname",nametxt);
                dialog.setArguments(args);
                args.putSerializable("SOME_KEY",nametxt);

                getParentFragmentManager().setFragmentResult("busdes", args);
                //SM.sendData(name);
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });


//        if (data != null) {
//            cardbusaddr = data.getString("addr");
//
//            Log.d("SN card", cardbusaddr);
//
//        }


        //
//            String carddetailsurl = "https://homework8-react.wl.r.appspot.com/card?id="+ cardbusid;
//            Log.d("autourl", carddetailsurl);
//            StringRequest carddetailsRequest = new StringRequest(Request.Method.GET, carddetailsurl,
//                    response_carddes -> {
//                        try {
//                            //Create a JSON object containing information from the API.
//                            JSONObject rescard = new JSONObject(response_carddes);
//                            JSONObject temp1 = (JSONObject) rescard.get("location");
//                            Log.d("temp1", String.valueOf(temp1));
//                            JSONArray addr = (JSONArray) temp1.get("display_address");
//
//                            if(addr != null) {
//                                Log.d("temp2", String.valueOf(addr));
////
//                                for (int x = 0; x < addr.length(); x++) {
//                                    String str1 = addr.getString(x);
//                                    str1 += " ";
//                                    address += str1;
//                                }
//                                Log.d("addr_view", (String) address_view.getText());
//                                Log.d("adr", address);
//                                address_view.setText(address);
//
//
////                                Log.d("adr", address);
//                            }
//
//
////                            JSONArray terms = (JSONArray) resautocom.get("terms");
////                            JSONObject catarr = null;
////                            JSONObject termarr = null;
//////                                    busresList.clear();
////
////
////                            for (int x = 0; x < categ.length(); x++) {
////                                catarr = categ.getJSONObject(x);
////                                String autocomcat = catarr.getString("title");
////                                Log.d("category", autocomcat);
//////                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));
////                            }
////                            for (int y = 0; y < terms.length(); y++) {
////                                termarr = terms.getJSONObject(y);
////                                String autocomterms = termarr.getString("text");
////                                Log.d("terms", autocomterms);
////                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));
////                            }
//                        }
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    },
//                    volleyError -> Toast.makeText(getActivity().getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show()
//            );
//
//            RequestQueue requestQueuecarddes = Volley.newRequestQueue(getActivity().getApplicationContext());
//            requestQueuecarddes.add(carddetailsRequest);
//        }

        //}
        return busdetailsfrag;
        // return inflater.inflate(R.layout.business_details_layout, container, false);

    }
}

//    interface SendMessage {
//        void sendData(String message);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            SM = (SendMessage) getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Error in retrieving data. Please try again");
//        }
//    }
//}

//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        Log.d("OnViewCreate", "OnViewCreate");
//        super.onViewCreated(view, savedInstanceState);

//        address_view = view.findViewById(R.id.textView13);
//        price_view = view.findViewById(R.id.textView14);
//        phone_view = view.findViewById(R.id.textView16);
//        category_view = view.findViewById(R.id.textView20);
//        status_view = view.findViewById(R.id.textView18);
//        link_view = view.findViewById(R.id.textView22);
//
////        address_view.setText("Testing set text");
//        if (getArguments() != null) {
////            cardbusaddr = data.getString("addr1");
//            address = getArguments().getString(cardbusaddr);
//            price = getArguments().getString(cardprice);
//            phone = getArguments().getString(cardphone);
//            category = getArguments().getString(cardcategory);
//            status = getArguments().getString(cardstatus);
//            link = getArguments().getString(cardlink);
////            Log.d("fragaddr", address);
//        }
////        Log.d("cardaddr", address);
//            if (status == "true") {
//                status_view.setText("Open Now");
//                status_view.setTextColor(Color.GREEN);
//
//            } else if (status == "false") {
//                status_view.setText("Closed");
//                status_view.setTextColor(Color.RED);
//            }
//            String linkedText = String.format("<a href=\"%s\">Business Link</a> ", link);
//
//            link_view.setText(Html.fromHtml(linkedText));
//            link_view.setMovementMethod(LinkMovementMethod.getInstance());
//            address_view.setText(address);
//            price_view.setText(price);
//            phone_view.setText(phone);
//            category_view.setText(category);




//        address_view = view.findViewById(R.id.textView13);
//          Bundle data = getArguments();
//            if(data != null){
//                cardbusid = data.getString("cardid1");
//
//                Log.d("SN card", cardbusid);
//
//                String carddetailsurl = "https://homework8-react.wl.r.appspot.com/card?id="+ cardbusid;
//                Log.d("autourl", carddetailsurl);
//                StringRequest carddetailsRequest = new StringRequest(Request.Method.GET, carddetailsurl,
//                        response_carddes -> {
//                            try {
//                                //Create a JSON object containing information from the API.
//                                JSONObject rescard = new JSONObject(response_carddes);
//                                JSONObject temp1 = (JSONObject) rescard.get("location");
//                                Log.d("temp1", String.valueOf(temp1));
//                                JSONArray addr = (JSONArray) temp1.get("display_address");
//
//                                if(addr != null) {
//                                    Log.d("temp2", String.valueOf(addr));
//    //
//                                    for (int x = 0; x < addr.length(); x++) {
//                                        String str1 = addr.getString(x);
//                                        str1 += " ";
//                                        address += str1;
//                                    }
//                                    Log.d("addr_view", (String) address_view.getText());
//
////                                    address_view.setText(address);
//
//
//    //                                Log.d("adr", address);
//                                }
//
//
//    //                            JSONArray terms = (JSONArray) resautocom.get("terms");
//    //                            JSONObject catarr = null;
//    //                            JSONObject termarr = null;
//    ////                                    busresList.clear();
//    //
//    //
//    //                            for (int x = 0; x < categ.length(); x++) {
//    //                                catarr = categ.getJSONObject(x);
//    //                                String autocomcat = catarr.getString("title");
//    //                                Log.d("category", autocomcat);
//    ////                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));
//    //                            }
//    //                            for (int y = 0; y < terms.length(); y++) {
//    //                                termarr = terms.getJSONObject(y);
//    //                                String autocomterms = termarr.getString("text");
//    //                                Log.d("terms", autocomterms);
//    //                                        busresList.add(new TabItem(count, bus_img_url, bus_name, str_ratings, str_dist));
//    //                            }
//                            }
//                            catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        },
//                        volleyError -> Toast.makeText(getActivity().getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show()
//                );
//
//                RequestQueue requestQueuecarddes = Volley.newRequestQueue(getActivity().getApplicationContext());
//                requestQueuecarddes.add(carddetailsRequest);
//            }
//            Log.d("adr", address);
//            address_view.setText(address);
//    }
//
