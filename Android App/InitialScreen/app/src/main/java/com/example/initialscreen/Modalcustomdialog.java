package com.example.initialscreen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Modalcustomdialog extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "MyCustomDialog";
    private static final String cardbusname = "busname";

    private EditText email, datetxt, timetxt;
    TextView nametxt;
    private Button Submit, Cancel;
    public static int date = 0;
    public static int month = 0;
    public static int year = 0;
    public static int timeh = 0;
    public static int mins = 0;
    public static int time_int = 0;
    public static String time = "";
    public static String emailtxt = "";
    public static String datetxt1 = "";
    private static String bus_name="Casa Noodle Teriyaki";


//    public static Modalcustomdialog newInstance(String busname) {
//        Modalcustomdialog modalcustomdialog = new Modalcustomdialog();
//        Bundle b = new Bundle();
//        b.putString(cardbusname, busname);
//
//        modalcustomdialog.setArguments(b);
//        return modalcustomdialog;
//    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {

    }

    public void datepick(){
        Calendar mCalendar = Calendar.getInstance();
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH);
        date = mCalendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                datetxt.setText(date+"-"+month+"-"+year);
            }
        }, year, month, date);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    public void timepick(){
        Calendar mCalendar = Calendar.getInstance();
        timeh = mCalendar.get(Calendar.HOUR_OF_DAY);
        mins = mCalendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int timeh, int mins) {
                Log.d("hour", String.valueOf(timeh));
                timetxt.setText(timeh + ":" + mins);
            }
        }, timeh, mins, false);
        timePickerDialog.show();
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.customdialog, container, false);

        //String bus_name = getArguments().getString(cardbusname);

        getParentFragmentManager().setFragmentResultListener("busdes", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                bus_name = result.getString(cardbusname);
                bus_name = (String) result.getSerializable("SOME_KEY");

                Log.d("SN   busname", bus_name);
                nametxt = view.findViewById(R.id.textView23);
                nametxt.setText(bus_name);
//                nametxt.setText(bus_name);
            }
        });
//        bus_name = (String) savedInstanceState.getSerializable("SOME_KEY");
//        nametxt = view.findViewById(R.id.textView23);
//        Log.d("SN   busname", bus_name);


        Submit = view.findViewById(R.id.button4);
        Cancel = view.findViewById(R.id.button5);
        datetxt = view.findViewById(R.id.editTextTextPersonName4);
        timetxt = view.findViewById(R.id.editTextTextPersonName5);
        email = view.findViewById(R.id.editTextTextPersonName3);

//        if (getArguments() != null) {
//            bus_name = getArguments().getString(cardbusname);
//        }



        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepick();
            }
        });

        timetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timepick();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");
                time = timetxt.getText().toString();
                time_int = Integer.parseInt(time.split(":")[0]);
                Log.d("time1", String.valueOf(time_int));

                datetxt1 = datetxt.getText().toString();

                emailtxt = email.getText().toString();

                if(!isValid(emailtxt)){
                    Toast.makeText(getActivity(), "Invalid Email Address", Toast.LENGTH_LONG).show();
                }

                else if(isValid(emailtxt)){
                    if(time_int >= 17 || time_int <= 10){
                        Log.d("Time1 inside", "here");
                        Toast.makeText(getActivity(), "Time should be between 10am and 5pm", Toast.LENGTH_LONG).show();
                    }
                }

                if(isValid(emailtxt) && (time_int <= 17 && time_int >= 10) && datetxt1 != "") {
                    Toast.makeText(getActivity(), "Reservation Booked", Toast.LENGTH_LONG).show();
                    Map<String, String> reservations = new TreeMap<>();
                    reservations.put("name", bus_name);
                    reservations.put("email", emailtxt);
                    reservations.put("date", datetxt1);
                    reservations.put("time", time);

                    Gson gson = new Gson();

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("reservation", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.clear();
//                    editor.commit();
                    Log.d("speff all", String.valueOf(sharedPreferences.getAll()));


                    String reservationbooking = sharedPreferences.getString("reservation", null);

                    if (reservationbooking != null) {
                        String storedresstr = sharedPreferences.getString("reservation", null);
                        Type type = new TypeToken<List<Map<String, String>>>() {
                        }.getType();
                        List<Map<String, String>> resermap = gson.fromJson(storedresstr, type);

                        resermap.add(reservations);
                        Log.d("S reservation: ", String.valueOf(reservations));

//                        String resermapstr = gson.toJson(resermap);
                        String resermapstr = gson.toJson(reservations);
                        Log.d("SAA resermapStr", resermapstr);
                        sharedPreferences.edit().putString(bus_name, resermapstr).apply();

                        Log.d("SAA sharedpref: --", String.valueOf(sharedPreferences.getAll()));

                        Log.d("Sharedprefempty", resermapstr);
                    }
                    else {
                        List<Map<String, String>> resermap = new ArrayList<>();
                        resermap.add(reservations);
                        String resermapstr = gson.toJson(resermap);

                        sharedPreferences.edit().putString(bus_name, resermapstr).apply();
                        Log.d("SAA sharedpref: 22", String.valueOf(sharedPreferences.getAll()));
                        Log.d("Sharedprefappend", resermapstr);
                    }
                }

                getDialog().dismiss();
            }
        });

        return view;
    }
}

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try{
//            mOnInputSelected = (OnInputSelected) getTargetFragment();
//        }catch (ClassCastException e){
//            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
//        }
//    }
//}
