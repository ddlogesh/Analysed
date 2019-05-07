package logeshd.analysed.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.channels.FileChannel;

import logeshd.analysed.R;
import logeshd.analysed.apis.jobseekers;
import logeshd.analysed.apis.recruiter;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.users;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class signup4_j extends AppCompatActivity implements View.OnClickListener {

    AVLoadingIndicatorView pcircle;
    TextView tv_terms,tv_privacy,tv_signup,tv_resume,tv_file_name;
    ImageView iv_back,iv_upload_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup4_j);

        tv_terms = findViewById(R.id.tv_terms);         tv_terms.setOnClickListener(this);
        tv_privacy = findViewById(R.id.tv_privacy);     tv_privacy.setOnClickListener(this);
        tv_signup = findViewById(R.id.tv_signup);       tv_signup.setOnClickListener(this);
        tv_resume = findViewById(R.id.tv_resume);       tv_resume.setOnClickListener(this);
        tv_file_name = findViewById(R.id.tv_file_name);

        iv_back = findViewById(R.id.iv_back);           iv_back.setOnClickListener(this);
        iv_upload_status = findViewById(R.id.iv_upload_status);
        pcircle = findViewById(R.id.pcircle);
    }

    /************************************************************************************/

    private class animations extends AsyncTask<Void,Void,Void> {
        Handler handler=new Handler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            tv_resume.setVisibility(View.INVISIBLE);
            iv_upload_status.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    iv_upload_status.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeInDown).duration(800).playOn(iv_upload_status);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            tv_resume.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.BounceIn).duration(500).playOn(tv_resume);
                        }
                    },500);
                }
            },300);

            return null;
        }
    }

    public void filePicker(){
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.STORAGE_DIR);
        properties.error_dir = new File(DialogConfigs.STORAGE_DIR);
        properties.offset = new File(DialogConfigs.STORAGE_DIR);
        properties.extensions = new String[]{"doc","docx","odt","pdf","txt","rtf"};

        final FilePickerDialog dialog = new FilePickerDialog(signup4_j.this,properties);
        dialog.setPositiveBtnName("DONE");
        dialog.setTitle("Choose a File");
        dialog.show();

        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                uploadResume(files[0]);
            }
        });
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

    private void isResume(){
        if(SharedPref.getString(getApplicationContext(), "signup","resume_file_name") == null){
            YoYo.with(Techniques.ZoomOutDown).duration(500).playOn(tv_signup);
            tv_signup.setEnabled(false);

            tv_resume.setText("Upload Resume");
            tv_resume.setBackgroundResource(R.drawable.button_green_solid);

            tv_file_name.setVisibility(View.GONE);
            iv_upload_status.setImageResource(R.drawable.resume_upload);
        }
        else{
            tv_resume.setText("Remove Resume");
            tv_resume.setBackgroundResource(R.drawable.button_red_solid);

            tv_file_name.setVisibility(View.VISIBLE);
            iv_upload_status.setImageResource(R.drawable.resume_remove);

            tv_signup.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.ZoomInUp).duration(500).playOn(tv_signup);
            tv_signup.setEnabled(true);
        }
    }

    /************************************************************************************/

    private void uploadResume(final String f){
        final File chosen_file=new File(f);
        if(chosen_file.exists()) {
            tv_signup.setEnabled(false);
            tv_resume.setEnabled(false);
            pcircle.setVisibility(View.VISIBLE);

            File storageDir = getApplicationContext().getExternalCacheDir();
            if (storageDir == null || !storageDir.exists()) {
                storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                if(!storageDir.exists())
                    storageDir.mkdirs();
            }

            final File cacheFile = new File(storageDir,chosen_file.getName());
            try {
                FileInputStream inStream = new FileInputStream(chosen_file);
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

            RequestBody fileReqBody = RequestBody.create(MediaType.parse("*/*"), chosen_file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", chosen_file.getName(), fileReqBody);

            MainRepository.getService().uploadResumeApi(part).enqueue(new Callback<status>() {
                @Override
                public void onResponse(Call<status> call, Response<status> response) {
                    status a = response.body();
                    if (a != null) {
                        if (a.getCode() == 1) {
                            SharedPref.putString(getApplicationContext(), "signup","resume_file_name", chosen_file.getName());
                            SharedPref.putString(getApplicationContext(), "signup", "resume_file_content", getFileContents(f));

                            tv_file_name.setText(chosen_file.getName());
                            isResume();

                            Log.d("ddlogesh", a.getMessage());
                            pcircle.setVisibility(View.GONE);
                            tv_resume.setEnabled(true);
                            tv_signup.setEnabled(true);
                        }
                        else {
                            Log.d("ddlogesh", "Failed");
                            pcircle.setVisibility(View.GONE);
                            tv_resume.setEnabled(true);
                            tv_signup.setEnabled(true);
                            CommonUtils.setSnackBar(getWindow().getDecorView(), "Resume uploading failed, please use file size < 2MB", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                        }
                    }
                    else {
                        Log.d("ddlogesh", "Failed");
                        pcircle.setVisibility(View.GONE);
                        tv_resume.setEnabled(true);
                        tv_signup.setEnabled(true);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh", "Nope: " + t.getMessage());
                    pcircle.setVisibility(View.GONE);
                    tv_resume.setEnabled(true);
                    tv_signup.setEnabled(true);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            });
        }
        else{
            Log.d("ddlogesh", "Failed");
            CommonUtils.setSnackBar(getWindow().getDecorView(), "File doesn't exist!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
        }
    }

    private void checkForSignupApi(users u){
        pcircle.setVisibility(View.VISIBLE);

        MainRepository.getService().checkForSignupApi(u).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    Log.d("ddlogesh", a.getMessage());
                    if(a.getMessage().equals("Successfully inserted")){
                        jobseekers j = new jobseekers();
                        j.setUser_id(a.getCode());
                        j.setFname(SharedPref.getString(getApplicationContext(),"signup","f_name"));
                        j.setLname(SharedPref.getString(getApplicationContext(),"signup","l_name"));
                        j.setEmail(SharedPref.getString(getApplicationContext(),"signup","ev_email"));
                        j.setLocation(SharedPref.getString(getApplicationContext(),"signup","ev_location"));
                        j.setSkills(SharedPref.getString(getApplicationContext(),"signup","ev_skills"));
                        j.setPhNumber(SharedPref.getString(getApplicationContext(),"signup","ev_mobile"));
                        j.setYearofpassing(SharedPref.getString(getApplicationContext(),"signup","sp_year_passing"));
                        j.setPicture(SharedPref.getString(getApplicationContext(), "profile_file_name"));
                        j.setResumename(SharedPref.getString(getApplicationContext(),"signup", "resume_file_name"));
                        j.setResume(SharedPref.getString(getApplicationContext(),"signup","resume_file_content"));

                        String str1 = SharedPref.getString(getApplicationContext(),"signup","sp_experience");
                        if (str1.equals("0-2 Years"))
                            str1 = "1";
                        else if (str1.equals("2-4 Years"))
                            str1 = "2";
                        else if (str1.equals("4+ Years"))
                            str1 = "3";
                        else
                            str1 = "0";
                        j.setExperience(str1);

                        String str = SharedPref.getString(getApplicationContext(),"signup","sp_qualification");
                        if (str.startsWith("U"))
                            str = "UG";
                        else if (str.equals("Ph.D"))
                            str = "PHD";
                        else
                            str = "PG";
                        j.setQualification(str);

                        signupApi(j);
                    }
                    else if(a.getMessage().equals("User already exists")) {
                        Log.d("ddlogesh", a.getMessage());
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0102: Email id already exists!", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                        pcircle.setVisibility(View.GONE);
                        tv_signup.setEnabled(true);
                        tv_resume.setEnabled(true);
                    }
                    else{
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0103: Server error, please try again later!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                        pcircle.setVisibility(View.GONE);
                        tv_signup.setEnabled(true);
                        tv_resume.setEnabled(true);
                    }
                }
                else {
                    pcircle.setVisibility(View.GONE);
                    tv_signup.setEnabled(true);
                    tv_resume.setEnabled(true);
                    Log.d("ddlogesh", "Failed");
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tERR-0104: Server error, please try again later!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                tv_resume.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ff0000",Color.WHITE);
            }
        });
    }

    private void signupApi(final jobseekers j){
        MainRepository.getService().signupJSApi(j).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    if(a.getMessage().equals("Successfully inserted")){
                        SharedPref.putInt(getApplicationContext(),"user_id",j.getUser_id());
                        SharedPref.putString(getApplicationContext(),"user_name",j.getEmail());
                        SharedPref.putInt(getApplicationContext(),"user_role",SharedPref.getInt(getApplicationContext(),"signup","user_role"));
                        SharedPref.putInt(getApplicationContext(),"id",j.getId());
                        SharedPref.putString(getApplicationContext(),"f_name",j.getFname());
                        SharedPref.putString(getApplicationContext(),"l_name",j.getLname());
                        SharedPref.putString(getApplicationContext(),"phone",j.getPhNumber());
                        SharedPref.putString(getApplicationContext(), "location", j.getLocation());
                        SharedPref.putString(getApplicationContext(), "referal", j.getReferal());
                        SharedPref.putString(getApplicationContext(), "resume_file_name", j.getResumename());
                        SharedPref.putString(getApplicationContext(), "resume_file_content", j.getResume());
                        SharedPref.putString(getApplicationContext(),"qualification",SharedPref.getString(getApplicationContext(),"signup","sp_qualification"));
                        SharedPref.putString(getApplicationContext(),"year_passing",SharedPref.getString(getApplicationContext(),"signup","sp_year_passing"));
                        SharedPref.putString(getApplicationContext(),"experience",SharedPref.getString(getApplicationContext(),"signup","sp_experience"));

                        SharedPref.remove(getApplicationContext(),"profile_file_name");
                        SharedPref.removeAll(getApplicationContext(),"signup");
                        SharedPref.removeAll(getApplicationContext(),"login");

                        Log.d("ddlogesh", a.getMessage());

                        SharedPref.putBoolean(getApplicationContext(),"is_logged_in",true);
                        pcircle.setVisibility(View.GONE);

                        startActivity(new Intent(getApplicationContext(), tour.class));
                        Bungee.inAndOut(signup4_j.this);
                    }
                    else {
                        Log.d("ddlogesh", a.getMessage());
                        tv_signup.setEnabled(true);
                        tv_resume.setEnabled(true);
                        pcircle.setVisibility(View.GONE);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Sorry, error in signing-up!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }
                else {
                    Log.d("ddlogesh", "Failed");
                    tv_signup.setEnabled(true);
                    tv_resume.setEnabled(true);
                    pcircle.setVisibility(View.GONE);
                    CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                tv_resume.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
            }
        });
    }

    /************************************************************************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_terms:
                SharedPref.putInt(getApplicationContext(),"back_flag",1);
                startActivity(new Intent(getApplicationContext(),terms.class));
                Bungee.slideUp(signup4_j.this);
                break;
            case R.id.tv_privacy:
                SharedPref.putInt(getApplicationContext(),"back_flag",1);
                startActivity(new Intent(getApplicationContext(),privacy.class));
                Bungee.slideUp(signup4_j.this);
                break;
            case R.id.tv_resume:
                if(tv_resume.getText().toString().equals("Upload Resume")){
                    if ((ContextCompat.checkSelfPermission(signup4_j.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(signup4_j.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                        if(startProgress())
                            filePicker();
                    }
                    else
                        ActivityCompat.requestPermissions(signup4_j.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 201);
                }
                else{
                    SharedPref.remove(getApplicationContext(),"signup","resume_file_name");
                    SharedPref.remove(getApplicationContext(),"signup","resume_file_content");
                    isResume();
                }
                break;
            case R.id.tv_signup:
                if(startProgress()){
                    users u=new users(SharedPref.getString(getApplicationContext(),"signup","ev_email"),SharedPref.getString(getApplicationContext(),"signup","ev_password"),Integer.toString(SharedPref.getInt(getApplicationContext(),"signup","user_role")),1);
                    checkForSignupApi(u);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new animations().execute();
        isResume();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), signup3_j.class));
        Bungee.slideRight(signup4_j.this);
    }

    /************************************************************************************/

    private Boolean startProgress(){
        CommonUtils.hideKeyboard(signup4_j.this);
        if(CommonUtils.alerter(getApplicationContext())) {
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease check your internet connection", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
            return false;
        }
        else
            return true;
    }
}
