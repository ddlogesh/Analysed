package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.recruiter.adapter.listCreateTask;
import logeshd.analysed.classes.drawer;

public class createChallenge extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.r_create_challenge);

        final ListView l1 = (ListView) findViewById(R.id.create_challenge_tech_list);
        ArrayList<drawer> arr = new ArrayList<>();
        listCreateTask adapter = new listCreateTask(this, arr);
        adapter.clear();
        adapter.add(new drawer("Technique1", "Hint1"));
        adapter.add(new drawer("Technique2", "Hint2"));
        l1.setAdapter(adapter);

        TextView tv_view = (TextView) findViewById(R.id.tv_view);
        tv_view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
            }
        });

        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_create = (TextView) findViewById(R.id.tv_create);
        TextView tv_reward = (TextView) findViewById(R.id.tv_reward);
        TextView tv_add = (TextView) findViewById(R.id.tv_add);
        TextView tv_plus = (TextView) findViewById(R.id.tv_plus);

        EditText ev_name = (EditText) findViewById(R.id.ev_name);
        EditText ev_description = (EditText) findViewById(R.id.ev_description);
        EditText ev_deadline = (EditText) findViewById(R.id.ev_deadline);
        EditText ev_duration = (EditText) findViewById(R.id.ev_duration);
        EditText ev_winner = (EditText) findViewById(R.id.ev_winner);
        EditText ev_ways = (EditText) findViewById(R.id.ev_ways);
        EditText ev_hint = (EditText) findViewById(R.id.ev_hint);

        CheckBox ck_cash = (CheckBox) findViewById(R.id.ck_cash);
        CheckBox ck_coupon = (CheckBox) findViewById(R.id.ck_coupon);
        CheckBox ck_service = (CheckBox) findViewById(R.id.ck_service);
        CheckBox ck_hire = (CheckBox) findViewById(R.id.ck_hire);
        CheckBox ck_other = (CheckBox) findViewById(R.id.ck_other);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");

        tv_title.setTypeface(custom_font1);
        tv_create.setTypeface(custom_font2);
        tv_reward.setTypeface(custom_font2);
        tv_add.setTypeface(custom_font2);
        tv_view.setTypeface(custom_font3);
        tv_plus.setTypeface(custom_font3);
        ev_name.setTypeface(custom_font3);
        ev_description.setTypeface(custom_font3);
        ev_deadline.setTypeface(custom_font3);
        ev_duration.setTypeface(custom_font3);
        ev_winner.setTypeface(custom_font3);
        ev_ways.setTypeface(custom_font3);
        ck_coupon.setTypeface(custom_font3);
        ck_service.setTypeface(custom_font3);
        ck_hire.setTypeface(custom_font3);
    }
}
