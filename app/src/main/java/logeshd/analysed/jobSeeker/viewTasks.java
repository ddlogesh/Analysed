package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.classes.drawer;
import logeshd.analysed.classes.recStatus;
import logeshd.analysed.jobSeeker.adapter.listSearchRecent;
import logeshd.analysed.jobSeeker.adapter.listViewTasksComp;
import logeshd.analysed.recruiter.adapter.recycleTasks;
import logeshd.analysed.recruiter.createTask;

public class viewTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_view_tasks);

        final ArrayList<recStatus> item1 = new ArrayList<>();
        final RecyclerView rv_task = (RecyclerView) findViewById(R.id.rv_task);
        final recycleTasks adapter = new recycleTasks(this,item1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_task.setLayoutManager(mLayoutManager);
        rv_task.setAdapter(adapter);

        item1.add(new recStatus("Task 1", "Task analysis is the analysis of how a task is accomplished, including a detailed description of both manual and mental activities",-1,70));
        item1.add(new recStatus("Task 2", "Task analysis is the analysis of how a task is accomplished, including a detailed description of both manual and mental activities",-1,50));
        item1.add(new recStatus("Task 3", "Task analysis is the analysis of how a task is accomplished, including a detailed description of both manual and mental activities",-1,100));
        adapter.notifyDataSetChanged();

        /********************************************************/

        ListView l1 = (ListView) findViewById(R.id.list_task_comp);
        listViewTasksComp adapter1 = new listViewTasksComp(this, new ArrayList<drawer>());
        adapter1.clear();
        adapter1.add(new drawer("Task 1","Today, 12:20 PM"));
        adapter1.add(new drawer("Task 2","Yesterday, 12:20 PM"));
        adapter1.add(new drawer("Task 3","23/03/19, 12:20 PM"));
        l1.setAdapter(adapter1);

        /********************************************************/

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });

        TextView tv_task_count=findViewById(R.id.tv_task_count);
        TextView tv_comp_count=findViewById(R.id.tv_comp_count);

        tv_task_count.setText(Integer.toString(item1.size()));
        tv_comp_count.setText(Integer.toString(item1.size()));
    }
}
