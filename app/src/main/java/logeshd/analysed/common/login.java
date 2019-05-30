package logeshd.analysed.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import logeshd.analysed.R;
import logeshd.analysed.apis.userDetails;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class login extends AppCompatActivity implements View.OnClickListener {

    AVLoadingIndicatorView pcircle;
    EditText ev_name, ev_password;
    TextView tv_forgot, tv_login, tv_terms, tv_signup, tv_tour, tv_privacy;
    ImageView iv_eye,iv_logo,iv_app1,iv_app2,iv_app3,iv_app4,iv_name,iv_password;
    RelativeLayout layout_social;

    static boolean flag_eye=true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_login);

        ev_name = findViewById(R.id.ev_name);
        ev_password = findViewById(R.id.ev_password);

        tv_forgot = findViewById(R.id.tv_forgot);   tv_forgot.setOnClickListener(this);
        tv_login = findViewById(R.id.tv_login);     tv_login.setOnClickListener(this);
        tv_terms = findViewById(R.id.tv_terms);     tv_terms.setOnClickListener(this);
        tv_signup = findViewById(R.id.tv_signup);   tv_signup.setOnClickListener(this);
        tv_tour = findViewById(R.id.tv_tour);       tv_tour.setOnClickListener(this);
        tv_privacy = findViewById(R.id.tv_privacy); tv_privacy.setOnClickListener(this);

        iv_eye = findViewById(R.id.iv_eye);         iv_eye.setOnClickListener(this);
        iv_logo = findViewById(R.id.iv_logo);

        pcircle = findViewById(R.id.pcircle);
    }

    /*******************************************************************/

    private class animations extends AsyncTask<Void,Void,Void>{
        Handler handler=new Handler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            iv_name = findViewById(R.id.iv_name);
            iv_password = findViewById(R.id.iv_password);

            iv_app1 = findViewById(R.id.iv_app1);
            iv_app2 = findViewById(R.id.iv_app2);
            iv_app3 = findViewById(R.id.iv_app3);
            iv_app4 = findViewById(R.id.iv_app4);

            layout_social = findViewById(R.id.layout_social);

            iv_logo.setVisibility(View.INVISIBLE);
            tv_login.setVisibility(View.INVISIBLE);
            layout_social.setVisibility(View.INVISIBLE);
            iv_app1.setVisibility(View.INVISIBLE);
            iv_app2.setVisibility(View.INVISIBLE);
            iv_app3.setVisibility(View.INVISIBLE);
            iv_app4.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    iv_logo.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.BounceIn).duration(500).playOn(iv_logo);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            tv_login.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.ZoomInUp).duration(500).playOn(tv_login);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    layout_social.setVisibility(View.VISIBLE);
                                    iv_app1.setVisibility(View.VISIBLE);
                                    iv_app2.setVisibility(View.VISIBLE);
                                    iv_app3.setVisibility(View.VISIBLE);
                                    iv_app4.setVisibility(View.VISIBLE);

                                    YoYo.with(Techniques.ZoomIn).duration(500).playOn(layout_social);
                                    YoYo.with(Techniques.BounceIn).duration(500).playOn(iv_app1);
                                    YoYo.with(Techniques.BounceIn).duration(550).playOn(iv_app2);
                                    YoYo.with(Techniques.BounceIn).duration(600).playOn(iv_app3);
                                    YoYo.with(Techniques.BounceIn).duration(650).playOn(iv_app4);
                                }
                            },500);
                        }
                    },500);
                }
            },100);

            return null;
        }
    }

    private void loginApi(String username, String password){

        MainRepository.getService().loginApi(username,password).enqueue(new Callback<userDetails>() {
            @Override
            public void onResponse(Call<userDetails> call, Response<userDetails> response) {
                final userDetails d=response.body();
                if(d!=null){
                    SharedPref.putString(getApplicationContext(),"user_name",ev_name.getEditableText().toString().trim());
                    SharedPref.putInt(getApplicationContext(),"user_role",Integer.parseInt(d.getUserRole()));
                    SharedPref.putInt(getApplicationContext(),"id",d.getId());
                    SharedPref.putString(getApplicationContext(),"f_name",d.getFname());
                    SharedPref.putString(getApplicationContext(),"l_name",d.getLname());
                    SharedPref.putString(getApplicationContext(),"referal",d.getReferal());

                    String url = null;
                    if (d.getUserRole().equals("1")) {
                        url = "http://analysed.in/analysed/Pages/" + d.getPicture();
                        SharedPref.putString(getApplicationContext(), "designation", d.getDesignation());
                        SharedPref.putString(getApplicationContext(), "organisation", d.getOrganisation());
                        SharedPref.putString(getApplicationContext(), "location", d.getAddress());
                        SharedPref.putString(getApplicationContext(),"phone",d.getPhone());
                    }
                    else {
                        url = "http://analysed.in/analysed/Pages/jobseeker/" + d.getPicture();
                        SharedPref.putInt(getApplicationContext(),"user_id",d.getUser_id());
                        SharedPref.putString(getApplicationContext(), "location", d.getLocation());
                        SharedPref.putString(getApplicationContext(),"phone",d.getPhNumber());

                        String str2 = d.getQualification();
                        if (str2.equals("UG"))
                            str2 = "Under graduate";
                        else if (str2.equals("PG"))
                            str2 = "Post graduate";
                        else if (str2.equals("PHD"))
                            str2 = "Ph.D";
                        SharedPref.putString(getApplicationContext(),"qualification",str2);

                        SharedPref.putString(getApplicationContext(),"year_passing",d.getYearofpassing());

                        String str1 = d.getExperience();
                        if (str1.equals("1"))
                            str1 = "0-2 Years";
                        else if (str1.equals("2"))
                            str1 = "2-4 Years";
                        else if (str1.equals("3"))
                            str1 = "4+ Years";
                        else
                            str1 = "0 Year";
                        SharedPref.putString(getApplicationContext(),"experience",str1);

                        SharedPref.putString(getApplicationContext(),"resume_file_name",d.getResumename());
                        SharedPref.putString(getApplicationContext(),"resume_file_content",d.getResume());

                        String url1="http://analysed.in/analysed/Pages/jobseeker/documents/" + d.getResumename();
                        Glide.with(login.this).asFile().load(url1).into(new SimpleTarget<File>() {
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

                    Glide.with(login.this).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
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
                                OutputStream fOut = new FileOutputStream(cacheFile);
                                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                                fOut.close();

                                SharedPref.removeAll(getApplicationContext(),"login");
                                SharedPref.removeAll(getApplicationContext(),"signup");
                                SharedPref.remove(getApplicationContext(),"profile_file_name");

                                SharedPref.putBoolean(getApplicationContext(),"is_logged_in",true);
                                pcircle.setVisibility(View.GONE);

                                startActivity(new Intent(getApplicationContext(), tour.class));
                                Bungee.inAndOut(login.this);
                            }
                            catch (Exception e){
                                e.printStackTrace();
                                pcircle.setVisibility(View.GONE);
                                tv_login.setEnabled(true);
                            }
                        }
                    });
                }
                else {
                    pcircle.setVisibility(View.GONE);
                    tv_login.setEnabled(true);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0001: Invalid Username or Password", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<userDetails> call, Throwable t) {
                pcircle.setVisibility(View.GONE);
                tv_login.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"\tERR-0002: Invalid Username or Password",R.drawable.ic_alert_white,"#ff0000",Color.WHITE);
            }
        });
    }

    /************************************************************************************/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED)
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease allow Analysed to access your Storage memory", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
        else if(startProgress())
            loginApi(ev_name.getEditableText().toString(), ev_password.getEditableText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        new animations().execute();

        if (SharedPref.getString(getApplicationContext(),"login","ev_name") != null)
            ev_name.setText(SharedPref.getString(getApplicationContext(),"login","ev_name"));
        if (SharedPref.getString(getApplicationContext(),"login","ev_password") != null)
            ev_password.setText(SharedPref.getString(getApplicationContext(),"login","ev_password"));

        SharedPref.removeAll(getApplicationContext(),"login");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forgot:
                SharedPref.removeAll(getApplicationContext(),"login");
                break;
            case R.id.tv_terms:
                if (ev_name.getEditableText().toString().trim().length() > 0)
                    SharedPref.putString(getApplicationContext(), "login", "ev_name", ev_name.getEditableText().toString().trim());
                if (ev_password.getEditableText().toString().trim().length() > 0)
                    SharedPref.putString(getApplicationContext(), "login", "ev_password", ev_password.getEditableText().toString().trim());
                SharedPref.putInt(getApplicationContext(),"back_flag",0);

                startActivity(new Intent(getApplicationContext(),terms.class));
                Bungee.slideUp(login.this);
                break;
            case R.id.tv_signup:
                SharedPref.removeAll(getApplicationContext(),"login");
                SharedPref.removeAll(getApplicationContext(),"signup");

                startActivity(new Intent(getApplicationContext(), signup1.class));
                Bungee.slideUp(login.this);
                break;
            case R.id.tv_tour:
                if (ev_name.getEditableText().toString().trim().length() > 0)
                    SharedPref.putString(getApplicationContext(),"login", "ev_name", ev_name.getEditableText().toString().trim());
                if (ev_password.getEditableText().toString().trim().length() > 0)
                    SharedPref.putString(getApplicationContext(),"login", "ev_password", ev_password.getEditableText().toString().trim());

                startActivity(new Intent(getApplicationContext(),takeTour.class));
                Bungee.slideLeft(login.this);
                break;
            case R.id.tv_privacy:
                if (ev_name.getEditableText().toString().trim().length() > 0)
                    SharedPref.putString(getApplicationContext(),"login", "ev_name", ev_name.getEditableText().toString().trim());
                if (ev_password.getEditableText().toString().trim().length() > 0)
                    SharedPref.putString(getApplicationContext(),"login", "ev_password", ev_password.getEditableText().toString().trim());
                SharedPref.putInt(getApplicationContext(),"back_flag",0);

                startActivity(new Intent(getApplicationContext(),privacy.class));
                Bungee.slideUp(login.this);
                break;
            case R.id.iv_eye:
                if(flag_eye) {
                    ev_password.setTransformationMethod(null);
                    ev_password.setSelection(ev_password.getEditableText().toString().trim().length());
                    iv_eye.setImageResource(R.drawable.ic_eye_hide);
                }
                else{
                    ev_password.setTransformationMethod(new PasswordTransformationMethod());
                    ev_password.setSelection(ev_password.getEditableText().toString().trim().length());
                    iv_eye.setImageResource(R.drawable.ic_eye_view);
                }
                flag_eye=!flag_eye;
                break;
            case R.id.tv_login:
                if(ev_name.getEditableText().toString().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tEmail id cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_name);
                }
                else if(ev_password.getEditableText().toString().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPassword cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_password);
                }
                else {
                    if ((ContextCompat.checkSelfPermission(login.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                        if(startProgress())
                            loginApi(ev_name.getEditableText().toString(), ev_password.getEditableText().toString());
                    }
                    else
                        ActivityCompat.requestPermissions(login.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                }
                break;
        }
    }

    /*******************************************************************/

    private Boolean startProgress(){
        CommonUtils.hideKeyboard(login.this);
        if(CommonUtils.alerter(getApplicationContext())) {
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease check your internet connection", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
            return false;
        }
        else {
            pcircle.setVisibility(View.VISIBLE);
            tv_login.setEnabled(false);
            return true;
        }
    }

    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
