package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.api.MasyarakatServices;
import besidev.sigavidsbogor.api.RetrofitBuilder;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.models.Masyarakat;
import cn.refactor.library.ShapeImageView;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDoneLogin extends AppCompatActivity {

    private TextView tvNama, tvEmail;
    private ShapeImageView ivFoto;
//    private GoogleSignInAccount acct;
    private Thread done;
    private Animation animFadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_login);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));

        tvNama = (TextView) findViewById(R.id.ws_nama);
        tvEmail = (TextView) findViewById(R.id.ws_email);
        ivFoto = (ShapeImageView) findViewById(R.id.ws_foto);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

//        acct = getIntent().getParcelableExtra("dataAkun");

        Picasso.with(getApplicationContext()).load(String.valueOf(besidev.sigavidsbogor.helpers.PreferenceManager.getPictureURL(getApplicationContext()))).resize(500, 500).into(ivFoto);
        tvNama.setText(besidev.sigavidsbogor.helpers.PreferenceManager.getDisplayName(getApplicationContext()));
        tvEmail.setText(besidev.sigavidsbogor.helpers.PreferenceManager.getEmail(getApplicationContext()));
        ivFoto.setAnimation(animFadeIn);
        inisialisasiRetrofit();
        done = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 5000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent keMain = new Intent(ActivityDoneLogin.this,BaseActivity.class);
                    finish();
                    startActivity(keMain);

                } catch (InterruptedException e) {
                    // do nothing
                } finally {
//                    ActivityDoneLogin.this.finish();
                }
            }
        };

        done.start();

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e = getPrefs.edit();

        //  Edit preference to make it false because we don't want this to run again
        e.putBoolean("LoggedIn", true);

        //  Apply changes
        e.apply();
    }

    private void inisialisasiRetrofit(){
        MasyarakatServices services = RetrofitBuilder.retrofit.create(MasyarakatServices.class);
        services.loginMasyarakat(besidev.sigavidsbogor.helpers.PreferenceManager.getEmail(this), besidev.sigavidsbogor.helpers.PreferenceManager.getDisplayName(this), besidev.sigavidsbogor.helpers.PreferenceManager.getBirthday(this), besidev.sigavidsbogor.helpers.PreferenceManager.getGender(this), besidev.sigavidsbogor.helpers.PreferenceManager.getPictureURL(this)).enqueue(new Callback<Masyarakat>() {
            @Override
            public void onResponse(retrofit2.Call<Masyarakat> call, Response<Masyarakat> response) {
                AppHelpers.LogCat("email baru, berhasil tambah");
            }

            @Override
            public void onFailure(retrofit2.Call<Masyarakat> call, Throwable t) {

            }
        });

    }
}