package logeshd.analysed;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import spencerstudios.com.bungeelib.Bungee;

public class login extends AppCompatActivity {
    private EditText ev_name;
    private EditText ev_password;
    private TextView tab_login;
    private TextView tab_signup;
    private TextView tv_forgot;
    private TextView tv_login;
    private TextView tv_terms1;
    private TextView tv_terms2;
    private TextView tv_tour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        ev_name = (EditText) findViewById(R.id.ev_name);
        ev_password = (EditText) findViewById(R.id.ev_password);
        tv_tour = (TextView) findViewById(R.id.tv_tour);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tab_login = (TextView) findViewById(R.id.tab_login);
        tab_signup = (TextView) findViewById(R.id.tab_signup);
        tv_terms1 = (TextView) findViewById(R.id.tv_terms1);
        tv_terms2 = (TextView) findViewById(R.id.tv_terms2);
        
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

        tv_terms2.setPaintFlags(tv_terms2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_forgot.setPaintFlags(tv_forgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_tour.setPaintFlags(tv_tour.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        
        tv_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),tour.class);
                startActivity(i1);
                Bungee.inAndOut(login.this);
            }
        });
        
        tv_tour.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),takeTour.class);
                startActivity(i1);
                Bungee.slideLeft(login.this);
            }
        });
        
        tab_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),signup.class);
                startActivity(i1);
                Bungee.slideRight(login.this);
            }
        });
        
        tv_terms2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),terms.class);
                startActivity(i1);
                Bungee.slideUp(login.this);
            }
        });
    }

    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
