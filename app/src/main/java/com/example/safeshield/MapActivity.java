package com.example.safeshield;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity {

    GoogleMap gMap;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<Address> arrAdr, list;
    Geocoder gcoder;
    CardView setSosAdd;
    String address="";
    SharedPreferences pref;
    private static final int Request_code = 101;

    String lat="", lng="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setSosAdd = findViewById(R.id.setSosAdd);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getCurrentLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

        setSosAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, SosListActivity.class);
                intent.putExtra("address", address);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
        }else{
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(60000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(5000);
            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    if(locationResult == null){
                        Toast.makeText(getApplicationContext(), "current location is null", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for(Location location : locationResult.getLocations()){
                        if(location!=null){
                            Toast.makeText(getApplicationContext(), "current location is = " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            };
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            gMap = googleMap;
                            if(location!=null){
                                lat = String.valueOf(location.getLatitude());
                                lng = String.valueOf(location.getLongitude());
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                gcoder = new Geocoder(MapActivity.this);
                                try {
                                    list = (ArrayList<Address>) gcoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(list.get(0).getAddressLine(0));
                                address = list.get(0).getSubLocality();
                                googleMap.addMarker(markerOptions);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

                                pref = getSharedPreferences("location", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("loc",list.get(0).getAddressLine(0));
                                editor.putString("lat", String.valueOf(location.getLatitude()));
                                editor.putString("lng", String.valueOf(location.getLongitude()));
                                editor.apply();

                            /*googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(@NonNull LatLng latLng) {
                                    Geocoder geocoder = new Geocoder(MapActivity.this);
                                    try {
                                        arrAdr = (ArrayList<Address>) geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                        System.out.println(arrAdr.get(0).getAddressLine(0));
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    googleMap.addMarker(new MarkerOptions().position(latLng).title(arrAdr.get(0).getAddressLine(0)));
                                }
                            });*/
                            }else{
                                Toast.makeText(MapActivity.this, "Please enable your location permission", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case Request_code:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
        }
    }
}