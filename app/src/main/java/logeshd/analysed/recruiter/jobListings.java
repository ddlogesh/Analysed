package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import logeshd.analysed.R;
import logeshd.analysed.apis.databases;
import logeshd.analysed.apis.joblistings;
import logeshd.analysed.jobSeeker.adapter.listViewJobs;
import logeshd.analysed.recruiter.adapter.listDatabase;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class jobListings extends AppCompatActivity implements View.OnClickListener {

    AVLoadingIndicatorView pcircle;
    TextView tv_no_data,tv_ongoing,tv_completed,tv_title,tv_invite;
    ImageView iv_home;
    ListView l1;
    static int flag=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.r_job_listings);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        l1 = (ListView) findViewById(R.id.job_list_timeline);
        
        tv_no_data=findViewById(R.id.tv_no_data);
        tv_ongoing=findViewById(R.id.tv_ongoing);       tv_ongoing.setOnClickListener(this);
        tv_completed=findViewById(R.id.tv_completed);   tv_completed.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_invite = (TextView) findViewById(R.id.tv_invite);
        iv_home = (ImageView) findViewById(R.id.iv_home);   iv_home.setOnClickListener(this);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_invite.setTypeface(custom_font1);
        tv_title.setTypeface(custom_font2);

        getList();
    }

    private void getList(){
        tv_no_data.setVisibility(View.GONE);
        l1.setVisibility(View.GONE);
        pcircle.setVisibility(View.VISIBLE);
        tv_ongoing.setEnabled(false);
        tv_completed.setEnabled(false);

        MainRepository.getService().getJobListings(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<List<joblistings>>() {
            @Override
            public void onResponse(Call<List<joblistings>> call, Response<List<joblistings>> response) {
                listViewJobs adapter = new listViewJobs(jobListings.this, new ArrayList<joblistings>());
                adapter.clear();

                List<joblistings> dlist=response.body();
                if(dlist!=null) {
                    for (joblistings d : dlist) {
                        if(flag==1 && d.getEndon()==null)
                            adapter.add(new joblistings(d.getId(), d.getPosition(), d.getSkills_req(), d.getQual_req(), d.getExp_req(), d.getJob_location(), d.getJob_description(), d.getPackages(), d.getJob_time(), d.getPostedby(), d.getCreatedon(), d.getEndon()));
                        else if(flag==2 && d.getEndon() != null)
                            adapter.add(new joblistings(d.getId(), d.getPosition(), d.getSkills_req(), d.getQual_req(), d.getExp_req(), d.getJob_location(), d.getJob_description(), d.getPackages(), d.getJob_time(), d.getPostedby(), d.getCreatedon(), d.getEndon()));
                    }
                    if(adapter.isEmpty()) {
                        l1.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                    }
                    else {
                        l1.setAdapter(adapter);

                        l1.setVisibility(View.VISIBLE);
                        tv_no_data.setVisibility(View.GONE);
                    }
                }
                else {
                    l1.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                }

                pcircle.setVisibility(View.GONE);
                tv_ongoing.setEnabled(true);
                tv_completed.setEnabled(true);
            }

            @Override
            public void onFailure(Call<List<joblistings>> call, Throwable t) {
                Log.d("ddlogesh",t.getMessage());
                l1.setVisibility(View.GONE);
                tv_no_data.setVisibility(View.VISIBLE);
                pcircle.setVisibility(View.GONE);
                tv_ongoing.setEnabled(true);
                tv_completed.setEnabled(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (SharedPref.getBoolean(getApplicationContext(), "is_task"))
            tv_title.setText("Choose a job");
        else
            tv_title.setText("Jobs Posted");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ongoing:
                if(flag==2) {
                    tv_ongoing.setTextColor(Color.parseColor("#ffffff"));
                    tv_completed.setTextColor(Color.parseColor("#74fdf4f4"));
                    flag = 1;
                    getList();
                }
                break;
            case R.id.tv_completed:
                if(flag==1) {
                    tv_ongoing.setTextColor(Color.parseColor("#74fdf4f4"));
                    tv_completed.setTextColor(Color.parseColor("#ffffff"));
                    flag = 2;
                    getList();
                }
                break;
            case R.id.iv_home:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        try {
            SharedPref.remove(getApplicationContext(), "is_task");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        super.onBackPressed();
    }
}