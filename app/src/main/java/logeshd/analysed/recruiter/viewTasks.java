package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.recruiter.adapter.recycleTasks;
import logeshd.analysed.classes.recStatus;
import logeshd.analysed.jobSeeker.dashboard;

public class viewTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_view_tasks);

        final ArrayList<recStatus> item1 = new ArrayList<>();
        final RecyclerView rv_task = (RecyclerView) findViewById(R.id.rv_task);
        final recycleTasks adapter = new recycleTasks(this,item1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_task.setLayoutManager(mLayoutManager);
        rv_task.setAdapter(adapter);

        item1.add(new recStatus("Task 1", "Task analysis is the analysis of how a task is accomplished, including a detailed description of both manual and mental activities",1,8));
        item1.add(new recStatus("Task 2", "Task analysis is the analysis of how a task is accomplished, including a detailed description of both manual and mental activities",0,10));
        item1.add(new recStatus("Task 3", "Task analysis is the analysis of how a task is accomplished, including a detailed description of both manual and mental activities",1,12));
        adapter.notifyDataSetChanged();

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });

        ((ImageView) findViewById(R.id.iv_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), createTask.class);
                startActivity(i1);
            }
        });

        final LinearLayout layout01=findViewById(R.id.layout01);
        final LinearLayout layout02=findViewById(R.id.layout02);
        final LinearLayout layout03=findViewById(R.id.layout03);

        final TextView tv_tab1=findViewById(R.id.tv_tab1);
        final TextView tv_tab2=findViewById(R.id.tv_tab2);
        final TextView tv_tab3=findViewById(R.id.tv_tab3);

        layout01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tab1.setTextColor(Color.parseColor("#000000"));
                tv_tab2.setTextColor(Color.parseColor("#BEFFFFFF"));
                tv_tab3.setTextColor(Color.parseColor("#BEFFFFFF"));

                layout01.setBackgroundResource(R.drawable.button_white_solid);
                layout02.setBackgroundColor(Color.parseColor("#00ffffff"));
                layout03.setBackgroundColor(Color.parseColor("#00ffffff"));
            }
        });

        layout02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tab1.setTextColor(Color.parseColor("#BEFFFFFF"));
                tv_tab2.setTextColor(Color.parseColor("#000000"));
                tv_tab3.setTextColor(Color.parseColor("#BEFFFFFF"));

                layout01.setBackgroundColor(Color.parseColor("#00ffffff"));
                layout02.setBackgroundResource(R.drawable.button_white_solid);
                layout03.setBackgroundColor(Color.parseColor("#00ffffff"));
            }
        });

        layout03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tab1.setTextColor(Color.parseColor("#BEFFFFFF"));
                tv_tab2.setTextColor(Color.parseColor("#BEFFFFFF"));
                tv_tab3.setTextColor(Color.parseColor("#000000"));

                layout01.setBackgroundColor(Color.parseColor("#00ffffff"));
                layout02.setBackgroundColor(Color.parseColor("#00ffffff"));
                layout03.setBackgroundResource(R.drawable.button_white_solid);
            }
        });
    }
}