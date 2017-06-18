package ir.rezabidar.onlineorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.novoda.merlin.Merlin;
import com.novoda.merlin.MerlinsBeard;

public class SplashScreen extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MerlinsBeard merlinsBeard = MerlinsBeard.from(this);

        if(merlinsBeard.isConnected())
        {
            Snackbar.make(findViewById(R.id.imageButton), "Connected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                //Intent mainIntent = new Intent(SplashScreen.this, TestActivity.class);
//                Intent mainIntent = new Intent(SplashScreen.this, ProductListTab.class);
                Intent mainIntent = new Intent(SplashScreen.this, CompanyListActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

