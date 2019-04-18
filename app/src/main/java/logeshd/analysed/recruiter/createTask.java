package logeshd.analysed.recruiter;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.task;
import logeshd.analysed.recruiter.adapter.listCreateTask;
import logeshd.analysed.classes.drawer;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createTask extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_create_task);

        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        new create().execute();
    }

    public class create extends AsyncTask<Void,Void,Void> {

        Handler handler = new Handler();
        List<String> list2;

        AVLoadingIndicatorView pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        Spinner sp_seeker = (Spinner) findViewById(R.id.sp_seeker);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_submit = (TextView) findViewById(R.id.tv_submit);

        EditText ev_name = (EditText) findViewById(R.id.ev_name);
        EditText ev_description = (EditText) findViewById(R.id.ev_description);
        EditText ev_accuracy = (EditText) findViewById(R.id.ev_accuracy);
        EditText ev_deadline = (EditText) findViewById(R.id.ev_deadline);
        EditText ev_duration = (EditText) findViewById(R.id.ev_duration);
        EditText ev_ways = (EditText) findViewById(R.id.ev_ways);
        EditText ev_hint = (EditText) findViewById(R.id.ev_hint);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            tv_title.setTypeface(custom_font1);

            ev_name.setTypeface(custom_font3);
            ev_description.setTypeface(custom_font3);
            ev_accuracy.setTypeface(custom_font3);
            ev_deadline.setTypeface(custom_font3);
            ev_duration.setTypeface(custom_font3);
            ev_ways.setTypeface(custom_font3);
            ev_hint.setTypeface(custom_font3);

            List<String> list1 = new ArrayList<String>();
            list2 = new ArrayList<String>();
            list1.clear();
            list2.clear();
            final int count = SharedPref.getInt(getApplicationContext(),"seekers_count");

            for(int i=1;i<=count;i++){
                list1.add(SharedPref.getString(getApplicationContext(),"seekers_name"+Integer.toString(i)));
                list2.add(SharedPref.getString(getApplicationContext(),"seekers_email"+Integer.toString(i)));
            }
            ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(createTask.this, R.layout.t_spinner_item_light_black, list1) {
                public int getCount() {
                    return count;
                }
            };
            sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_seeker.setAdapter(sp_adapter);
            sp_seeker.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ev_deadline.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        final Calendar c=Calendar.getInstance();
                        DatePickerDialog d = new DatePickerDialog(createTask.this, new DatePickerDialog.OnDateSetListener() {
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
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Task name cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_description.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Description cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_accuracy.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Accuracy cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_deadline.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Please choose a deadline", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_duration.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Duration cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_ways.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Please give some ways of doing task", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else if(ev_hint.getEditableText().toString().trim().length()==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Please give some hint for doing task", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    else {
                        pcircle.setVisibility(View.VISIBLE);
                        tv_submit.setEnabled(false);
                        task t = new task();

                        t.setEmail(SharedPref.getString(getApplicationContext(), "user_name"));
                        t.setSeeker_email_list(list2);
                        t.setTask_end(ev_deadline.getEditableText().toString().trim());
                        t.setTask_name(ev_name.getEditableText().toString().trim());
                        t.setTask_desription(ev_description.getEditableText().toString().trim());
                        t.setTask_acc(ev_accuracy.getEditableText().toString().trim());
                        t.setTask_time(ev_duration.getEditableText().toString().trim());
                        t.setWay(ev_ways.getEditableText().toString().trim());
                        t.setHint(ev_hint.getEditableText().toString().trim());

                        MainRepository.getService().createTask(t).enqueue(new Callback<status>() {
                            @Override
                            public void onResponse(Call<status> call, Response<status> response) {
                                status a = response.body();
                                if (a != null) {
                                    if (a.getMessage().equals("Successfully inserted")) {
                                        Log.d("ddlogesh", a.getMessage());
                                        pcircle.setVisibility(View.GONE);
                                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Task successfully posted", R.drawable.ic_tick_white, "#ffa779c4", Color.WHITE);

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
                                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Sorry, error in posting the task!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
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

    @Override
    public void onBackPressed() {
        int count = SharedPref.getInt(getApplicationContext(),"seekers_count");
        for(int i=1;i<=count;i++){
            SharedPref.remove(getApplicationContext(),"seekers_name"+Integer.toString(i));
            SharedPref.remove(getApplicationContext(),"seekers_email"+Integer.toString(i));
        }
        super.onBackPressed();
    }
}
