package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import logeshd.analysed.R;
import logeshd.analysed.apis.databases;
import logeshd.analysed.recruiter.adapter.listDatabase;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class database extends AppCompatActivity {

    AVLoadingIndicatorView pcircle;
    TextView tv_no_data;
    ListView l1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_database);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        tv_no_data=findViewById(R.id.tv_no_data);
        l1 = (ListView) findViewById(R.id.lv_database);

        MainRepository.getService().getDatabase(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<List<databases>>() {
            @Override
            public void onResponse(Call<List<databases>> call, Response<List<databases>> response) {
                listDatabase adapter = new listDatabase(database.this, new ArrayList<databases>());
                adapter.clear();

                List<databases> dlist=response.body();
                if(dlist!=null) {
                    for (databases d : dlist)
                        adapter.add(new databases(d.getId(), d.getFname(), d.getLname(), d.getPicture(), d.getPosition(),d.getQualification(),d.getYearofpassing(),d.getExperience(),d.getEmail(),d.getSkills()));
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
            public void onFailure(Call<List<databases>> call, Throwable t) {
                Log.d("ddlogesh",t.getMessage());
                l1.setVisibility(View.GONE);
                tv_no_data.setVisibility(View.VISIBLE);
                pcircle.setVisibility(View.GONE);
            }
        });

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        EditText ev_skills = (EditText) findViewById(R.id.ev_skills);
        EditText ev_qualification = (EditText) findViewById(R.id.ev_qualification);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        tv_title.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf"));
        ev_skills.setTypeface(custom_font1);
        ev_qualification.setTypeface(custom_font1);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });
    }
}