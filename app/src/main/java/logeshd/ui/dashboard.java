package logeshd.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import logeshd.ui.classes.itemDrawer;
import logeshd.ui.adapter.listViewDrawer;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView l1=(ListView)findViewById(R.id.list_slidermenu);
        ArrayList<itemDrawer> arr = new ArrayList<itemDrawer>();
        listViewDrawer adapter = new listViewDrawer(this, arr);
        adapter.clear();

        adapter.add(new itemDrawer("Dashboard","nav_dashboard"));
        adapter.add(new itemDrawer("Virtual Recruitment","nav_recruitment"));
        adapter.add(new itemDrawer("My Database","nav_database"));
        adapter.add(new itemDrawer("Job Listings","nav_job_list"));
        adapter.add(new itemDrawer("Resume Sorting Tool","nav_sort_resume"));
        adapter.add(new itemDrawer("Create A Task","nav_task"));
        adapter.add(new itemDrawer("Create A Challenge","nav_challenge"));
        adapter.add(new itemDrawer("Share Profile","nav_profile"));
        adapter.add(new itemDrawer("Referral","nav_referral"));
        adapter.add(new itemDrawer("About Us","nav_about"));
        adapter.add(new itemDrawer("Sign Out","nav_sign_out"));
        l1.setAdapter(adapter);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i1=null;
                String data = (String)((itemDrawer)parent.getItemAtPosition(position)).getTitle();
                if(data.equals("Dashboard"))
                    drawer.closeDrawer(GravityCompat.START);
                else if(data.equals("Job Listings"))
                    i1=new Intent(getApplicationContext(), myJobs.class);
                else if(data.equals("Resume Sorting Tool"))
                    i1=new Intent(getApplicationContext(), resumeSortingTool.class);
                else if(data.equals("About Us"))
                    i1=new Intent(getApplicationContext(), aboutOrganization.class);
                else if(data.equals("Sign Out"))
                    i1=new Intent(getApplicationContext(), login.class);

                if(i1!=null)
                    startActivity(i1);
            }
        });

        /*********************************************************************************/

        ImageView iv_menu = findViewById(R.id.iv_menu);
        final ImageView iv_dash_card = findViewById(R.id.iv_dash_card);
        final ImageView iv_tab1 = findViewById(R.id.iv_tab1);
        final ImageView iv_tab2 = findViewById(R.id.iv_tab2);
        final ImageView iv_tab3 = findViewById(R.id.iv_tab3);

        TextView tv_dashboard = findViewById(R.id.tv_dashboard);
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        tv_dashboard.setTypeface(custom_font2);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        iv_tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_dash_card.setImageResource(R.drawable.dash_card_1);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.dash_active_tab);
                iv_tab2.setBackgroundResource(R.drawable.dash_inactive_tab);
                iv_tab3.setBackgroundResource(R.drawable.dash_inactive_tab);

            }
        });

        iv_tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_dash_card.setImageResource(R.drawable.dash_card_2);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.dash_inactive_tab);
                iv_tab2.setBackgroundResource(R.drawable.dash_active_tab);
                iv_tab3.setBackgroundResource(R.drawable.dash_inactive_tab);
            }
        });

        iv_tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_dash_card.setImageResource(R.drawable.dash_card_3);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.dash_inactive_tab);
                iv_tab2.setBackgroundResource(R.drawable.dash_inactive_tab);
                iv_tab3.setBackgroundResource(R.drawable.dash_active_tab);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            Intent intent1 = new Intent(Intent.ACTION_MAIN);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent1.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent1);
        }
    }
}
