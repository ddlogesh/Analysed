package logeshd.analysed.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import logeshd.analysed.R;

public class splashScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.c_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                /*if(SharedPref.getBoolean(getApplicationContext(),"is_logged_in")){
                    if(SharedPref.getInt(getApplicationContext(),"user_role") == 1)
                        startActivity(new Intent(getApplicationContext(), logeshd.analysed.recruiter.dashboard.class));
                    else
                        startActivity(new Intent(getApplicationContext(), logeshd.analysed.jobSeeker.dashboard.class));
                }
                else*/
                    startActivity(new Intent(getApplicationContext(), signup1.class));
            }
        },100);
    }

    //3500
}

/*
/var/www/analysed.in/analysed/webservices/common
/var/www/analysed.in/analysed/webservices/jobseeker
/var/www/analysed.in/analysed/webservices/recruiter

/var/www/analysed.in/analysed/Pages/dashboard

cd C:\Users\Logesh Dinakaran\AppData\Local\Android\Sdk\platform-tools
adb tcpip 5555
adb shell ifconfig
adb connect 100.102.247.58:5555
 */