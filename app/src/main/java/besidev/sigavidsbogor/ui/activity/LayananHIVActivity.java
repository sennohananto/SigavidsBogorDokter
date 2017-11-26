package besidev.sigavidsbogor.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.logging.Logger;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.api.LayananHIVServices;
import besidev.sigavidsbogor.helpers.AppConstants;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.models.LayananHIV;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static besidev.sigavidsbogor.api.RetrofitBuilder.retrofit;

public class LayananHIVActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LatLng myLatLng;
    public static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1;
    private boolean myLocationFocused;
    private LayananHIV kumpLay[];
    private CardView cardviewmaps;
    private LinearLayout llNav, llTel;
    private TextView tvNamaLay, tvAlamatLay, tvJambukaLay, tvBiayaLay;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan_hiv);
        inisialisasiRetrofit();
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cardviewmaps = (CardView) findViewById(R.id.cardviewmaps);
        llNav = (LinearLayout) findViewById(R.id.linlaynav);
        llTel = (LinearLayout) findViewById(R.id.linlaytelepon);
        tvNamaLay = (TextView) findViewById(R.id.tvNamaLayKes);
        tvAlamatLay = (TextView) findViewById(R.id.tvAlamatKes);
        tvBiayaLay = (TextView) findViewById(R.id.tvBiayaKes);
        tvJambukaLay = (TextView) findViewById(R.id.tvJamBukaKes);

//        mMap.setOnCameraMoveListener(() -> cardviewmaps.setVisibility(View.GONE));

        llNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+kumpLay[index].getLatitude()+", "+kumpLay[index].getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        llTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                if(!kumpLay[index].getNotelepon().equals("")){
                    intent.setData(Uri.parse("tel:"+kumpLay[index].getNotelepon()));
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Layanan Kesehatan ini belum memiliki no telepon",Toast.LENGTH_LONG).show();
                }
            }
        });
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            return;
        }



        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLatLng = new LatLng(location.getLatitude(),location.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(myLatLng).title("Lokasi Saya"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 14));
            }
        });

        myLocationFocused = true;
        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                if(myLocationFocused){
                    mMap.setOnMyLocationChangeListener(null);
                    myLocationFocused = false;
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void inisialisasiRetrofit(){
        LayananHIVServices services = retrofit.create(LayananHIVServices.class);
        Call<List<LayananHIV>> call = services.getLayananHIV();
        call.enqueue(new Callback<List<LayananHIV>>() {
            @Override
            public void onResponse(Call<List<LayananHIV>> call, Response<List<LayananHIV>> response) {
                List<LayananHIV> listLayananHIV = response.body();
                kumpLay = new LayananHIV[listLayananHIV.size()];
                int i = 0;
                for(LayananHIV l : listLayananHIV){
                    kumpLay[i] = l;
                    i++;
                    AppHelpers.LogCat(l.getNamalayanan());
                }
                attachLayananHIVMarkers();
            }

            @Override
            public void onFailure(Call<List<LayananHIV>> call, Throwable t) {

            }
        });
    }

    private void attachLayananHIVMarkers(){
        for(int i=0;i<kumpLay.length;i++){
            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(kumpLay[i].getLatitude()),Double.parseDouble(kumpLay[i].getLongitude())))).setTitle(kumpLay[i].getNamalayanan());
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onMarkerClick(Marker marker) {
                index=-1;
                for(int i=0;i<kumpLay.length;i++){
                        if(marker.getTitle().equals(kumpLay[i].getNamalayanan())){
                            index=i;
                        }
                }
                AppHelpers.LogCat(kumpLay[index].getAlamat());
                tvNamaLay.setText(kumpLay[index].getNamalayanan());
                tvAlamatLay.setText("Alamat : "+ kumpLay[index].getAlamat());
                if(kumpLay[index].getBiaya().equals("")){
                    tvBiayaLay.setText("Biaya : GRATIS");
                }else{
                    tvBiayaLay.setText("Biaya : "+kumpLay[index].getBiaya());
                }
                tvJambukaLay.setText(kumpLay[index].getJambuka());
                cardviewmaps.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }
}
