package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.aboutOrganization;
import logeshd.analysed.classes.drawer;
import logeshd.analysed.common.feedback;
import logeshd.analysed.common.login;
import logeshd.analysed.common.referral;
import logeshd.analysed.jobSeeker.adapter.listNavDrawer;
import logeshd.analysed.recruiter.database;
import logeshd.analysed.recruiter.jobListings;
import logeshd.analysed.recruiter.resumeSortingTool;
import logeshd.analysed.recruiter.shareProfile;
import logeshd.analysed.recruiter.startHiring0;
import logeshd.analysed.recruiter.viewTasksStatus;
import logeshd.analysed.viewChallengeStatus;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_dashboard);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ListView l1 = (ListView) findViewById(R.id.list_slidermenu);
        listNavDrawer adapter = new listNavDrawer(this, new ArrayList<drawer>());
        adapter.clear();
        adapter.add(new drawer("Dashboard", "nav_dashboard"));
        adapter.add(new drawer("Start Hiring!", "nav_recruitment"));
        adapter.add(new drawer("Job Listings", "nav_job_list"));
        adapter.add(new drawer("Database", "nav_database"));
        adapter.add(new drawer("Resume Sorting Tool", "nav_sort_resume"));
        adapter.add(new drawer("Tasks", "nav_task"));
        adapter.add(new drawer("Challenges", "nav_challenge"));
        adapter.add(new drawer("Share Profile", "nav_profile"));
        adapter.add(new drawer("Referral", "nav_referral"));
        adapter.add(new drawer("About Us", "nav_about"));
        adapter.add(new drawer("Sign Out", "nav_sign_out"));
        l1.setAdapter(adapter);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i1 = null;
                String data = ((logeshd.analysed.classes.drawer) parent.getItemAtPosition(position)).getTitle();
                if (data.equals("Dashboard")) {
                    drawer.closeDrawer((int) GravityCompat.START);
                } else if (data.equals("Start Hiring!")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), startHiring0.class);
                } else if (data.equals("Job Listings")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), jobListings.class);
                } else if (data.equals("Database")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), database.class);
                } else if (data.equals("Resume Sorting Tool")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), resumeSortingTool.class);
                } else if (data.equals("Tasks")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), viewTasksStatus.class);
                } else if (data.equals("Challenges")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), viewChallengeStatus.class);
                } else if (data.equals("Share Profile")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), shareProfile.class);
                } else if (data.equals("Referral")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), referral.class);
                } else if (data.equals("About Us")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), aboutOrganization.class);
                } else if (data.equals("Sign Out")) {
                    i1 = new Intent(dashboard.this.getApplicationContext(), login.class);
                }
                if (i1 != null) {
                    startActivity(i1);
                }
            }
        });

        ImageView iv_menu = (ImageView) findViewById(R.id.iv_menu);
        final ImageView iv_dash_card = (ImageView) findViewById(R.id.iv_dash_card);
        final ImageView iv_tab1 = (ImageView) findViewById(R.id.iv_tab1);
        final ImageView iv_tab2 = (ImageView) findViewById(R.id.iv_tab2);
        final ImageView iv_tab3 = (ImageView) findViewById(R.id.iv_tab3);

        TextView tv_dashboard = (TextView) findViewById(R.id.tv_dashboard);
        TextView tv_name = (TextView) findViewById(R.id.tv_name);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_dashboard.setTypeface(custom_font2);
        tv_name.setTypeface(custom_font1);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });

        iv_tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_dash_card.setImageResource(R.drawable.dash_card_1);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.active_black_circle);
                iv_tab2.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab3.setBackgroundResource(R.drawable.inactive_light_grey_circle);
            }
        });

        iv_tab2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iv_dash_card.setImageResource(R.drawable.dash_card_2);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab2.setBackgroundResource(R.drawable.active_black_circle);
                iv_tab3.setBackgroundResource(R.drawable.inactive_light_grey_circle);
            }
        });

        iv_tab3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iv_dash_card.setImageResource(R.drawable.dash_card_3);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab2.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab3.setBackgroundResource(R.drawable.active_black_circle);
            }
        });

        ((ImageView) findViewById(R.id.iv_dp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), viewProfile.class);
                startActivity(i1);
            }
        });

        ((TextView) findViewById(R.id.tv_issue2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), feedback.class);
                startActivity(i1);
            }
        });
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
            return;
        }
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
