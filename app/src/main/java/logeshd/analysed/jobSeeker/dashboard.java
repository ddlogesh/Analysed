package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.userDetails;
import logeshd.analysed.classes.drawer;
import logeshd.analysed.common.feedback;
import logeshd.analysed.common.login;
import logeshd.analysed.common.referral;
import logeshd.analysed.common.adapter.listNavDrawer;
import logeshd.analysed.common.shareProfile;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import logeshd.analysed.jobSeeker.viewChallenges;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard extends AppCompatActivity implements View.OnClickListener{

    TextView tv_dashboard,tv_name,tv_issue2;
    DrawerLayout drawer;
    ImageView iv_dash_card,iv_menu,iv_tab1,iv_tab2,iv_tab3;
    CircleImageView iv_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_dashboard);

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

    public String getFileContents(String path){
        try {
            String inp,text="";

            File file=new File(path);
            if(!path.endsWith("pdf")) {
                BufferedReader bf = new BufferedReader(new FileReader(file));
                while ((inp = bf.readLine()) != null)
                    text += inp;
            }
            return text;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public class dpUpdate extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MainRepository.getService().getProfilePicApi(SharedPref.getString(getApplicationContext(),"user_name"),"0").enqueue(new Callback<status>() {
                @Override
                public void onResponse(Call<status> call, Response<status> response) {
                    status a=response.body();
                    if(a!=null) {
                        if (a.getCode()==1) {
                            String url = "http://analysed.in/analysed/Pages/jobseeker/" + a.getMessage();
                            Glide.with(dashboard.this).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap image, Transition<? super Bitmap> transition) {
                                    File storageDir = getApplicationContext().getExternalCacheDir();
                                    if (storageDir == null || !storageDir.exists()) {
                                        storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                                        if(!storageDir.exists())
                                            storageDir.mkdirs();
                                    }

                                    try {
                                        final File cacheFile = new File(storageDir, "profile.jpg");
                                        iv_dp.setImageBitmap(image);
                                        OutputStream fOut = new FileOutputStream(cacheFile);
                                        image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                                        fOut.close();
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh",t.getMessage());
                    t.printStackTrace();
                }
            });
            return null;
        }
    }

    public class ResumeUpdate extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MainRepository.getService().getResumeApi(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<status>() {
                @Override
                public void onResponse(Call<status> call, Response<status> response) {
                    final status a=response.body();
                    if(a!=null) {
                        if (a.getCode()==1) {
                            String url1="http://analysed.in/analysed/Pages/jobseeker/documents/" + a.getMessage();
                            Glide.with(dashboard.this).asFile().load(url1).into(new SimpleTarget<File>() {
                                @Override
                                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                    File storageDir = getApplicationContext().getExternalCacheDir();
                                    if (storageDir == null || !storageDir.exists()) {
                                        storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                                        if(!storageDir.exists())
                                            storageDir.mkdirs();
                                    }

                                    final File cacheFile = new File(storageDir,a.getMessage());
                                    try {
                                        FileInputStream inStream = new FileInputStream(resource);
                                        FileOutputStream outStream = new FileOutputStream(cacheFile);
                                        FileChannel inChannel = inStream.getChannel();
                                        FileChannel outChannel = outStream.getChannel();
                                        inChannel.transferTo(0, inChannel.size(), outChannel);
                                        inStream.close();
                                        outStream.close();

                                        SharedPref.putString(getApplicationContext(),"resume_file_name",a.getMessage());
                                        SharedPref.putString(getApplicationContext(),"resume_file_content",getFileContents(a.getMessage()));
                                    }
                                    catch (Exception e) {
                                        Log.d("ddlogesh", e.getMessage());
                                    }
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh",t.getMessage());
                    t.printStackTrace();
                }
            });
            return null;
        }
    }

    public class getProfile extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            MainRepository.getService().getProfileApi(SharedPref.getString(getApplicationContext(), "user_name"), "0").enqueue(new Callback<userDetails>() {
                @Override
                public void onResponse(Call<userDetails> call, Response<userDetails> response) {
                    final userDetails d = response.body();
                    if (d != null) {
                        SharedPref.putString(getApplicationContext(), "f_name", d.getFname());
                        SharedPref.putString(getApplicationContext(), "l_name", d.getLname());
                        SharedPref.putString(getApplicationContext(), "referal", d.getReferal());

                        String url = "http://analysed.in/analysed/Pages/jobseeker" + d.getPicture();
                        SharedPref.putInt(getApplicationContext(), "user_id", d.getUser_id());
                        SharedPref.putString(getApplicationContext(), "location", d.getLocation());
                        SharedPref.putString(getApplicationContext(), "phone", d.getPhNumber());
                        SharedPref.putString(getApplicationContext(),"qualification",d.getQualification());
                        SharedPref.putString(getApplicationContext(),"year_passing",d.getYearofpassing());
                        SharedPref.putString(getApplicationContext(),"experience",d.getExperience());
                        SharedPref.putString(getApplicationContext(),"resume_file_name",d.getResumename());
                        SharedPref.putString(getApplicationContext(),"resume_file_content",d.getResume());

                        Glide.with(dashboard.this).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap image, Transition<? super Bitmap> transition) {
                                File storageDir = getApplicationContext().getExternalCacheDir();
                                if (storageDir == null || !storageDir.exists()) {
                                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                                    if (!storageDir.exists())
                                        storageDir.mkdirs();
                                }

                                try {
                                    final File cacheFile = new File(storageDir, "profile.jpg");
                                    OutputStream fOut = new FileOutputStream(cacheFile);
                                    image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                                    fOut.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        String url1="http://analysed.in/analysed/Pages/jobseeker/documents/" + d.getResumename();
                        Glide.with(dashboard.this).asFile().load(url1).into(new SimpleTarget<File>() {
                            @Override
                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                File storageDir = getApplicationContext().getExternalCacheDir();
                                if (storageDir == null || !storageDir.exists()) {
                                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                                    if(!storageDir.exists())
                                        storageDir.mkdirs();
                                }

                                final File cacheFile = new File(storageDir,d.getResumename());
                                try {
                                    FileInputStream inStream = new FileInputStream(resource);
                                    FileOutputStream outStream = new FileOutputStream(cacheFile);
                                    FileChannel inChannel = inStream.getChannel();
                                    FileChannel outChannel = outStream.getChannel();
                                    inChannel.transferTo(0, inChannel.size(), outChannel);
                                    inStream.close();
                                    outStream.close();
                                }
                                catch (Exception e) {
                                    Log.d("ddlogesh", e.getMessage());
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<userDetails> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            return null;
        }
    }

    public class updates extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dpUpdate dp=new dpUpdate();
            ResumeUpdate rs=new ResumeUpdate();
            tv_name.setText(SharedPref.getString(getApplicationContext(),"f_name")+ " " + SharedPref.getString(getApplicationContext(),"l_name"));

            try {
                File storageDir = getApplicationContext().getExternalCacheDir();
                if (storageDir == null || !storageDir.exists()) {
                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                    if (!storageDir.exists())
                        storageDir.mkdirs();
                }
                File cacheFile = new File(storageDir, "profile.jpg");
                if(cacheFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getPath());
                    iv_dp.setImageBitmap(bitmap);
                }
                else
                    dp.execute();
            }
            catch (Exception e){
                dp.execute();
                e.printStackTrace();
                Log.d("ddlogesh","came02: "+e.getMessage());
            }

            try {
                File storageDir = getApplicationContext().getExternalCacheDir();
                if (storageDir == null || !storageDir.exists()) {
                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                    if (!storageDir.exists())
                        storageDir.mkdirs();
                }
                File cacheFile = new File(storageDir, SharedPref.getString(getApplicationContext(),"resume_file_name"));
                if(!cacheFile.exists())
                    rs.execute();
            }
            catch (Exception e){
                rs.execute();
                e.printStackTrace();
                Log.d("ddlogesh","came02: "+e.getMessage());
            }

            ListView l1 = (ListView) findViewById(R.id.list_slidermenu);
            listNavDrawer adapter = new listNavDrawer(dashboard.this, new ArrayList<drawer>());
            adapter.clear();
            adapter.add(new drawer("Dashboard", "nav_dashboard"));
            adapter.add(new drawer("Score Board", "nav_recruitment"));
            adapter.add(new drawer("Tasks", "nav_task"));
            adapter.add(new drawer("Skills", "nav_database"));
            adapter.add(new drawer("Events", "nav_sort_resume"));
            adapter.add(new drawer("Challenges", "nav_challenge"));
            adapter.add(new drawer("Portfolio", "nav_challenge"));
            adapter.add(new drawer("Share Profile", "nav_profile"));
            adapter.add(new drawer("Referral", "nav_referral"));
            adapter.add(new drawer("Sign Out", "nav_sign_out"));
            l1.setAdapter(adapter);

            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String data = ((logeshd.analysed.classes.drawer) parent.getItemAtPosition(position)).getTitle();
                    if (data.equals("Dashboard"))
                        drawer.closeDrawer((int) GravityCompat.START);
                    else if (data.equals("Score Board"))
                        startActivity(new Intent(getApplicationContext(), scoreBoard.class));
                    else if (data.equals("Tasks"))
                        startActivity(new Intent(getApplicationContext(), viewTasks.class));
                    else if (data.equals("Skills"))
                        startActivity(new Intent(getApplicationContext(), viewSkills.class));
                    else if (data.equals("Events"))
                        startActivity(new Intent(getApplicationContext(), events.class));
                    else if (data.equals("Challenges"))
                        startActivity(new Intent(getApplicationContext(), viewChallenges.class));
                    else if (data.equals("Portfolio"));
                        //startActivity(new Intent(getApplicationContext(), viewChallenges.class));
                    else if (data.equals("Share Profile"))
                        startActivity(new Intent(getApplicationContext(), shareProfile.class));
                    else if (data.equals("Referral"))
                        startActivity(new Intent(getApplicationContext(), referral.class));
                    else if (data.equals("Sign Out")) {
                        SharedPref.removeAll(getApplicationContext());
                        startActivity(new Intent(getApplicationContext(), login.class));
                    }
                }
            });

            try {
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                if (SharedPref.getString(getApplicationContext(), "is_today") != null) {
                    String d = SharedPref.getString(getApplicationContext(), "is_today");
                    Date old_date = sdf.parse(d);
                    if ((new Date().getTime() - old_date.getTime()) > 86400000) {
                        new getProfile().execute();
                        SharedPref.putString(getApplicationContext(), "is_today", sdf.format(new Date()));
                    }
                }
                else{
                    new getProfile().execute();
                    SharedPref.putString(getApplicationContext(), "is_today", sdf.format(new Date()));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
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
