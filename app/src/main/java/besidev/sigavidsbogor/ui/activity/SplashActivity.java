package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import besidev.sigavidsbogor.R;

public class SplashActivity extends AppCompatActivity {
    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2000) {
                        sleep(100);
                        waited += 100;
                    }
//                    Context context = SplashActivity.this;
//                    SharedPreferences sharedPref = context.getSharedPreferences("dataLogin",context.MODE_PRIVATE);
//                    if(sharedPref.getString("nim",null) != null){
//                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent);
//                    }else{
//                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent);
//                    }

                    Intent keLoginActivity = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(keLoginActivity);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }
            }
        };
        splashTread.start();
    }
}
