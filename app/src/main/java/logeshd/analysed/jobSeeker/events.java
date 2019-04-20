package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.graphics.Color;
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

import com.savvi.rangedatepicker.CalendarPickerView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logeshd.analysed.R;
import logeshd.analysed.apis.e_book;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.task;
import logeshd.analysed.jobSeeker.adapter.listViewTasksComp;
import logeshd.analysed.jobSeeker.adapter.recycleViewEvents;
import logeshd.analysed.jobSeeker.adapter.recycleViewTasks;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_events);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date d=new Date(2017,1,1);
        Date d1=new Date(2019,1,1);
        calendar.init(d,d1).inMode(CalendarPickerView.SelectionMode.RANGE);

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
        TextView tv_no_data=findViewById(R.id.tv_no_data);
        TextView tv_e_book_count=findViewById(R.id.tv_e_book_count);

        RecyclerView rv_e_book = (RecyclerView) findViewById(R.id.rv_e_book);

        @Override
        protected Void doInBackground(Void... voids) {

            MainRepository.getService().getEventsApi(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<List<e_book>>() {
                @Override
                public void onResponse(Call<List<e_book>> call, Response<List<e_book>> response) {
                    List<e_book> a = response.body();
                    if (a != null) {
                        recycleViewEvents adapter = new recycleViewEvents(events.this,a);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), 0, false);
                        rv_e_book.setLayoutManager(mLayoutManager);
                        rv_e_book.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        rv_e_book.setVisibility(View.VISIBLE);
                        tv_e_book_count.setVisibility(View.VISIBLE);
                        tv_e_book_count.setText(Integer.toString(a.size()));

                        tv_no_data.setVisibility(View.GONE);
                        pcircle.setVisibility(View.GONE);
                    }
                    else {
                        rv_e_book.setVisibility(View.GONE);
                        tv_e_book_count.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                        pcircle.setVisibility(View.GONE);
                        Log.d("ddlogesh", "Failed");
                    }
                }

                @Override
                public void onFailure(Call<List<e_book>> call, Throwable t) {
                    rv_e_book.setVisibility(View.GONE);
                    tv_e_book_count.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                    pcircle.setVisibility(View.GONE);
                }
            });

            return null;
        }
    }
}
