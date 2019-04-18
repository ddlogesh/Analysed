package logeshd.analysed.recruiter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;
import logeshd.analysed.apis.status;
import logeshd.analysed.common.signup;
import logeshd.analysed.common.tour;
import logeshd.analysed.recruiter.adapter.listCreateTask;
import logeshd.analysed.classes.drawer;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class createChallenge extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_create_challenge);

        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        new create().execute();
    }

    public class create extends AsyncTask<Void,Void,Void>{

        Handler handler = new Handler();
        AVLoadingIndicatorView pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_reward = (TextView) findViewById(R.id.tv_reward);
        TextView tv_submit = (TextView) findViewById(R.id.tv_submit);

        EditText ev_name = (EditText) findViewById(R.id.ev_name);
        EditText ev_description = (EditText) findViewById(R.id.ev_description);
        EditText ev_deadline = (EditText) findViewById(R.id.ev_deadline);
        EditText ev_duration = (EditText) findViewById(R.id.ev_duration);
        EditText ev_winner = (EditText) findViewById(R.id.ev_winner);
        EditText ev_ways = (EditText) findViewById(R.id.ev_ways);
        EditText ev_hint = (EditText) findViewById(R.id.ev_hint);

        CheckBox ck_cash = (CheckBox) findViewById(R.id.ck_cash);
        CheckBox ck_coupon = (CheckBox) findViewById(R.id.ck_coupon);
        CheckBox ck_service = (CheckBox) findViewById(R.id.ck_service);
        CheckBox ck_hire = (CheckBox) findViewById(R.id.ck_hire);
        CheckBox ck_other = (CheckBox) findViewById(R.id.ck_other);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            tv_title.setTypeface(custom_font1);
            tv_reward.setTypeface(custom_font2);
            ev_name.setTypeface(custom_font3);
            ev_description.setTypeface(custom_font3);
            ev_deadline.setTypeface(custom_font3);
            ev_duration.setTypeface(custom_font3);
            ev_winner.setTypeface(custom_font3);
            ev_hint.setTypeface(custom_font3);
            ev_ways.setTypeface(custom_font3);
            ck_cash.setTypeface(custom_font3);
            ck_coupon.setTypeface(custom_font3);
            ck_service.setTypeface(custom_font3);
            ck_hire.setTypeface(custom_font3);
            ck_other.setTypeface(custom_font3);

            ev_deadline.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        final Calendar c=Calendar.getInstance();
                        DatePickerDialog d = new DatePickerDialog(createChallenge.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                c.set(Calendar.YEAR,year);
                                c.set(Calendar.MONTH,month);
                                c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                ev_deadline.setText(sdf.format(c.getTime()));
                            }
                        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

                        d.getDatePicker().setMinDate(c.getTimeInMillis());
                        d.show();
                    }
                }
            });
        }

        @Override
        protected Void doInBackground(Void... voids) {

            tv_submit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ev_name.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Challenge name cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_description.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Description cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_deadline.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Please choose a deadline", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_duration.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Duration cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_winner.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "No.of winner cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_ways.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Please give some ways of doing challenge", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_hint.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Please give some hint for doing challenge", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else {
                        pcircle.setVisibility(View.VISIBLE);
                        tv_submit.setEnabled(false);
                        challenge c = new challenge();

                        c.setEmail(SharedPref.getString(getApplicationContext(), "user_name"));
                        c.setChallenge_end(ev_deadline.getEditableText().toString().trim());
                        c.setChallenge_name(ev_name.getEditableText().toString().trim());
                        c.setChallenge_description(ev_description.getEditableText().toString().trim());
                        c.setWinners(ev_winner.getEditableText().toString().trim());
                        c.setChallenge_time(ev_duration.getEditableText().toString().trim());
                        c.setWay(ev_ways.getEditableText().toString().trim());
                        c.setHint(ev_hint.getEditableText().toString().trim());

                        if(ck_cash.isChecked())
                            c.setCash("1");
                        else
                            c.setCash("");

                        if(ck_coupon.isChecked())
                            c.setCoupan("1");
                        else
                            c.setCoupan("");

                        if(ck_service.isChecked())
                            c.setService("1");
                        else
                            c.setService("");

                        if(ck_hire.isChecked())
                            c.setJob("Hiring/Job Offer");
                        else
                            c.setJob("");

                        if(ck_other.isChecked())
                            c.setOther("1");
                        else
                            c.setOther("");

                        MainRepository.getService().createChallenge(c).enqueue(new Callback<status>() {
                            @Override
                            public void onResponse(Call<status> call, Response<status> response) {
                                status a = response.body();
                                if (a != null) {
                                    if (a.getMessage().equals("Successfully inserted")) {
                                        Log.d("ddlogesh", a.getMessage());
                                        pcircle.setVisibility(View.GONE);
                                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Challenge successfully posted", R.drawable.ic_tick_white, "#ffa779c4", Color.WHITE);

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                onBackPressed();
                                            }
                                        },1000);
                                    }
                                    else {
                                        Log.d("ddlogesh", Integer.toString(a.getCode()));
                                        tv_submit.setEnabled(true);
                                        pcircle.setVisibility(View.GONE);
                                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Sorry, error in posting the challenge!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                                    }
                                }
                                else {
                                    Log.d("ddlogesh", "Failed");
                                    tv_submit.setEnabled(true);
                                    pcircle.setVisibility(View.GONE);
                                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                                }
                            }

                            @Override
                            public void onFailure(Call<status> call, Throwable t) {
                                Log.d("ddlogesh", "Nope " + t.getMessage());
                                pcircle.setVisibility(View.GONE);
                                tv_submit.setEnabled(true);
                                CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                            }
                        });
                    }
                }
            });

            return null;
        }
    }
}
