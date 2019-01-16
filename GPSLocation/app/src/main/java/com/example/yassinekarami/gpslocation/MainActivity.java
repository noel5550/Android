package com.example.yassinekarami.gpslocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.os.Debug;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.location.LocationListener;
import android.content.Intent;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    TextView text;

    LocationManager locationManager;
    double myLatitude;
    double myLongitude;

    String adress = "";
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
          //      text.append("\n" + location.getAltitude() + "\n" + location.getLongitude());
                myLatitude = location.getLatitude();
                myLongitude = location.getLongitude();
                adress = getAdress(myLatitude,myLongitude);
                text.setText(adress);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION
                                                ,Manifest.permission.ACCESS_COARSE_LOCATION},10);

            return;
        }

        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case 10 :
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                break;
        }
    }

    private String getAdress(double latitude, double longitude)
    {
        String adress ="";
        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
        try
        {
            List<Address> adresseList = geocoder.getFromLocation(latitude,longitude,1);
            adress = adresseList.get(0).getAddressLine(0);
        }catch(IOException e )
        {
            text.setText("erreur localisation");
        };
        return adress;
    }


}