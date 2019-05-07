package logeshd.analysed.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import logeshd.analysed.R;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import spencerstudios.com.bungeelib.Bungee;

public class signup1 extends AppCompatActivity implements View.OnClickListener {

    TextView tv_signin,tv_tab1,tv_tab2,tv_next;
    RelativeLayout layout_tab1,layout_tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup1);

        tv_signin = findViewById(R.id.tv_signin);       tv_signin.setOnClickListener(this);
        tv_next = findViewById(R.id.tv_next);           tv_next.setOnClickListener(this);
        tv_tab1 = findViewById(R.id.tv_tab1);
        tv_tab2 = findViewById(R.id.tv_tab2);

        layout_tab1 = findViewById(R.id.layout_tab1);   layout_tab1.setOnClickListener(this);
        layout_tab2 = findViewById(R.id.layout_tab2);   layout_tab2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_signin:
                onBackPressed();
                break;
            case R.id.tv_next:
                SharedPref.remove(getApplicationContext(),"profile_file_name");
                startActivity(new Intent(getApplicationContext(), signup2.class));
                Bungee.slideLeft(signup1.this);
                break;
            case R.id.layout_tab1:
                tv_tab1.setTextColor(Color.WHITE);
                tv_tab2.setTextColor(Color.BLACK);

                layout_tab1.setBackgroundResource(R.drawable.signup_tab_left_selected);
                layout_tab2.setBackgroundResource(R.drawable.signup_tab_right_unselected);
                SharedPref.putInt(getApplicationContext(),"signup","user_role",1);
                if(tv_next.getVisibility()==View.GONE) {
                    tv_next.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInUp).duration(500).playOn(tv_next);
                }
                break;
            case R.id.layout_tab2:
                tv_tab1.setTextColor(Color.BLACK);
                tv_tab2.setTextColor(Color.WHITE);

                layout_tab1.setBackgroundResource(R.drawable.signup_tab_left_unselected);
                layout_tab2.setBackgroundResource(R.drawable.signup_tab_right_selected);
                SharedPref.putInt(getApplicationContext(),"signup","user_role",0);
                if(tv_next.getVisibility()==View.GONE) {
                    tv_next.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInUp).duration(500).playOn(tv_next);
                }
                break;
        }
    }

    public void onBackPressed() {
        SharedPref.removeAll(getApplicationContext(),"signup");
        SharedPref.removeAll(getApplicationContext(),"login");

        startActivity(new Intent(getApplicationContext(), login.class));
        Bungee.slideDown(signup1.this);
    }
}
