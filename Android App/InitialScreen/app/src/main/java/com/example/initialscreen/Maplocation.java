package com.example.initialscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maplocation extends Fragment implements OnMapReadyCallback{

    private static final String maplat = "lat";
    private static final String maplon = "lon";

    private GoogleMap mMap;

    public static String latitude="";
    public static String longitude="";

    public Maplocation() {
    }

    public static Maplocation newInstance(String lat, String lon) {
        Maplocation mapfrag = new Maplocation();
        Bundle b = new Bundle();
        b.putString(maplat, lat);
        b.putString(maplon, lon);

        latitude = lat;
        longitude = lon;

        mapfrag.setArguments(b);
        return mapfrag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mapfrag = inflater.inflate(R.layout.map_location_layout,container,false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);

        return mapfrag;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (getArguments() != null) {
//            latitude = getArguments().getString(maplat);
//            longitude = getArguments().getString(maplon);

            Double finallat = Double.parseDouble(latitude);
            Double finallon = Double.parseDouble(longitude);

            Log.d("lat23", latitude);


            LatLng loc = new LatLng(finallat, finallon);
            mMap.addMarker(new MarkerOptions().position(loc)).showInfoWindow();

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));

        }

    }
}

