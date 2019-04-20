package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.wang.avi.AVLoadingIndicatorView;

import logeshd.analysed.R;
import logeshd.analysed.apis.skills;
import logeshd.analysed.jobSeeker.dashboard;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewSkills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_view_skills);

        ImageView iv_home=findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),dashboard.class));
            }
        });

        new updates().execute();
    }

    public class updates extends AsyncTask<Void,Void,Void> {

        AVLoadingIndicatorView pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);

        CircularProgressBar pb_total = findViewById(R.id.pb_total);
        TextView tv_total_percent = findViewById(R.id.tv_total_percent);
        TextView tv_pt= findViewById(R.id.tv_pt);
        TextView tv_os = findViewById(R.id.tv_os);

        TextView tv_t_count = findViewById(R.id.tv_t_count);
        TextView tv_total= findViewById(R.id.tv_total);

        CircularProgressBar pb_rec = findViewById(R.id.pb_rec);
        TextView tv_r_count = findViewById(R.id.tv_r_count);
        TextView tv_rec = findViewById(R.id.tv_rec);
        TextView tv_rec_percent= findViewById(R.id.tv_rec_percent);

        CircularProgressBar pb_challenge = findViewById(R.id.pb_challenge);
        TextView tv_c_count = findViewById(R.id.tv_c_count);
        TextView tv_challenge = findViewById(R.id.tv_challenge);
        TextView tv_challenge_percent= findViewById(R.id.tv_challenge_percent);

        Handler handler = new Handler();
        Handler handler2 = new Handler();
        int i,j,k;

        @Override
        protected Void doInBackground(Void... voids) {

            MainRepository.getService().getSkillsApi(SharedPref.getString(getApplicationContext(),"user_name"), SharedPref.getInt(getApplicationContext(),"user_id")).enqueue(new Callback<skills>() {
                @Override
                public void onResponse(Call<skills> call, Response<skills> response) {
                    final skills s = response.body();
                    if(s!=null){
                        tv_t_count.setText(Integer.toString(s.getT_total()));
                        tv_r_count.setText(Integer.toString(s.getR_total()));
                        tv_c_count.setText(Integer.toString(s.getC_total()));

                        tv_pt.setVisibility(View.VISIBLE);
                        tv_os.setVisibility(View.VISIBLE);
                        pb_rec.setVisibility(View.VISIBLE);
                        pb_challenge.setVisibility(View.VISIBLE);
                        pb_total.setVisibility(View.VISIBLE);

                        pcircle.setVisibility(View.GONE);

                        drawProgress(s);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for(i=0;i<s.getR_skill();) {
                                    writeRSkill(s.getR_skill());

                                    try {
                                        Thread.sleep(1200/(int)s.getR_skill());
                                    }
                                    catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d("ddlogesh", e.getMessage());
                                    }
                                }
                            }
                        }).start();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for(j=0;j<s.getC_skill();) {
                                    writeCSkill(s.getC_skill());

                                    try {
                                        Thread.sleep(1200/(int)s.getC_skill());
                                    }
                                    catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d("ddlogesh", e.getMessage());
                                    }
                                }
                            }
                        }).start();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for(k=0;k<s.getT_skill();) {
                                    writeTSkill(s.getT_skill());

                                    try {
                                        Thread.sleep(1200/(int)s.getT_skill());
                                    }
                                    catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d("ddlogesh", e.getMessage());
                                    }
                                }
                            }
                        }).start();
                    }
                    else
                        pcircle.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<skills> call, Throwable t) {
                    pcircle.setVisibility(View.GONE);
                }
            });

            return null;
        }

        private void drawProgress(final skills s){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    pb_total.setProgressWithAnimation(s.getT_skill(), 1200);
                    pb_rec.setProgressWithAnimation(s.getR_skill(), 1200);
                    pb_challenge.setProgressWithAnimation(s.getC_skill(), 1200);
                }
            });
        }

        private void writeRSkill(final float f){
            handler2.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(i==(int)f)
                            tv_rec_percent.setText(Float.toString(f) + "%");
                        else
                            tv_rec_percent.setText(Integer.toString(i) + "%");
                        i++;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ddlogesh", e.getMessage());
                    }
                }
            });
        }

        private void writeCSkill(final float f){
            handler2.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(j==(int)f)
                            tv_challenge_percent.setText(Float.toString(f) + "%");
                        else
                            tv_challenge_percent.setText(Integer.toString(j) + "%");
                        j++;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ddlogesh", e.getMessage());
                    }
                }
            });
        }

        private void writeTSkill(final float f){
            handler2.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(k==(int)f)
                            tv_total_percent.setText(Float.toString(f));
                        else
                            tv_total_percent.setText(Integer.toString(k));
                        k++;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ddlogesh", e.getMessage());
                    }
                }
            });
        }
    }
}
