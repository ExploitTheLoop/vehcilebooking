package com.path.pathfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class SearchBarMap extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap Mmap;
    SearchView searchView;
    SupportMapFragment MapFragment;
    LinearLayout explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar_map);
        searchView=(SearchView) findViewById(R.id.location);
        explore = (LinearLayout) findViewById(R.id.explore);
        MapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapg);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList=null;
                if(location!= null || !location.equals("")){
                    Geocoder gecoder = new Geocoder(SearchBarMap.this);
                    try {
                        addressList = gecoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address= addressList.get(0);
                    LatLng latlng =new LatLng(address.getLatitude(),address.getLongitude());
                    Mmap.addMarker(new MarkerOptions().position(latlng).title(location));
                    Mmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
                    locationcred.Setlang(address.getLatitude());
                    locationcred.setlong(address.getLongitude());
                    locationcred.location = address.toString();


                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!locationcred.location.equals("")){
                    startActivity(new Intent(SearchBarMap.this,MainActivity.class));
                }

            }
        });
        MapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Mmap = googleMap;
    }
}