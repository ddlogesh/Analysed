package logeshd.analysed.recruiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;
import logeshd.analysed.recruiter.adapter.listChallengeStatus;
import logeshd.analysed.recruiter.adapter.listTaskStatus;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewChallenges extends AppCompatActivity implements View.OnClickListener{

    AVLoadingIndicatorView pcircle;
    TextView tv_no_data,tv_day,tv_date;
    ListView l1;
    ImageView iv_create,iv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_view_challenges);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        l1 = (ListView) findViewById(R.id.list_challenge);

        tv_no_data=findViewById(R.id.tv_no_data);
        tv_day=findViewById(R.id.tv_day);
        tv_date=findViewById(R.id.tv_date);

        iv_create=findViewById(R.id.iv_create); iv_create.setOnClickListener(this);
        iv_home=findViewById(R.id.iv_home);     iv_home.setOnClickListener(this);

        MainRepository.getService().getAssignedChallenges(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<List<challenge>>() {
            @Override
            public void onResponse(Call<List<challenge>> call, Response<List<challenge>> response) {
                listChallengeStatus adapter = new listChallengeStatus(viewChallenges.this, new ArrayList<challenge>());
                adapter.clear();

                List<challenge> dlist=response.body();
                if(dlist != null) {
                    for (challenge d : dlist) {
                        challenge c=new challenge();
                        c.setId(d.getId());
                        c.setEmail(d.getEmail());
                        c.setChallengeId(d.getChallengeId());
                        c.setChallenge_end(d.getChallenge_end());
                        c.setChallenge_name(d.getChallenge_name());
                        c.setChallenge_description(d.getChallenge_description());
                        c.setWinners(d.getWinners());
                        c.setChallenge_time(d.getChallenge_time());
                        c.setEnd_challenge(d.getEnd_challenge());
                        c.setSeeker_email(d.getSeeker_email());
                        c.setFile(d.getFile());
                        c.setT_score(d.getT_score());
                        c.setScore(d.getScore());
                        adapter.add(c);
                    }
                    l1.setAdapter(adapter);

                    l1.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                }
                else {
                    l1.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                }

                pcircle.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<challenge>> call, Throwable t) {
                Log.d("ddlogesh",t.getMessage());
                l1.setVisibility(View.GONE);
                tv_no_data.setVisibility(View.VISIBLE);
                pcircle.setVisibility(View.GONE);
            }
        });

        SimpleDateFormat sdf1=new SimpleDateFormat("EEEE", Locale.US);
        SimpleDateFormat sdf2=new SimpleDateFormat("MMMM dd", Locale.US);
        tv_day.setText(sdf1.format(new Date()));
        tv_date.setText(sdf2.format(new Date()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home:
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                break;
            case R.id.iv_create:
                startActivity(new Intent(getApplicationContext(), createChallenge.class));
                break;
        }
    }
}
