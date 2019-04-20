package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import logeshd.analysed.apis.challenge;
import logeshd.analysed.jobSeeker.adapter.listViewChallengesComp;
import logeshd.analysed.jobSeeker.adapter.listViewTasksComp;
import logeshd.analysed.jobSeeker.adapter.recycleViewChallenges;
import logeshd.analysed.jobSeeker.adapter.recycleViewTasks;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewChallenges extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_view_challenges);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });

        new updates().execute();
    }

    public class updates extends AsyncTask<Void,Void,Void> {

        AVLoadingIndicatorView pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        AVLoadingIndicatorView pcircle1 = (AVLoadingIndicatorView) findViewById(R.id.pcircle1);
        TextView tv_no_data=findViewById(R.id.tv_no_data);
        TextView tv_no_data1=findViewById(R.id.tv_no_data1);

        TextView tv_challenge_count=findViewById(R.id.tv_challenge_count);
        TextView tv_comp_count=findViewById(R.id.tv_comp_count);

        ListView l1 = (ListView) findViewById(R.id.list_challenge_comp);
        listViewChallengesComp list_adapter = new listViewChallengesComp(viewChallenges.this, new ArrayList<challenge>());

        RecyclerView rv_challenge = (RecyclerView) findViewById(R.id.rv_challenge);
        ArrayList<challenge> items = new ArrayList<>();
        recycleViewChallenges adapter = new recycleViewChallenges(viewChallenges.this,items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), 0, false);

        @Override
        protected Void doInBackground(Void... voids) {
            rv_challenge.setLayoutManager(mLayoutManager);
            rv_challenge.setAdapter(adapter);

            MainRepository.getService().getChallenges().enqueue(new Callback<List<challenge>>() {
                @Override
                public void onResponse(Call<List<challenge>> call, Response<List<challenge>> response) {
                    list_adapter.clear();
                    items.clear();

                    List<challenge> dlist = response.body();
                    if (dlist != null) {
                        for (challenge d : dlist) {
                            if (d.getScore() != 0)
                                list_adapter.add(d);
                            else
                                items.add(d);
                        }

                        if (list_adapter.isEmpty()) {
                            l1.setVisibility(View.GONE);
                            tv_no_data1.setVisibility(View.VISIBLE);
                            tv_comp_count.setVisibility(View.GONE);
                        }
                        else {
                            l1.setAdapter(list_adapter);
                            l1.setVisibility(View.VISIBLE);
                            tv_comp_count.setVisibility(View.VISIBLE);
                            tv_comp_count.setText(Integer.toString(list_adapter.getCount()));
                            tv_no_data1.setVisibility(View.GONE);
                        }

                        if (items.size()==0) {
                            rv_challenge.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);
                            tv_challenge_count.setVisibility(View.GONE);
                        }
                        else {
                            adapter.notifyDataSetChanged();
                            rv_challenge.setVisibility(View.VISIBLE);
                            tv_no_data.setVisibility(View.GONE);
                            tv_challenge_count.setVisibility(View.VISIBLE);
                            tv_challenge_count.setText(Integer.toString(items.size()));
                        }
                    }
                    else {
                        rv_challenge.setVisibility(View.GONE);
                        l1.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                        tv_comp_count.setVisibility(View.GONE);
                        tv_challenge_count.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                        tv_no_data1.setVisibility(View.VISIBLE);
                        Log.d("ddlogesh", "empty");
                    }

                    pcircle.setVisibility(View.GONE);
                    pcircle1.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<challenge>> call, Throwable t) {
                    Log.d("ddlogesh", t.getMessage());

                    rv_challenge.setVisibility(View.GONE);
                    l1.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    tv_comp_count.setVisibility(View.GONE);
                    tv_challenge_count.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                    tv_no_data1.setVisibility(View.VISIBLE);
                    pcircle.setVisibility(View.GONE);
                    pcircle1.setVisibility(View.GONE);
                }
            });

            return null;
        }
    }
}
