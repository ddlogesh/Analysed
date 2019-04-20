package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;
import logeshd.analysed.apis.task;
import logeshd.analysed.jobSeeker.adapter.listTaskStatus;
import logeshd.analysed.jobSeeker.adapter.listChallengeStatus;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class scoreBoard extends AppCompatActivity implements View.OnClickListener{

    AVLoadingIndicatorView pcircle;
    TextView tv_no_data,tv_recruiter,tv_challenge,tv_title;
    ListView l1;
    ImageView iv_home;
    static int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_score_board);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        l1 = (ListView) findViewById(R.id.list_score_board);

        tv_no_data=findViewById(R.id.tv_no_data);
        tv_recruiter=findViewById(R.id.tv_recruiter);   tv_recruiter.setOnClickListener(this);
        tv_challenge=findViewById(R.id.tv_challenge);   tv_challenge.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_home=findViewById(R.id.iv_home);             iv_home.setOnClickListener(this);

        Typeface custom_font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        tv_title.setTypeface(custom_font);

        getList();
    }

    private void getList(){
        tv_no_data.setVisibility(View.GONE);
        l1.setVisibility(View.GONE);
        pcircle.setVisibility(View.VISIBLE);
        tv_recruiter.setEnabled(false);
        tv_challenge.setEnabled(false);
        Log.d("ddlogesh","cam3");

        if(flag==1) {
            MainRepository.getService().getTasks(SharedPref.getString(getApplicationContext(), "user_name")).enqueue(new Callback<List<task>>() {
                @Override
                public void onResponse(Call<List<task>> call, Response<List<task>> response) {
                    listTaskStatus adapter = new listTaskStatus(scoreBoard.this, new ArrayList<task>());
                    adapter.clear();

                    List<task> dlist = response.body();
                    if (dlist != null) {
                        for (task d : dlist) {
                            if (d.getScore() != 0)
                                adapter.add(d);
                        }

                        if (adapter.isEmpty()) {
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
                        Log.d("ddlogesh", "empty");
                    }

                    pcircle.setVisibility(View.GONE);
                    tv_recruiter.setEnabled(true);
                    tv_challenge.setEnabled(true);
                }

                @Override
                public void onFailure(Call<List<task>> call, Throwable t) {
                    Log.d("ddlogesh", t.getMessage());
                    l1.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                    pcircle.setVisibility(View.GONE);
                    tv_recruiter.setEnabled(true);
                    tv_challenge.setEnabled(true);
                }
            });
        }
        else if(flag==2){
            MainRepository.getService().getChallenges().enqueue(new Callback<List<challenge>>() {
                @Override
                public void onResponse(Call<List<challenge>> call, Response<List<challenge>> response) {
                    listChallengeStatus adapter = new listChallengeStatus(scoreBoard.this, new ArrayList<challenge>());
                    adapter.clear();

                    List<challenge> dlist = response.body();
                    if (dlist != null) {
                        for (challenge d : dlist) {
                            if (d.getSeeker_email().equals(SharedPref.getString(getApplicationContext(), "user_name")) && d.getScore() != 0)
                                adapter.add(d);
                        }

                        if (adapter.isEmpty()) {
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
                        Log.d("ddlogesh", "empty");
                    }

                    pcircle.setVisibility(View.GONE);
                    tv_recruiter.setEnabled(true);
                    tv_challenge.setEnabled(true);
                }

                @Override
                public void onFailure(Call<List<challenge>> call, Throwable t) {
                    Log.d("ddlogesh", t.getMessage());
                    l1.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                    pcircle.setVisibility(View.GONE);
                    tv_recruiter.setEnabled(true);
                    tv_challenge.setEnabled(true);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_recruiter:
                if(flag==2) {
                    tv_recruiter.setTextColor(Color.parseColor("#ffffff"));
                    tv_challenge.setTextColor(Color.parseColor("#74fdf4f4"));
                    flag = 1;
                    getList();
                }
                break;
            case R.id.tv_challenge:
                Log.d("ddlogesh","cam1");
                if(flag==1) {
                    tv_recruiter.setTextColor(Color.parseColor("#74fdf4f4"));
                    tv_challenge.setTextColor(Color.parseColor("#ffffff"));
                    flag = 2;
                    Log.d("ddlogesh","cam2");
                    getList();
                }
                break;
            case R.id.iv_home:
                onBackPressed();
                break;
        }
    }
}
