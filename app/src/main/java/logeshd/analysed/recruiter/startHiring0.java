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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.joblistings;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.task;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class startHiring0 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.r_start_hiring0);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        new create().execute();
    }

    public class create extends AsyncTask<Void,Void,Void> {

        Handler handler = new Handler();
        AVLoadingIndicatorView pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        EditText ev_recruiter_name1 = (EditText) findViewById(R.id.ev_recruiter_name1);
        EditText ev_organization_name1 = (EditText) findViewById(R.id.ev_organization_name1);
        EditText ev_designation = (EditText) findViewById(R.id.ev_designation);
        EditText ev_position = (EditText) findViewById(R.id.ev_position);
        EditText ev_skill = (EditText) findViewById(R.id.ev_skill);
        EditText ev_qualification = (EditText) findViewById(R.id.ev_qualification);
        EditText ev_experience = (EditText) findViewById(R.id.ev_experience);
        EditText ev_job_location = (EditText) findViewById(R.id.ev_job_location);
        EditText ev_package = (EditText) findViewById(R.id.ev_package);
        EditText ev_timings = (EditText) findViewById(R.id.ev_timings);
        EditText ev_description = (EditText) findViewById(R.id.ev_description);
        ImageView iv_post = (ImageView) findViewById(R.id.iv_post);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            tv_title.setTypeface(custom_font2);

            ev_recruiter_name1.setTypeface(custom_font1);
            ev_organization_name1.setTypeface(custom_font1);
            ev_designation.setTypeface(custom_font1);
            ev_position.setTypeface(custom_font1);
            ev_skill.setTypeface(custom_font1);
            ev_qualification.setTypeface(custom_font1);
            ev_experience.setTypeface(custom_font1);
            ev_job_location.setTypeface(custom_font1);
            ev_package.setTypeface(custom_font1);
            ev_timings.setTypeface(custom_font1);
            ev_description.setTypeface(custom_font1);

            ev_recruiter_name1.setText(SharedPref.getString(getApplicationContext(),"f_name")+" "+SharedPref.getString(getApplicationContext(),"l_name"));
            ev_organization_name1.setText(SharedPref.getString(getApplicationContext(),"organisation"));
            ev_designation.setText(SharedPref.getString(getApplicationContext(),"designation"));
        }

        @Override
        protected Void doInBackground(Void... voids) {

            iv_post.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ev_position.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Position cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_skill.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Skill cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_qualification.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Qualification cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_experience.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Experience cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_job_location.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Job location cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_package.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Package cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_timings.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Job timings cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_description.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Job description cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else {
                        pcircle.setVisibility(View.VISIBLE);
                        iv_post.setEnabled(false);

                        joblistings j = new joblistings();
                        j.setPosition(ev_position.getEditableText().toString().trim());
                        j.setSkills_req(ev_skill.getEditableText().toString().trim());
                        j.setQual_req(ev_qualification.getEditableText().toString().trim());
                        j.setExp_req(ev_experience.getEditableText().toString().trim());
                        j.setJob_location(ev_job_location.getEditableText().toString().trim());
                        j.setJob_description(ev_description.getEditableText().toString().trim());
                        j.setPackages(ev_package.getEditableText().toString().trim());
                        j.setJob_time(ev_timings.getEditableText().toString().trim());
                        j.setPostedby(SharedPref.getString(getApplicationContext(),"user_name"));

                        MainRepository.getService().createJob(j).enqueue(new Callback<status>() {
                            @Override
                            public void onResponse(Call<status> call, Response<status> response) {
                                status a = response.body();
                                if (a != null) {
                                    if (a.getMessage().equals("Successfully inserted")) {
                                        Log.d("ddlogesh", a.getMessage());
                                        pcircle.setVisibility(View.GONE);
                                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Job successfully posted", R.drawable.ic_tick_white, "#ffa779c4", Color.WHITE);

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                onBackPressed();
                                            }
                                        },1000);
                                    }
                                    else {
                                        Log.d("ddlogesh", Integer.toString(a.getCode()));
                                        iv_post.setEnabled(true);
                                        pcircle.setVisibility(View.GONE);
                                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Sorry, error in posting the job!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                                    }
                                }
                                else {
                                    Log.d("ddlogesh", "Failed");
                                    iv_post.setEnabled(true);
                                    pcircle.setVisibility(View.GONE);
                                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                                }
                            }

                            @Override
                            public void onFailure(Call<status> call, Throwable t) {
                                Log.d("ddlogesh", "Nope " + t.getMessage());
                                pcircle.setVisibility(View.GONE);
                                iv_post.setEnabled(true);
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
