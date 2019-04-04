package logeshd.analysed.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.wang.avi.AVLoadingIndicatorView;

import logeshd.analysed.R;
import logeshd.analysed.apis.users;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.service.MainService;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
                else if(startProgress())
                    new loginProcess().execute();
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
            loginApi(MainRepository.getService(), ev_name.getEditableText().toString(), ev_password.getEditableText().toString());
            return null;
        }
    }

    /*******************************************************************/

    private void loginApi(MainService service, String username, String password){

        service.loginApi(username,password).enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                users d=response.body();
                if(d!=null){
                    SharedPref.putInt(getApplicationContext(),"user_role",Integer.parseInt(d.getUserRole()));
                    SharedPref.putInt(getApplicationContext(),"user_id",d.getId());
                    SharedPref.putString(getApplicationContext(),"user_name",d.getName());
                    SharedPref.putBoolean(getApplicationContext(),"is_logged_in",true);
                    pcircle.setVisibility(View.GONE);

                    startActivity(new Intent(getApplicationContext(), tour.class));
                    Bungee.inAndOut(login.this);
                }
                else {
                    pcircle.setVisibility(View.GONE);
                    tv_login.setEnabled(true);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Invalid Username or Password", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
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

    /*******************************************************************/

    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
