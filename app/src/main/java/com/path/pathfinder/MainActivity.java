package com.path.pathfinder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LinearLayout layout;
    LinearLayout Mainview, twowheeler,threewheeler,ace,pickup,mini;
    RelativeLayout navbar;
    Button bookcar;
    TextView droplocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Spinner spinnerpayments = findViewById(R.id.spinner_options_payment);

        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.payments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerpayments.setAdapter(adapter);


        bookcar = findViewById(R.id.bookcar);
        layout =  findViewById(R.id.container);
        navbar = findViewById(R.id.InnerRelativeLayout);
        twowheeler = findViewById(R.id.twowheeler);
        threewheeler = findViewById(R.id.threewheeler);
        ace = findViewById(R.id.ace);
        pickup = findViewById(R.id.pickup);
        mini = findViewById(R.id.mini);
        droplocation = findViewById(R.id.droplocation);


        twowheeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookcar.setText("Book 2 Wheeler");
            }
        });

        threewheeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookcar.setText("Book 3 Wheeler");
            }
        });

        ace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookcar.setText("Book Ace");
            }
        });

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookcar.setText("Book Pickup");
            }
        });

        mini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookcar.setText("Book Mini");
            }
        });

        bookcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View recievercard = getLayoutInflater().inflate(R.layout.contact_layout,null);
                layout.removeAllViews();
                navbar.removeAllViews();
                layout.addView(recievercard);

                Button confirm = findViewById(R.id.confirm);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        ViewGroup viewGroup = findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.succesdialouge_layout, viewGroup, false);
                        builder.setView(dialogView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });

            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(locationcred.getLangtitude(), locationcred.getLongtitude());
        droplocation.setText(locationcred.location);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in delhi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}