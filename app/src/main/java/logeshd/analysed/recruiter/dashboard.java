package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;
import logeshd.analysed.aboutOrganization;
import logeshd.analysed.common.adapter.listNavDrawer;
import logeshd.analysed.classes.drawer;
import logeshd.analysed.common.login;
import logeshd.analysed.common.feedback;
import logeshd.analysed.jobSeeker.viewProfile;
import logeshd.analysed.common.referral;
import logeshd.analysed.utils.SharedPref;

public class dashboard extends AppCompatActivity implements View.OnClickListener{

    TextView tv_dashboard,tv_name,tv_issue2;
    DrawerLayout drawer;
    ImageView iv_dash_card,iv_menu,iv_tab1,iv_tab2,iv_tab3;
    CircleImageView iv_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_dashboard);

        Log.d("ddlogesh","cam here3");

        iv_dash_card = (ImageView) findViewById(R.id.iv_dash_card);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);           iv_menu.setOnClickListener(this);
        iv_tab1 = (ImageView) findViewById(R.id.iv_tab1);           iv_tab1.setOnClickListener(this);
        iv_tab2 = (ImageView) findViewById(R.id.iv_tab2);           iv_tab2.setOnClickListener(this);
        iv_tab3 = (ImageView) findViewById(R.id.iv_tab3);           iv_tab3.setOnClickListener(this);

        iv_dp = (CircleImageView) findViewById(R.id.iv_dp);         iv_dp.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        tv_dashboard = (TextView) findViewById(R.id.tv_dashboard);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_issue2 = (TextView) findViewById(R.id.tv_issue2);        tv_issue2.setOnClickListener(this);

        new updates().execute();

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_dashboard.setTypeface(custom_font2);
        tv_name.setTypeface(custom_font1);
    }

    public class updates extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            tv_name.setText(SharedPref.getString(getApplicationContext(),"f_name")+ " " + SharedPref.getString(getApplicationContext(),"l_name"));

            try {
                File storageDir = getApplicationContext().getExternalCacheDir();
                if (storageDir == null || !storageDir.exists()) {
                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                    if (!storageDir.exists())
                        storageDir.mkdirs();
                }
                File cacheFile = new File(storageDir, "profile.jpg");
                Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getPath());
                iv_dp.setImageBitmap(bitmap);
                Log.d("ddlogesh","cam here4");
            }
            catch (Exception e){
                //TODO:
                //if profile_image from cache directory removed
                //handle by giving request & downloading from server
                e.printStackTrace();
                Log.d("ddlogesh","cam here5: "+e.getMessage());
            }

            ListView l1 = (ListView) findViewById(R.id.list_slidermenu);
            listNavDrawer adapter = new listNavDrawer(dashboard.this, new ArrayList<drawer>());
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
                    String data = ((logeshd.analysed.classes.drawer) parent.getItemAtPosition(position)).getTitle();
                    if (data.equals("Dashboard"))
                        drawer.closeDrawer((int) GravityCompat.START);
                    else if (data.equals("Start Hiring!"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), startHiring0.class));
                    else if (data.equals("Job Listings"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), jobListings.class));
                    else if (data.equals("Database"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), database.class));
                    else if (data.equals("Resume Sorting Tool"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), resumeSortingTool.class));
                    else if (data.equals("Tasks"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), viewTasks.class));
                    else if (data.equals("Challenges"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), viewChallenges.class));
                    else if (data.equals("Share Profile"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), shareProfile.class));
                    else if (data.equals("Referral"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), referral.class));
                    else if (data.equals("About Us"))
                        startActivity(new Intent(dashboard.this.getApplicationContext(), aboutOrganization.class));
                    else if (data.equals("Sign Out")) {
                        SharedPref.removeAll(getApplicationContext());
                        startActivity(new Intent(dashboard.this.getApplicationContext(), login.class));
                    }
                }
            });

            return null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_issue2:
                startActivity(new Intent(getApplicationContext(), feedback.class));
                break;

            case R.id.iv_dp:
                startActivity(new Intent(getApplicationContext(), viewProfile.class));
                break;

            case R.id.iv_menu:
                drawer.openDrawer(Gravity.START);
                break;

            case R.id.iv_tab1:
                iv_dash_card.setImageResource(R.drawable.dash_card_1);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.active_black_circle);
                iv_tab2.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab3.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                break;

            case R.id.iv_tab2:
                iv_dash_card.setImageResource(R.drawable.dash_card_2);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab2.setBackgroundResource(R.drawable.active_black_circle);
                iv_tab3.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                break;

            case R.id.iv_tab3:
                iv_dash_card.setImageResource(R.drawable.dash_card_3);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_dash_card);
                iv_tab1.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab2.setBackgroundResource(R.drawable.inactive_light_grey_circle);
                iv_tab3.setBackgroundResource(R.drawable.active_black_circle);
                break;
        }
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
