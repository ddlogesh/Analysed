package logeshd.analysed.common;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.jobSeeker.dashboard;
import spencerstudios.com.bungeelib.Bungee;

public class signup extends AppCompatActivity {
    EditText ev_email,ev_first_name1,ev_last_name1,ev_location,ev_mobile,ev_password1,ev_password2;
    ImageView iv_job_seekers;
    TextView tab_login,tab_signup,tv_role,tv_signup,tv_terms1,tv_terms2,tv_tour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.c_signup);
        
        tv_tour = (TextView) findViewById(R.id.tv_tour);
        tv_signup = (TextView) findViewById(R.id.tv_signup);
        tv_terms1 = (TextView) findViewById(R.id.tv_terms1);
        tv_terms2 = (TextView) findViewById(R.id.tv_terms2);
        tab_login = (TextView) findViewById(R.id.tab_login);
        tab_signup = (TextView) findViewById(R.id.tab_signup);
        tv_role = (TextView) findViewById(R.id.tv_role);
        
        ev_first_name1 = (EditText) findViewById(R.id.ev_first_name1);
        ev_last_name1 = (EditText) findViewById(R.id.ev_last_name1);
        ev_email = (EditText) findViewById(R.id.ev_email);
        ev_location = (EditText) findViewById(R.id.ev_location);
        ev_mobile = (EditText) findViewById(R.id.ev_mobile);
        ev_password1 = (EditText) findViewById(R.id.ev_password1);
        ev_password2 = (EditText) findViewById(R.id.ev_password2);
        
        iv_job_seekers = (ImageView) findViewById(R.id.iv_job_seekers);
        
        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        
        tv_tour.setTypeface(custom_font2);
        tv_signup.setTypeface(custom_font2);
        tab_login.setTypeface(custom_font2);
        tab_signup.setTypeface(custom_font2);
        tv_terms1.setTypeface(custom_font1);
        tv_terms2.setTypeface(custom_font1);
        tv_terms2.setTypeface(custom_font1);
        tv_role.setTypeface(custom_font2);
        
        ev_first_name1.setTypeface(custom_font1);
        ev_last_name1.setTypeface(custom_font1);
        ev_email.setTypeface(custom_font1);
        ev_location.setTypeface(custom_font1);
        ev_mobile.setTypeface(custom_font1);
        ev_password1.setTypeface(custom_font1);
        ev_password2.setTypeface(custom_font1);

        tv_terms2.setPaintFlags(tv_terms2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_tour.setPaintFlags(tv_tour.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tv_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                Bungee.inAndOut(signup.this);
                //c_tour.class
            }
        });

        tv_tour.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), takeTour.class));
                Bungee.slideRight(signup.this);
            }
        });

        tab_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
                Bungee.slideLeft(signup.this);
            }
        });

        tv_terms2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), terms.class));
                Bungee.slideUp(signup.this);
            }
        });

        iv_job_seekers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RelativeLayout) findViewById(R.id.layout_role)).setVisibility(View.GONE);
                ((ScrollView) findViewById(R.id.layout_scroll)).setVisibility(View.VISIBLE);
            }
        });

        Spinner sp_qualification = (Spinner) findViewById(R.id.sp_qualification);
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Under graduate");
        list1.add("Post graduate");
        list1.add("Ph.D");
        list1.add("Qualification");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.t_spinner_item, list1) {
            public int getCount() {
                return 3;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_qualification.setAdapter(adapter);
        sp_qualification.setSelection(3);
        sp_qualification.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 3) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner sp_year_passing = (Spinner) findViewById(R.id.sp_year_passing);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("1997");
        list2.add("1998");
        list2.add("1999");
        list2.add("2000");
        list2.add("2001");
        list2.add("2002");
        list2.add("2003");
        list2.add("2004");
        list2.add("2005");
        list2.add("2006");
        list2.add("2007");
        list2.add("2008");
        list2.add("2009");
        list2.add("2010");
        list2.add("2011");
        list2.add("2012");
        list2.add("2013");
        list2.add("2014");
        list2.add("2015");
        list2.add("2016");
        list2.add("2017");
        list2.add("2018");
        list2.add("2019");
        list2.add("2020");
        list2.add("2021");
        list2.add("Year Of Passing");
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.t_spinner_item, list2) {
            public int getCount() {
                return 25;
            }
        };
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_year_passing.setAdapter(adapter2);
        sp_year_passing.setSelection(25);
        sp_year_passing.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 25) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner sp_experience = (Spinner) findViewById(R.id.sp_experience);
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("0 Year");
        list3.add("0-2 Years");
        list3.add("2-4 Years");
        list3.add("4+ Years");
        list3.add("Experience");
        adapter2 = new ArrayAdapter<String>(this, R.layout.t_spinner_item, list3) {
            public int getCount() {
                return 4;
            }
        };
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_experience.setAdapter(adapter2);
        sp_experience.setSelection(4);
        sp_experience.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 4) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
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
