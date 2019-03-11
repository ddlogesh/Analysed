package logeshd.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import spencerstudios.com.bungeelib.Bungee;

public class login extends AppCompatActivity {

    private TextView tv_tour,tv_forgot,tv_login,tv_terms1,tv_terms2,tab_login,tab_signup;
    private EditText ev_name,ev_password;
    private ImageView iv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ev_name=findViewById(R.id.ev_name);
        ev_password=findViewById(R.id.ev_password);
        tv_tour=findViewById(R.id.tv_tour);
        tv_forgot=findViewById(R.id.tv_forgot);
        tv_login=findViewById(R.id.tv_login);
        iv_login=findViewById(R.id.iv_login);
        tab_login=findViewById(R.id.tab_login);
        tab_signup=findViewById(R.id.tab_signup);
        tv_terms1=findViewById(R.id.tv_terms1);
        tv_terms2=findViewById(R.id.tv_terms2);

        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        ev_name.setTypeface(custom_font3);
        ev_password.setTypeface(custom_font3);
        tv_tour.setTypeface(custom_font4);
        tv_login.setTypeface(custom_font4);
        tab_login.setTypeface(custom_font4);
        tab_signup.setTypeface(custom_font4);
        tv_forgot.setTypeface(custom_font4);
        tv_terms1.setTypeface(custom_font3);
        tv_terms2.setTypeface(custom_font3);

        tv_terms2.setPaintFlags(tv_terms2.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv_forgot.setPaintFlags(tv_forgot.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv_tour.setPaintFlags(tv_tour.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        iv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), tour.class);
                startActivity(i1);
                Bungee.inAndOut(login.this);
            }
        });

        tv_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), tour.class);
                //startActivity(i1);
                //Bungee.slideLeft(login.this);
            }
        });

        tab_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), signup.class);
                startActivity(i1);
                Bungee.slideRight(login.this);
            }
        });
    }

    /*@Override
    protected void onResume() {
        super.onResume();

        iv_login1.setVisibility(View.INVISIBLE);
        iv_login2.setVisibility(View.INVISIBLE);
        ev_mail.setVisibility(View.INVISIBLE);
        tv_next.setVisibility(View.INVISIBLE);
        tv_copy.setVisibility(View.INVISIBLE);
        icon.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout1.setVisibility(View.VISIBLE);
                tv_copy.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.BounceIn).duration(300).playOn(layout1);
                YoYo.with(Techniques.FadeInUp).duration(300).playOn(tv_copy);
            }
        },2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_next.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.BounceIn).duration(300).playOn(tv_next);
            }
        },1700);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ev_mail.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.StandUp).duration(500).playOn(ev_mail);
            }
        },1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_login1.setVisibility(View.VISIBLE);
                iv_login2.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Tada).duration(300).playOn(iv_login1);
                YoYo.with(Techniques.Tada).duration(300).playOn(iv_login2);
            }
        },900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                icon.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.ZoomIn).duration(300).playOn(icon);
            }
        },500);
    }*/

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
