package logeshd.analysed.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

import logeshd.analysed.R;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.userDetails;
import logeshd.analysed.apis.users;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.service.MainService;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;
import spencerstudios.com.bungeelib.Bungee;

public class login extends AppCompatActivity {

    private AVLoadingIndicatorView pcircle;
    private EditText ev_name,ev_password;
    private TextView tab_login,tab_signup,tv_forgot,tv_login,tv_terms1,tv_terms2,tv_tour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_login);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        ev_name = (EditText) findViewById(R.id.ev_name);
        ev_password = (EditText) findViewById(R.id.ev_password);
        tv_tour = (TextView) findViewById(R.id.tv_tour);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tab_login = (TextView) findViewById(R.id.tab_login);
        tab_signup = (TextView) findViewById(R.id.tab_signup);
        tv_terms1 = (TextView) findViewById(R.id.tv_terms1);
        tv_terms2 = (TextView) findViewById(R.id.tv_terms2);
        
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        
        ev_name.setTypeface(custom_font3);
        ev_password.setTypeface(custom_font3);
        tv_tour.setTypeface(custom_font4);
        tv_login.setTypeface(custom_font4);
        tab_login.setTypeface(custom_font4);
        tab_signup.setTypeface(custom_font4);
        tv_forgot.setTypeface(custom_font4);
        tv_terms1.setTypeface(custom_font3);
        tv_terms2.setTypeface(custom_font3);

        tv_terms2.setPaintFlags(tv_terms2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_forgot.setPaintFlags(tv_forgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_tour.setPaintFlags(tv_tour.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        /************************************************************************************/
        
        tv_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ev_name.getEditableText().toString().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Email id cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if(ev_password.getEditableText().toString().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(),"Password cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else {
                    if ((ContextCompat.checkSelfPermission(login.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                        if(startProgress())
                            new loginProcess().execute();
                    }
                    else
                        ActivityCompat.requestPermissions(login.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                }
            }
        });

        /************************************************************************************/
        
        tv_tour.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),takeTour.class);
                startActivity(i1);
                Bungee.slideLeft(login.this);
            }
        });
        
        tab_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),signup.class);
                startActivity(i1);
                Bungee.slideRight(login.this);
            }
        });
        
        tv_terms2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),terms.class);
                startActivity(i1);
                Bungee.slideUp(login.this);
            }
        });
    }

    /*******************************************************************/

    public class loginProcess extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            loginApi(ev_name.getEditableText().toString(), ev_password.getEditableText().toString());
            return null;
        }
    }

    /*******************************************************************/

    private void loginApi(String username, String password){

        MainRepository.getService().loginApi(username,password).enqueue(new Callback<userDetails>() {
            @Override
            public void onResponse(Call<userDetails> call, Response<userDetails> response) {
                userDetails d=response.body();
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
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Invalid Username or Password", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<userDetails> call, Throwable t) {
                pcircle.setVisibility(View.GONE);
                tv_login.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
            }
        });
    }

    /************************************************************************************/

    private Boolean startProgress(){
        CommonUtils.hideKeyboard(login.this);
        if(CommonUtils.alerter(getApplicationContext())) {
            CommonUtils.setSnackBar(getWindow().getDecorView(), "Please check your internet connection", R.drawable.ic_alert_white, "#ff0000", Color.BLACK);
            return false;
        }
        else {
            pcircle.setVisibility(View.VISIBLE);
            tv_login.setEnabled(false);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED)
            CommonUtils.setSnackBar(getWindow().getDecorView(), "Please allow Analysed to access your Storage memory", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
        else if(startProgress())
            loginApi(ev_name.getEditableText().toString(), ev_password.getEditableText().toString());
    }

    /*******************************************************************/

    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
