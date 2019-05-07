package logeshd.analysed.common;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import spencerstudios.com.bungeelib.Bungee;

public class signup3_j extends AppCompatActivity implements View.OnClickListener{

    EditText ev_mobile,ev_skills,ev_location;
    TextView tv_next;
    Spinner sp_qualification,sp_year_passing,sp_experience;
    ImageView iv_back,iv_mobile,iv_skills,iv_location,iv_qualification,iv_year_passing,iv_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup3_j);

        ev_mobile = findViewById(R.id.ev_mobile);
        ev_skills = findViewById(R.id.ev_skills);
        ev_location = findViewById(R.id.ev_location);

        tv_next = findViewById(R.id.tv_next);   tv_next.setOnClickListener(this);
        iv_back = findViewById(R.id.iv_back);   iv_back.setOnClickListener(this);

        new signUpUpdates().execute();
    }

    /************************************************************************************/

    private class animations extends AsyncTask<Void,Void,Void> {
        Handler handler=new Handler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            iv_mobile = findViewById(R.id.iv_mobile);
            iv_skills = findViewById(R.id.iv_skills);
            iv_location = findViewById(R.id.iv_location);
            iv_qualification = findViewById(R.id.iv_qualification);
            iv_year_passing = findViewById(R.id.iv_year_passing);
            iv_experience = findViewById(R.id.iv_experience);

            tv_next.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv_next.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInUp).duration(500).playOn(tv_next);
                }
            },100);

            return null;
        }
    }

    public class signUpUpdates extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            sp_qualification = (Spinner) findViewById(R.id.sp_qualification);
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("Under graduate");
            list1.add("Post graduate");
            list1.add("Ph.D");
            list1.add("Qualification");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup3_j.this, R.layout.t_spinner_item_light_black, list1) {
                public int getCount() {
                    return 3;
                }
            };
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_qualification.setAdapter(adapter);

            sp_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 3) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_year_passing = (Spinner) findViewById(R.id.sp_year_passing);
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
            ArrayAdapter adapter2 = new ArrayAdapter<String>(signup3_j.this, R.layout.t_spinner_item_light_black, list2) {
                public int getCount() {
                    return 25;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_year_passing.setAdapter(adapter2);

            sp_year_passing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 25) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_experience = (Spinner) findViewById(R.id.sp_experience);
            ArrayList<String> list3 = new ArrayList<>();
            list3.add("0 Year");
            list3.add("0-2 Years");
            list3.add("2-4 Years");
            list3.add("4+ Years");
            list3.add("Experience");
            adapter2 = new ArrayAdapter<String>(signup3_j.this, R.layout.t_spinner_item_light_black, list3) {
                public int getCount() {
                    return 4;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_experience.setAdapter(adapter2);

            sp_experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 4) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            return null;
        }
    }

    /************************************************************************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_next:
                if ((sp_qualification.getSelectedItem().toString()).equals("Qualification")) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tSelect qualification", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_qualification);
                }
                else if ((sp_year_passing.getSelectedItem().toString()).equals("Year Of Passing")) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tSelect year of passing", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_year_passing);
                }
                else if ((sp_experience.getSelectedItem().toString()).equals("Experience")) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tSelect experience", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_experience);
                }
                else if (ev_skills.getEditableText().toString().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tSkills cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_skills);
                }
                else if (ev_location.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tLocation cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_location);
                }
                else if (ev_mobile.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tMobile number cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_mobile);
                }
                else {
                    startActivity(new Intent(getApplicationContext(), signup4_j.class));
                    Bungee.slideLeft(signup3_j.this);
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ev_mobile.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_mobile",ev_mobile.getEditableText().toString().trim());
        if (ev_skills.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_skills",ev_skills.getEditableText().toString().trim());
        if (ev_location.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_location",ev_location.getEditableText().toString().trim());

        if (!(sp_qualification.getSelectedItem().toString()).equals("Qualification")) {
            SharedPref.putInt(getApplicationContext(), "signup", "sp_qualification_pos", sp_qualification.getSelectedItemPosition()+1);
            SharedPref.putString(getApplicationContext(), "signup", "sp_qualification", sp_qualification.getSelectedItem().toString().trim());
        }

        if (!(sp_year_passing.getSelectedItem().toString()).equals("Year Of Passing")){
            SharedPref.putInt(getApplicationContext(), "signup", "sp_year_passing_pos", sp_year_passing.getSelectedItemPosition()+1);
            SharedPref.putString(getApplicationContext(), "signup", "sp_year_passing", sp_year_passing.getSelectedItem().toString().trim());
        }

        if (!(sp_experience.getSelectedItem().toString()).equals("Experience")){
            SharedPref.putInt(getApplicationContext(), "signup", "sp_experience_pos", sp_experience.getSelectedItemPosition()+1);
            SharedPref.putString(getApplicationContext(), "signup", "sp_experience", sp_experience.getSelectedItem().toString().trim());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new animations().execute();

        if (SharedPref.getString(getApplicationContext(),"signup","ev_mobile") != null)
            ev_mobile.setText(SharedPref.getString(getApplicationContext(),"signup","ev_mobile"));
        if (SharedPref.getString(getApplicationContext(),"signup","ev_skills") != null)
            ev_skills.setText(SharedPref.getString(getApplicationContext(),"signup","ev_skills"));
        if (SharedPref.getString(getApplicationContext(),"signup","ev_location") != null)
            ev_location.setText(SharedPref.getString(getApplicationContext(),"signup","ev_location"));

        if (SharedPref.getInt(getApplicationContext(),"signup","sp_qualification_pos") != 0)
            sp_qualification.setSelection(SharedPref.getInt(getApplicationContext(),"signup","sp_qualification_pos")-1);
        else
            sp_qualification.setSelection(3);

        if (SharedPref.getInt(getApplicationContext(),"signup","sp_year_passing_pos") != 0)
            sp_year_passing.setSelection(SharedPref.getInt(getApplicationContext(),"signup","sp_year_passing_pos")-1);
        else
            sp_year_passing.setSelection(25);

        if (SharedPref.getInt(getApplicationContext(),"signup","sp_experience_pos") != 0)
            sp_experience.setSelection(SharedPref.getInt(getApplicationContext(),"signup","sp_experience_pos")-1);
        else
            sp_experience.setSelection(4);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), signup2.class));
        Bungee.slideRight(signup3_j.this);
    }
}
