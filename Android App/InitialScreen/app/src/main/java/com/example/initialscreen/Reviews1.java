package com.example.initialscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class Reviews1 extends Fragment {

    private static final String cardrev1name = "addr";
    private static final String cardrev1rating = "price";
    private static final String cardrev1text = "phone";
    private static final String cardrev1time = "category";


    TextView rev1_view;
    TextView rev2_view;
    TextView rev3_view;

    public static String name_1="";
    public static String rating_1="";
    public static String text_1="";
    public static String time_1="";

    public static String name_2="";
    public static String rating_2="";
    public static String text_2="";
    public static String time_2="";

    public static String name_3="";
    public static String rating_3="";
    public static String text_3="";
    public static String time_3="";

    public Reviews1(){}

    public static Reviews1 newInstance(String name1, String rating1, String text1, String time1,
                                       String name2, String rating2, String text2, String time2,
                                       String name3, String rating3, String text3, String time3) {
        Reviews1 reviewsfrag = new Reviews1();
        Bundle b = new Bundle();
        b.putString(cardrev1name, name1);
        b.putString(cardrev1rating, rating1);
        b.putString(cardrev1text, text1);
        b.putString(cardrev1time, time1);
        Log.d("B1 write rev", name1);
        name_1 = name1;
        rating_1 = rating1;
        text_1 = text1;
        time_1 = time1;

        name_2 = name2;
        rating_2 = rating2;
        text_2 = text2;
        time_2 = time2;

        name_3 = name3;
        rating_3 = rating3;
        text_3 = text3;
        time_3 = time3;

        reviewsfrag.setArguments(b);
        return reviewsfrag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View reviews1 = inflater.inflate(R.layout.reviews_layout, container, false);
            Log.d("B1 Inside onCreate Reviews Frag", name_1);


        rev1_view = reviews1.findViewById(R.id.textView11);
        rev1_view.setText(name_1+"\nRating:"+rating_1+"/5"+"\n\n"+text_1+"\n\n"+time_1);

        rev2_view = reviews1.findViewById(R.id.textView10);
        rev2_view.setText(name_2+"\nRating:"+rating_2+"/5"+"\n\n"+text_2+"\n\n"+time_2);

        rev3_view = reviews1.findViewById(R.id.textView12);
        rev3_view.setText(name_3+"\nRating:"+rating_3+"/5"+"\n\n"+text_3+"\n\n"+time_3);

//        if (getArguments() != null) {
//            name_1 = getArguments().getString(cardrev1name);
//            rating_1 = getArguments().getString(cardrev1rating);
//            text_1 = getArguments().getString(cardrev1text);
//            time_1 = getArguments().getString(cardrev1time);
//
////            Log.d("fragaddr", address);
//        }
//        Log.d("fragname1", name_1);
//

            return reviews1;

    }
}
