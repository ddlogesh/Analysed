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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wang.avi.AVLoadingIndicatorView;

import logeshd.analysed.R;
import logeshd.analysed.apis.jobseekers;
import logeshd.analysed.apis.recruiter;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.users;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class signup3_r extends AppCompatActivity implements View.OnClickListener {

    AVLoadingIndicatorView pcircle;
    TextView tv_terms,tv_privacy,tv_signup;
    EditText ev_organisation,ev_designation,ev_location,ev_mobile;
    ImageView iv_back,iv_organisation,iv_designation,iv_location,iv_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup3_r);

        tv_terms = findViewById(R.id.tv_terms);         tv_terms.setOnClickListener(this);
        tv_privacy = findViewById(R.id.tv_privacy);     tv_privacy.setOnClickListener(this);
        tv_signup = findViewById(R.id.tv_signup);       tv_signup.setOnClickListener(this);

        ev_organisation = findViewById(R.id.ev_organisation);
        ev_designation = findViewById(R.id.ev_designation);
        ev_location = findViewById(R.id.ev_location);
        ev_mobile = findViewById(R.id.ev_mobile);

        iv_back = findViewById(R.id.iv_back);           iv_back.setOnClickListener(this);
        pcircle = findViewById(R.id.pcircle);
    }

    /************************************************************************************/

    private class animations extends AsyncTask<Void,Void,Void> {
        Handler handler=new Handler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            iv_organisation = findViewById(R.id.iv_organisation);
            iv_designation = findViewById(R.id.iv_designation);
            iv_location = findViewById(R.id.iv_location);
            iv_mobile = findViewById(R.id.iv_mobile);

            tv_signup.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_signup.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInUp).duration(500).playOn(tv_signup);
                }
            },100);

            return null;
        }
    }

    private void checkForSignupApi(users u){
        MainRepository.getService().checkForSignupApi(u).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    Log.d("ddlogesh", a.getMessage());
                    if(a.getMessage().equals("Successfully inserted")){
                        recruiter r = new recruiter();
                        r.setFname(SharedPref.getString(getApplicationContext(),"signup","f_name"));
                        r.setLname(SharedPref.getString(getApplicationContext(),"signup","l_name"));
                        r.setEmail(SharedPref.getString(getApplicationContext(),"signup","ev_email"));
                        r.setPhone(ev_mobile.getEditableText().toString().trim());
                        r.setOrganisation(ev_organisation.getEditableText().toString().trim());
                        r.setDesignation(ev_designation.getEditableText().toString().trim());
                        r.setAddress(ev_location.getEditableText().toString().trim());
                        r.setPicture(SharedPref.getString(getApplicationContext(), "profile_file_name"));

                        signupApi(r);
                    }
                    else if(a.getMessage().equals("User already exists")) {
                        Log.d("ddlogesh", a.getMessage());
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0102: Email id already exists!", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                        pcircle.setVisibility(View.GONE);
                        tv_signup.setEnabled(true);
                    }
                    else{
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0103: Server error, please try again later!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                        pcircle.setVisibility(View.GONE);
                        tv_signup.setEnabled(true);
                    }
                }
                else {
                    pcircle.setVisibility(View.GONE);
                    tv_signup.setEnabled(true);
                    Log.d("ddlogesh", "Failed");
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0104: Server error, please try again later!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ff0000",Color.WHITE);
            }
        });
    }

    private void signupApi(final recruiter r){
        MainRepository.getService().signupRCApi(r).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    if(a.getMessage().equals("Successfully inserted")){
                        SharedPref.putString(getApplicationContext(),"user_name",r.getEmail());
                        SharedPref.putInt(getApplicationContext(),"user_role",SharedPref.getInt(getApplicationContext(),"signup","user_role"));
                        SharedPref.putInt(getApplicationContext(),"id",r.getId());
                        SharedPref.putString(getApplicationContext(),"f_name",r.getFname());
                        SharedPref.putString(getApplicationContext(),"l_name",r.getLname());
                        SharedPref.putString(getApplicationContext(),"phone",r.getPhone());
                        SharedPref.putString(getApplicationContext(), "location", r.getAddress());
                        SharedPref.putString(getApplicationContext(), "referal", r.getReferal());

                        SharedPref.remove(getApplicationContext(),"profile_file_name");
                        SharedPref.removeAll(getApplicationContext(),"signup");
                        SharedPref.removeAll(getApplicationContext(),"login");

                        Log.d("ddlogesh", a.getMessage());

                        SharedPref.putBoolean(getApplicationContext(),"is_logged_in",true);
                        pcircle.setVisibility(View.GONE);

                        startActivity(new Intent(getApplicationContext(), tour.class));
                        Bungee.shrink(signup3_r.this);
                    }
                    else {
                        Log.d("ddlogesh", a.getMessage());
                        tv_signup.setEnabled(true);
                        pcircle.setVisibility(View.GONE);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0201: Sorry, error in signing-up!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                    }
                }
                else {
                    Log.d("ddlogesh", "Failed");
                    tv_signup.setEnabled(true);
                    pcircle.setVisibility(View.GONE);
                    CommonUtils.setSnackBar(getWindow().getDecorView(),"\tERR-0202: Server error, please try again later!",R.drawable.ic_alert_white,"#ff0000",Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"\tERR-0203: Server error, please try again later!",R.drawable.ic_alert_white,"#ff0000",Color.WHITE);
            }
        });
    }

    /************************************************************************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_terms:
                SharedPref.putInt(getApplicationContext(),"back_flag",2);
                startActivity(new Intent(getApplicationContext(),terms.class));
                Bungee.slideUp(signup3_r.this);
                break;
            case R.id.tv_privacy:
                SharedPref.putInt(getApplicationContext(),"back_flag",2);
                startActivity(new Intent(getApplicationContext(),privacy.class));
                Bungee.slideUp(signup3_r.this);
                break;
            case R.id.tv_signup:
                if (ev_organisation.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tCompany name cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_organisation);
                }
                else if (ev_designation.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tDesignation cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_designation);
                }
                else if (ev_location.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tCompany location cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_location);
                }
                else if (ev_mobile.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tMobile number cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_mobile);
                }
                else{
                    if(startProgress()){
                        users u=new users(SharedPref.getString(getApplicationContext(),"signup","ev_email"),SharedPref.getString(getApplicationContext(),"signup","ev_password"),Integer.toString(SharedPref.getInt(getApplicationContext(),"signup","user_role")),1);
                        checkForSignupApi(u);
                    }
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ev_organisation.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_organisation",ev_organisation.getEditableText().toString().trim());
        if (ev_designation.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_designation",ev_designation.getEditableText().toString().trim());
        if (ev_location.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_location",ev_location.getEditableText().toString().trim());
        if (ev_mobile.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_mobile",ev_mobile.getEditableText().toString().trim());
    }

    @Override
    protected void onResume() {
        super.onResume();

        new animations().execute();

        if (SharedPref.getString(getApplicationContext(),"signup","ev_organisation") != null)
            ev_organisation.setText(SharedPref.getString(getApplicationContext(),"signup","ev_organisation"));
        if (SharedPref.getString(getApplicationContext(),"signup","ev_designation") != null)
            ev_designation.setText(SharedPref.getString(getApplicationContext(),"signup","ev_designation"));
        if (SharedPref.getString(getApplicationContext(),"signup","ev_location") != null)
            ev_location.setText(SharedPref.getString(getApplicationContext(),"signup","ev_location"));
        if (SharedPref.getString(getApplicationContext(),"signup","ev_mobile") != null)
            ev_mobile.setText(SharedPref.getString(getApplicationContext(),"signup","ev_mobile"));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), signup2.class));
        Bungee.slideRight(signup3_r.this);
    }

    /************************************************************************************/

    private Boolean startProgress(){
        CommonUtils.hideKeyboard(signup3_r.this);
        if(CommonUtils.alerter(getApplicationContext())) {
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease check your internet connection", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
            return false;
        }
        else {
            pcircle.setVisibility(View.VISIBLE);
            tv_signup.setEnabled(false);
            return true;
        }
    }
}
