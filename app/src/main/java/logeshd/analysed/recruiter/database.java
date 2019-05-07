package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import spencerstudios.com.bungeelib.Bungee;

public class database extends AppCompatActivity implements View.OnClickListener{

    AVLoadingIndicatorView pcircle;
    TextView tv_no_data;
    ImageView iv_search;
    EditText ev_name,ev_position;
    ListView l1;

    List<databases> dlist;
    listDatabase adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_database);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        tv_no_data=findViewById(R.id.tv_no_data);
        l1 = (ListView) findViewById(R.id.lv_database);

        MainRepository.getService().getDatabase(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<List<databases>>() {
            @Override
            public void onResponse(Call<List<databases>> call, Response<List<databases>> response) {
                adapter = new listDatabase(database.this, new ArrayList<databases>());
                adapter.clear();

                dlist=response.body();
                if(dlist!=null) {
                    for (databases d : dlist)
                        adapter.add(new databases(d.getId(), d.getFname(), d.getLname(), d.getPicture(), d.getPosition(), d.getQualification(), d.getYearofpassing(), d.getExperience() ,d.getLocation(), d.getEmail(), d.getSkills()));
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
        ev_name = (EditText) findViewById(R.id.ev_name);
        ev_position = (EditText) findViewById(R.id.ev_position);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/lato.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/lato_bold.ttf");

        tv_title.setTypeface(custom_font2);
        ev_name.setTypeface(custom_font1);
        ev_position.setTypeface(custom_font1);

        iv_search = (ImageView) findViewById(R.id.iv_search);           iv_search.setOnClickListener(this);
        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);     iv_home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
                String name = ev_name.getEditableText().toString().trim().toLowerCase();
                String position = ev_position.getEditableText().toString().trim().toLowerCase();

                if(name.length()>0 || position.length()>0) {
                    if (dlist != null) {
                        adapter.clear();
                        Log.d("ddlogesh", "clicked" + position);

                        for (databases d : dlist) {
                            String fname = d.getFname().toLowerCase();
                            String lname = d.getLname().toLowerCase();
                            String pos = d.getPosition().toLowerCase();

                            Boolean a1=fname.contains(name);
                            Boolean a2=lname.contains(name);
                            Boolean b=pos.contains(position);

                            if (name.length()>0 && position.length()>0 && (a1 || a2) && b)
                                adapter.add(new databases(d.getId(), d.getFname(), d.getLname(), d.getPicture(), d.getPosition(), d.getQualification(), d.getYearofpassing(), d.getExperience(), d.getLocation(), d.getEmail(), d.getSkills()));
                            else if (name.length()>0 && position.length()==0 && (a1 || a2))
                                adapter.add(new databases(d.getId(), d.getFname(), d.getLname(), d.getPicture(), d.getPosition(), d.getQualification(), d.getYearofpassing(), d.getExperience(), d.getLocation(), d.getEmail(), d.getSkills()));
                            else if (name.length()==0 && position.length()>0 && b)
                                adapter.add(new databases(d.getId(), d.getFname(), d.getLname(), d.getPicture(), d.getPosition(), d.getQualification(), d.getYearofpassing(), d.getExperience(), d.getLocation(), d.getEmail(), d.getSkills()));
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
                    }
                }
                else
                    Toast.makeText(this, "Both name and position cannot be empty!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_home:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        String name = ev_name.getEditableText().toString().trim();
        String position = ev_position.getEditableText().toString().trim();

        if(name.length()>0 || position.length()>0) {
            Intent intent=new Intent(getApplicationContext(),database.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Bungee.fade(database.this);
        }
        else
            super.onBackPressed();
    }
}