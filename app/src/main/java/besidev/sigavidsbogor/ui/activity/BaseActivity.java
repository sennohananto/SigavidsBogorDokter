package besidev.sigavidsbogor.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import besidev.sigavidsbogor.SigavidsApp;
import besidev.sigavidsbogor.helpers.AppConstants;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.helpers.PreferenceManager;
import besidev.sigavidsbogor.R;
import cn.refactor.library.ShapeImageView;

import static besidev.sigavidsbogor.SigavidsApp.mGoogleApiClient;
import static besidev.sigavidsbogor.ui.activity.LayananHIVActivity.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ShapeImageView navhead_photo;
    private TextView tv_nama, tv_email, tv_birthday, tv_gender;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        this.googleApiClient = SigavidsApp.getmGoogleApiClient();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = LayoutInflater.from(this).inflate(R.layout.nav_header_base, null);
        navigationView.addHeaderView(navHeader);
        navigationView.getHeaderView(0).setVisibility(View.GONE);
        //Kenalan\0
        navhead_photo = (ShapeImageView) navHeader.findViewById(R.id.navhead_photo);
        tv_nama = (TextView) navHeader.findViewById(R.id.navhead_nama);
        tv_birthday = (TextView) navHeader.findViewById(R.id.navhead_birthday);
        tv_email = (TextView) navHeader.findViewById(R.id.navhead_email);
        tv_gender = (TextView) navHeader.findViewById(R.id.navhead_gender);
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        Picasso.with(this).load(PreferenceManager.getPictureURL(this)).into(navhead_photo);

        tv_nama.setText(PreferenceManager.getDisplayName(this));
        tv_birthday.setText(PreferenceManager.getBirthday(this));
        tv_email.setText(PreferenceManager.getEmail(this));
        tv_gender.setText(PreferenceManager.getGender(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kenalihiv) {
            Intent keInfoHIV = new Intent(BaseActivity.this,InfoHIVActivity.class);
            startActivity(keInfoHIV);
        } else if (id == R.id.nav_layananhiv) {
            Intent keLayananHIV = new Intent(BaseActivity.this,LayananHIVActivity.class);
            startActivity(keLayananHIV);
        } else if (id == R.id.nav_temanpendamping) {
            Intent keTemanPendamping = new Intent(BaseActivity.this,TemanPendampingActivity.class);
            startActivity(keTemanPendamping);
        } else if (id == R.id.nav_rekammedis) {
            Intent keRekamMedis = new Intent(BaseActivity.this,RekamMedisActivity.class);
            startActivity(keRekamMedis);
        } else if (id == R.id.nav_qna) {
            Intent keQna = new Intent(BaseActivity.this,QnaActivity.class);
            startActivity(keQna);
        } else if (id == R.id.nav_about) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
            adBuilder.setView(R.layout.dialog_about)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(true)
                    .setTitle("Tentang Aplikasi Sigavids Bogor")
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .show();
        } else if (id == R.id.nav_logout){

            SharedPreferences getPrefs = android.preference.PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor edit = getPrefs.edit();
            edit.putBoolean("LoggedIn",false);
            edit.apply();

            SharedPreferences dataUser = getSharedPreferences(AppConstants.DATA_USER,MODE_PRIVATE);
            SharedPreferences.Editor clearData = dataUser.edit();
            clearData.clear();
            clearData.apply();

            Intent keLogin = new Intent(BaseActivity.this,LoginActivity.class);
            this.finish();
            startActivity(keLogin);
//            googleApiClient.connect();
//            Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(@NonNull Status status) {
//                            if(status.isSuccess())
//                            AppHelpers.LogCat("BERHASIL REVOKE ACCESS");
//                    }
//            });
//
//            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(Status status) {
//                            if (status.isSuccess())
//                            AppHelpers.LogCat("BERHASIL LOGOUT");
//                        }
//                    });
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
