    package com.example.mymap;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        final LatLng ibirapuera = new LatLng(-23.587097, -46.657635);

        CircleOptions circleOpitions = new CircleOptions();
        circleOpitions.center(ibirapuera);
        circleOpitions.radius(300);
        circleOpitions.fillColor(android.R.color.black);
        mMap.addCircle(circleOpitions);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double latitudes = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,"onClick Lat"+latitudes+"Long"+longitude , Toast.LENGTH_SHORT).show();

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(ibirapuera);
                polylineOptions.add(latLng);
                polylineOptions.color(Color.BLUE);
                polylineOptions.width(15);
                mMap.addPolyline(polylineOptions);
                mMap.addMarker(new MarkerOptions().position(latLng)
                        .title("Ibirapuera")
                        .snippet("Local")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.batmanicon)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                CircleOptions circleOpitions = new CircleOptions();
                circleOpitions.center(latLng);
                circleOpitions.radius(300);
                circleOpitions.fillColor(android.R.color.black);
                mMap.addCircle(circleOpitions);

            }
        });

      mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
          @Override
          public void onMapLongClick(LatLng latLng) {

              Double latitudes = latLng.latitude;
              Double longitude = latLng.longitude;

              Toast.makeText(MapsActivity.this,"onClick Lat"+latitudes+"Long"+longitude , Toast.LENGTH_SHORT).show();

              mMap.addMarker(new MarkerOptions().position(latLng)
                      .title("Ibirapuera")
                      .snippet("Local")
                      .icon(BitmapDescriptorFactory.fromResource(R.drawable.batmanicon)));
              mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
          }
      });

        mMap.addMarker(new MarkerOptions().position(ibirapuera)
                .title("Ibirapuera")
              .icon(BitmapDescriptorFactory.fromResource(R.drawable.batmanicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera, 12));
    }
}