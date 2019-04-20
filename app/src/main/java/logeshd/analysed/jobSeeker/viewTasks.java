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
import logeshd.analysed.apis.task;
import logeshd.analysed.jobSeeker.adapter.listViewTasksComp;
import logeshd.analysed.jobSeeker.adapter.recycleViewTasks;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_view_tasks);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });

        new updates().execute();
    }

    public class updates extends AsyncTask<Void,Void,Void>{

        AVLoadingIndicatorView pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        AVLoadingIndicatorView pcircle1 = (AVLoadingIndicatorView) findViewById(R.id.pcircle1);
        TextView tv_no_data=findViewById(R.id.tv_no_data);
        TextView tv_no_data1=findViewById(R.id.tv_no_data1);

        TextView tv_task_count=findViewById(R.id.tv_task_count);
        TextView tv_comp_count=findViewById(R.id.tv_comp_count);

        ListView l1 = (ListView) findViewById(R.id.list_task_comp);
        listViewTasksComp list_adapter = new listViewTasksComp(viewTasks.this, new ArrayList<task>());

        RecyclerView rv_task = (RecyclerView) findViewById(R.id.rv_task);
        ArrayList<task> items = new ArrayList<>();
        recycleViewTasks adapter = new recycleViewTasks(viewTasks.this,items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), 0, false);

        @Override
        protected Void doInBackground(Void... voids) {
            rv_task.setLayoutManager(mLayoutManager);
            rv_task.setAdapter(adapter);

            MainRepository.getService().getTasks(SharedPref.getString(getApplicationContext(), "user_name")).enqueue(new Callback<List<task>>() {
                @Override
                public void onResponse(Call<List<task>> call, Response<List<task>> response) {
                    list_adapter.clear();
                    items.clear();

                    List<task> dlist = response.body();
                    if (dlist != null) {
                        for (task d : dlist) {
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
                            rv_task.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);
                            tv_task_count.setVisibility(View.GONE);
                        }
                        else {
                            adapter.notifyDataSetChanged();
                            rv_task.setVisibility(View.VISIBLE);
                            tv_no_data.setVisibility(View.GONE);
                            tv_task_count.setVisibility(View.VISIBLE);
                            tv_task_count.setText(Integer.toString(items.size()));
                        }
                    }
                    else {
                        rv_task.setVisibility(View.GONE);
                        l1.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                        tv_comp_count.setVisibility(View.GONE);
                        tv_task_count.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                        tv_no_data1.setVisibility(View.VISIBLE);
                        Log.d("ddlogesh", "empty");
                    }

                    pcircle.setVisibility(View.GONE);
                    pcircle1.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<task>> call, Throwable t) {
                    Log.d("ddlogesh", t.getMessage());

                    rv_task.setVisibility(View.GONE);
                    l1.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    tv_comp_count.setVisibility(View.GONE);
                    tv_task_count.setVisibility(View.GONE);
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