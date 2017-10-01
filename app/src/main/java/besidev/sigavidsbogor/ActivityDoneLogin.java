package besidev.sigavidsbogor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import cn.refactor.library.ShapeImageView;

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

        Picasso.with(getApplicationContext()).load(String.valueOf(besidev.sigavidsbogor.PreferenceManager.getPictureURL(getApplicationContext()))).resize(500, 500).into(ivFoto);
        tvNama.setText(besidev.sigavidsbogor.PreferenceManager.getDisplayName(getApplicationContext()));
        tvEmail.setText(besidev.sigavidsbogor.PreferenceManager.getEmail(getApplicationContext()));
        ivFoto.setAnimation(animFadeIn);

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
}