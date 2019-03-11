package logeshd.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

public class signup extends AppCompatActivity {

    TextView tv_tour,tv_signup,tv_terms1,tv_terms2,tab_login,tab_signup;
    EditText ev_first_name1,ev_last_name1,ev_email,ev_location,ev_mobile,ev_password1,ev_password2;
    ImageView iv_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        tv_tour=findViewById(R.id.tv_tour);
        tv_signup=findViewById(R.id.tv_signup);
        tv_terms1=findViewById(R.id.tv_terms1);
        tv_terms2=findViewById(R.id.tv_terms2);
        tab_login=findViewById(R.id.tab_login);
        tab_signup=findViewById(R.id.tab_signup);

        ev_first_name1=findViewById(R.id.ev_first_name1);
        ev_last_name1=findViewById(R.id.ev_last_name1);
        ev_email=findViewById(R.id.ev_email);
        ev_location=findViewById(R.id.ev_location);
        ev_mobile=findViewById(R.id.ev_mobile);
        ev_password1=findViewById(R.id.ev_password1);
        ev_password2=findViewById(R.id.ev_password2);
        iv_signup=findViewById(R.id.iv_signup);

        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        tv_tour.setTypeface(custom_font4);
        tv_signup.setTypeface(custom_font4);
        tab_login.setTypeface(custom_font4);
        tab_signup.setTypeface(custom_font4);
        tv_terms1.setTypeface(custom_font3);
        tv_terms2.setTypeface(custom_font3);
        tv_terms2.setTypeface(custom_font3);
        ev_first_name1.setTypeface(custom_font3);
        ev_last_name1.setTypeface(custom_font3);
        ev_email.setTypeface(custom_font3);
        ev_location.setTypeface(custom_font3);
        ev_mobile.setTypeface(custom_font3);
        ev_password1.setTypeface(custom_font3);
        ev_password2.setTypeface(custom_font3);

        tv_terms2.setPaintFlags(tv_terms2.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv_tour.setPaintFlags(tv_tour.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        iv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), tour.class);
                startActivity(i1);
                Bungee.inAndOut(signup.this);
            }
        });

        tv_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), tour.class);
                //startActivity(i1);
                //Bungee.slideRight(signup.this);
            }
        });

        tab_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), login.class);
                startActivity(i1);
                Bungee.slideLeft(signup.this);
            }
        });

        /***********************************************************************/

        Spinner sp_qualification = (Spinner) findViewById(R.id.sp_qualification);
        List<String> list1 = new ArrayList<String>();
        list1.add("Under graduate");
        list1.add("Post graduate");
        list1.add("Ph.D");
        list1.add("Qualification");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item, list1) {
            @Override
            public int getCount() {
                return(3);
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_qualification.setAdapter(adapter);
        sp_qualification.setSelection(3);

        sp_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 3)
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /***********************************************************************/

        Spinner sp_year_passing = (Spinner) findViewById(R.id.sp_year_passing);
        List<String> list2 = new ArrayList<String>();
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
        adapter = new ArrayAdapter<String>(this,R.layout.spinner_item, list2) {
            @Override
            public int getCount() {
                return(25);
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_year_passing.setAdapter(adapter);
        sp_year_passing.setSelection(25);

        sp_year_passing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 25)
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /***********************************************************************/

        Spinner sp_experience = (Spinner) findViewById(R.id.sp_experience);
        List<String> list3 = new ArrayList<String>();
        list3.add("0 Year");
        list3.add("0-2 Years");
        list3.add("2-4 Years");
        list3.add("4+ Years");
        list3.add("Experience");
        adapter = new ArrayAdapter<String>(this,R.layout.spinner_item, list3) {
            @Override
            public int getCount() {
                return(4);
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_experience.setAdapter(adapter);
        sp_experience.setSelection(4);

        sp_experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 4)
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}