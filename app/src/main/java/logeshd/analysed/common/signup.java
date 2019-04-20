package logeshd.analysed.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
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

public class signup extends AppCompatActivity implements View.OnClickListener{

    AVLoadingIndicatorView pcircle;
    CircleImageView iv_profile;
    EditText ev_email,ev_first_name1,ev_last_name1,ev_location,ev_mobile,ev_skills,ev_password1,ev_password2,ev_designation,ev_organisation;
    ImageView iv_job_seekers,iv_recruiter,iv_edit;
    TextView tab_login,tab_signup,tv_role,tv_signup,tv_terms1,tv_terms2,tv_tour,tv_resume;
    Spinner sp_qualification,sp_year_passing,sp_experience;
    int flag=-1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.c_signup);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        iv_profile=(CircleImageView)findViewById(R.id.iv_profile);
        tv_tour = (TextView) findViewById(R.id.tv_tour);        tv_tour.setOnClickListener(this);
        tv_resume = (TextView) findViewById(R.id.tv_resume);    tv_resume.setOnClickListener(this);
        tv_signup = (TextView) findViewById(R.id.tv_signup);    tv_signup.setOnClickListener(this);
        tv_terms1 = (TextView) findViewById(R.id.tv_terms1);
        tv_terms2 = (TextView) findViewById(R.id.tv_terms2);    tv_terms2.setOnClickListener(this);
        tab_login = (TextView) findViewById(R.id.tab_login);    tab_login.setOnClickListener(this);
        tab_signup = (TextView) findViewById(R.id.tab_signup);
        tv_role = (TextView) findViewById(R.id.tv_role);
        
        ev_first_name1 = (EditText) findViewById(R.id.ev_first_name1);
        ev_last_name1 = (EditText) findViewById(R.id.ev_last_name1);
        ev_email = (EditText) findViewById(R.id.ev_email);
        ev_location = (EditText) findViewById(R.id.ev_location);
        ev_mobile = (EditText) findViewById(R.id.ev_mobile);
        ev_skills = (EditText) findViewById(R.id.ev_skills);
        ev_password1 = (EditText) findViewById(R.id.ev_password1);
        ev_password2 = (EditText) findViewById(R.id.ev_password2);
        ev_designation = (EditText) findViewById(R.id.ev_designation);
        ev_organisation = (EditText) findViewById(R.id.ev_organisation);

        iv_job_seekers = (ImageView) findViewById(R.id.iv_job_seekers); iv_job_seekers.setOnClickListener(this);
        iv_recruiter = (ImageView) findViewById(R.id.iv_recruiter);     iv_recruiter.setOnClickListener(this);
        iv_edit = (ImageView) findViewById(R.id.iv_edit);               iv_edit.setOnClickListener(this);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        
        tv_tour.setTypeface(custom_font2);
        tv_signup.setTypeface(custom_font2);
        tab_login.setTypeface(custom_font2);
        tab_signup.setTypeface(custom_font2);
        tv_terms1.setTypeface(custom_font1);
        tv_terms2.setTypeface(custom_font1);
        tv_terms2.setTypeface(custom_font1);
        tv_role.setTypeface(custom_font2);
        
        ev_first_name1.setTypeface(custom_font1);
        ev_last_name1.setTypeface(custom_font1);
        ev_email.setTypeface(custom_font1);
        ev_location.setTypeface(custom_font1);
        ev_mobile.setTypeface(custom_font1);
        ev_password1.setTypeface(custom_font1);
        ev_password2.setTypeface(custom_font1);
        ev_designation.setTypeface(custom_font1);
        ev_organisation.setTypeface(custom_font1);

        new signUpUpdates().execute();

        tv_terms2.setPaintFlags(tv_terms2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_tour.setPaintFlags(tv_tour.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    /************************************************************************************/

    public class signUpUpdates extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            sp_qualification = (Spinner) findViewById(R.id.sp_qualification);
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("Under graduate");
            list1.add("Post graduate");
            list1.add("Ph.D");
            list1.add("Qualification");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup.this, R.layout.t_spinner_item_light_black, list1) {
                public int getCount() {
                    return 3;
                }
            };
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_qualification.setAdapter(adapter);
            sp_qualification.setSelection(3);
            sp_qualification.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 3) {
                        ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_year_passing = (Spinner) findViewById(R.id.sp_year_passing);
            ArrayList<String> list2 = new ArrayList<>();
            list2.add("1997");
            list2.add("1998");
            list2.add("1999");
            list2.add("2000");
            list2.add("2001");
            list2.add("2002");
            list2.add("2003");
            list2.add("2004");
            list2.add("2005");
            list2.add("2006");
            list2.add("2007");
            list2.add("2008");
            list2.add("2009");
            list2.add("2010");
            list2.add("2011");
            list2.add("2012");
            list2.add("2013");
            list2.add("2014");
            list2.add("2015");
            list2.add("2016");
            list2.add("2017");
            list2.add("2018");
            list2.add("2019");
            list2.add("2020");
            list2.add("2021");
            list2.add("Year Of Passing");
            ArrayAdapter adapter2 = new ArrayAdapter<String>(signup.this, R.layout.t_spinner_item_light_black, list2) {
                public int getCount() {
                    return 25;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_year_passing.setAdapter(adapter2);
            sp_year_passing.setSelection(25);
            sp_year_passing.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 25) {
                        ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_experience = (Spinner) findViewById(R.id.sp_experience);
            ArrayList<String> list3 = new ArrayList<>();
            list3.add("0 Year");
            list3.add("0-2 Years");
            list3.add("2-4 Years");
            list3.add("4+ Years");
            list3.add("Experience");
            adapter2 = new ArrayAdapter<String>(signup.this, R.layout.t_spinner_item_light_black, list3) {
                public int getCount() {
                    return 4;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_experience.setAdapter(adapter2);
            sp_experience.setSelection(4);
            sp_experience.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 4) {
                        ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

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

        final FilePickerDialog dialog = new FilePickerDialog(signup.this,properties);
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

    /************************************************************************************/

    private void uploadProfilePic() {
        iv_edit.setEnabled(false);
        pcircle.setVisibility(View.VISIBLE);

        File storageDir = getApplicationContext().getExternalCacheDir();
        if (storageDir == null || !storageDir.exists()) {
            storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
            if(!storageDir.exists())
                storageDir.mkdirs();
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
        final File imageFile = new File(storageDir,"IMG" + sdf.format(new Date()) + ".jpg");
        final File cacheFile = new File(storageDir,"profile.jpg");
        try {
            FileInputStream inStream = new FileInputStream(cacheFile);
            FileOutputStream outStream = new FileOutputStream(imageFile);
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inStream.close();
            outStream.close();
        }
        catch (Exception e) {
            Log.d("ddlogesh", e.getMessage());
        }

        if(imageFile.exists()) {
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            RequestBody userRole = RequestBody.create(MediaType.parse("text/plain"), Integer.toString(flag));
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", imageFile.getName(), fileReqBody);

            MainRepository.getService().uploadImageApi(part, userRole).enqueue(new Callback<status>() {
                @Override
                public void onResponse(Call<status> call, Response<status> response) {
                    status a = response.body();
                    if (a != null) {
                        if (a.getCode() == 1) {
                            Log.d("ddlogesh", a.getMessage());
                            if(flag==0)
                                SharedPref.putString(getApplicationContext(), "profile_file_name", "user-images/" + imageFile.getName());
                            else if(flag==1)
                                SharedPref.putString(getApplicationContext(), "profile_file_name", "avatars/" + imageFile.getName());

                            iv_profile.setBorderWidth(5);
                            iv_profile.setBorderColor(Color.parseColor("#ff33adff"));
                            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());
                            iv_profile.setImageBitmap(bitmap);

                            pcircle.setVisibility(View.GONE);
                            iv_edit.setEnabled(true);
                        }
                        else {
                            Log.d("ddlogesh", a.getMessage());
                            pcircle.setVisibility(View.GONE);
                            iv_edit.setEnabled(false);
                            CommonUtils.setSnackBar(getWindow().getDecorView(), "Profile picture uploading failed", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                        }
                    }
                    else {
                        Log.d("ddlogesh", "Failed");
                        pcircle.setVisibility(View.GONE);
                        iv_edit.setEnabled(false);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh", "Nope: " + t.getMessage());
                    pcircle.setVisibility(View.GONE);
                    iv_edit.setEnabled(false);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            });
        }
        else{
            Log.d("ddlogesh", "Failed");
            pcircle.setVisibility(View.GONE);
            iv_edit.setEnabled(false);
            CommonUtils.setSnackBar(getWindow().getDecorView(), "Profile picture uploading failed", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
        }
    }

    private void uploadResume(final String f){
        final File chosen_file=new File(f);
        if(chosen_file.exists()) {
            pcircle.setVisibility(View.VISIBLE);
            tv_resume.setEnabled(false);

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
                            SharedPref.putString(getApplicationContext(), "resume_file_name", chosen_file.getName());
                            SharedPref.putString(getApplicationContext(), "resume_file_content", getFileContents(f));

                            Drawable img = getResources().getDrawable(R.drawable.ic_tick_white);
                            tv_resume.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                            pcircle.setVisibility(View.GONE);

                            Log.d("ddlogesh", a.getMessage());
                        }
                        else {
                            Log.d("ddlogesh", "Failed");
                            pcircle.setVisibility(View.GONE);
                            tv_resume.setEnabled(true);
                            CommonUtils.setSnackBar(getWindow().getDecorView(), "Resume uploading failed, please use file size < 2MB", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                        }
                    }
                    else {
                        Log.d("ddlogesh", "Failed");
                        pcircle.setVisibility(View.GONE);
                        tv_resume.setEnabled(true);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh", "Nope: " + t.getMessage());
                    pcircle.setVisibility(View.GONE);
                    tv_resume.setEnabled(true);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            });
        }
        else{
            Log.d("ddlogesh", "Failed");
            CommonUtils.setSnackBar(getWindow().getDecorView(), "Resume uploading failed", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
        }
    }

    private void checkForSignupApi(users u){
        pcircle.setVisibility(View.VISIBLE);
        tv_signup.setEnabled(false);
        MainRepository.getService().checkForSignupApi(u).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    Log.d("ddlogesh", a.getMessage());
                    if(a.getMessage().equals("Successfully inserted")){
                        if(flag==0) {
                            jobseekers j = new jobseekers();
                            j.setUser_id(a.getCode());
                            j.setFname(ev_first_name1.getEditableText().toString().trim());
                            j.setLname(ev_last_name1.getEditableText().toString().trim());
                            j.setEmail(ev_email.getEditableText().toString().trim());
                            j.setLocation(ev_location.getEditableText().toString().trim());
                            j.setSkills(ev_skills.getEditableText().toString().trim());
                            j.setPhNumber(ev_mobile.getEditableText().toString().trim());
                            j.setYearofpassing(sp_year_passing.getSelectedItem().toString());
                            j.setPicture(SharedPref.getString(getApplicationContext(), "profile_file_name"));
                            j.setResumename(SharedPref.getString(getApplicationContext(), "resume_file_name"));
                            j.setResume(SharedPref.getString(getApplicationContext(), "resume_file_content"));

                            String str1 = sp_experience.getSelectedItem().toString();
                            if (str1.equals("0-2 Years"))
                                str1 = "1";
                            else if (str1.equals("2-4 Years"))
                                str1 = "2";
                            else if (str1.equals("4+ Years"))
                                str1 = "3";
                            else
                                str1 = "0";
                            j.setExperience(str1);

                            String str = sp_qualification.getSelectedItem().toString();
                            if (str.startsWith("U"))
                                str = "UG";
                            else if (str.equals("Ph.D"))
                                str = "PHD";
                            else
                                str = "PG";
                            j.setQualification(str);

                            signupJSApi(j);
                        }
                        else if(flag==1){
                            recruiter r = new recruiter();
                            r.setFname(ev_first_name1.getEditableText().toString().trim());
                            r.setLname(ev_last_name1.getEditableText().toString().trim());
                            r.setEmail(ev_email.getEditableText().toString().trim());
                            r.setPhone(ev_mobile.getEditableText().toString().trim());
                            r.setOrganisation(ev_organisation.getEditableText().toString().trim());
                            r.setDesignation(ev_designation.getEditableText().toString().trim());
                            r.setAddress(ev_location.getEditableText().toString().trim());
                            r.setPicture(SharedPref.getString(getApplicationContext(), "profile_file_name"));

                            signupRCApi(r);
                        }
                    }
                    else if(a.getMessage().equals("User already exists")) {
                        Log.d("ddlogesh", a.getMessage());
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Username already exists!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                        pcircle.setVisibility(View.GONE);
                        tv_signup.setEnabled(true);
                    }
                    else{
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                        pcircle.setVisibility(View.GONE);
                        tv_signup.setEnabled(true);
                    }
                }
                else {
                    pcircle.setVisibility(View.GONE);
                    tv_signup.setEnabled(true);
                    Log.d("ddlogesh", "Failed");
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
            }
        });
    }

    private void signupJSApi(final jobseekers j){
        MainRepository.getService().signupJSApi(j).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    if(a.getMessage().equals("Successfully inserted")){
                        SharedPref.putInt(getApplicationContext(),"user_id",j.getUser_id());
                        SharedPref.putString(getApplicationContext(),"user_name",j.getEmail());
                        SharedPref.putInt(getApplicationContext(),"user_role",flag);
                        SharedPref.putInt(getApplicationContext(),"id",j.getId());
                        SharedPref.putString(getApplicationContext(),"f_name",j.getFname());
                        SharedPref.putString(getApplicationContext(),"l_name",j.getLname());
                        SharedPref.putString(getApplicationContext(),"phone",j.getPhNumber());
                        SharedPref.putString(getApplicationContext(), "location", j.getLocation());
                        SharedPref.putString(getApplicationContext(), "referal", j.getReferal());
                        SharedPref.putString(getApplicationContext(),"qualification",sp_qualification.getSelectedItem().toString());
                        SharedPref.putString(getApplicationContext(),"year_passing",sp_year_passing.getSelectedItem().toString());
                        SharedPref.putString(getApplicationContext(),"experience",sp_experience.getSelectedItem().toString());

                        String[] keys={"profile_file_name","ev_email"};
                        for(int i=0;i<keys.length;i++)
                            SharedPref.remove(getApplicationContext(),keys[i]);

                        Log.d("ddlogesh", a.getMessage());

                        SharedPref.putBoolean(getApplicationContext(),"is_logged_in",true);
                        pcircle.setVisibility(View.GONE);

                        startActivity(new Intent(getApplicationContext(), tour.class));
                        Bungee.inAndOut(signup.this);
                    }
                    else {
                        Log.d("ddlogesh", a.getMessage());
                        tv_signup.setEnabled(true);
                        pcircle.setVisibility(View.GONE);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Sorry, error in signing-up!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }
                else {
                    Log.d("ddlogesh", "Failed");
                    tv_signup.setEnabled(true);
                    pcircle.setVisibility(View.GONE);
                    CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
            }
        });
    }

    private void signupRCApi(final recruiter r){
        MainRepository.getService().signupRCApi(r).enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null) {
                    if(a.getMessage().equals("Successfully inserted")){
                        SharedPref.putString(getApplicationContext(),"user_name",r.getEmail());
                        SharedPref.putInt(getApplicationContext(),"user_role",flag);
                        SharedPref.putInt(getApplicationContext(),"id",r.getId());
                        SharedPref.putString(getApplicationContext(),"f_name",r.getFname());
                        SharedPref.putString(getApplicationContext(),"l_name",r.getLname());
                        SharedPref.putString(getApplicationContext(),"phone",r.getPhone());
                        SharedPref.putString(getApplicationContext(), "location", r.getAddress());
                        SharedPref.putString(getApplicationContext(), "referal", r.getReferal());

                        String[] keys={"profile_file_name","ev_email"};
                        for(int i=0;i<keys.length;i++)
                            SharedPref.remove(getApplicationContext(),keys[i]);

                        Log.d("ddlogesh", a.getMessage());

                        SharedPref.putBoolean(getApplicationContext(),"is_logged_in",true);
                        pcircle.setVisibility(View.GONE);

                        startActivity(new Intent(getApplicationContext(), tour.class));
                        Bungee.inAndOut(signup.this);
                    }
                    else {
                        Log.d("ddlogesh", a.getMessage());
                        tv_signup.setEnabled(true);
                        pcircle.setVisibility(View.GONE);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Sorry, error in signing-up!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }
                else {
                    Log.d("ddlogesh", "Failed");
                    tv_signup.setEnabled(true);
                    pcircle.setVisibility(View.GONE);
                    CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
                pcircle.setVisibility(View.GONE);
                tv_signup.setEnabled(true);
                CommonUtils.setSnackBar(getWindow().getDecorView(),"Server error, please try again later!",R.drawable.ic_alert_white,"#ffa779c4",Color.WHITE);
            }
        });
    }

    /************************************************************************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tour:
                startActivity(new Intent(getApplicationContext(), takeTour.class));
                Bungee.slideRight(signup.this);
                break;

            case R.id.tab_login:
                startActivity(new Intent(getApplicationContext(), login.class));
                Bungee.slideLeft(signup.this);
                break;

            case R.id.tv_terms2:
                startActivity(new Intent(getApplicationContext(), terms.class));
                Bungee.slideUp(signup.this);
                break;

            case R.id.iv_edit:
                if ((ContextCompat.checkSelfPermission(signup.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(signup.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
                    startActivityForResult(CommonUtils.getPickImageChooserIntent(getApplicationContext()),200);
                else
                    ActivityCompat.requestPermissions(signup.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                break;

            case R.id.tv_resume:
                if ((ContextCompat.checkSelfPermission(signup.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(signup.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
                    filePicker();
                else
                    ActivityCompat.requestPermissions(signup.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 201);
                break;

            case R.id.iv_job_seekers:
                ((RelativeLayout) findViewById(R.id.layout_role)).setVisibility(View.GONE);
                ((ScrollView) findViewById(R.id.layout_scroll)).setVisibility(View.VISIBLE);
                ((RelativeLayout) findViewById(R.id.layout_js)).setVisibility(View.VISIBLE);
                ((RelativeLayout) findViewById(R.id.layout_rc)).setVisibility(View.GONE);
                tv_resume.setVisibility(View.VISIBLE);
                tv_signup.setEnabled(true);
                flag=0;
                break;

            case R.id.iv_recruiter:
                ((RelativeLayout) findViewById(R.id.layout_role)).setVisibility(View.GONE);
                ((ScrollView) findViewById(R.id.layout_scroll)).setVisibility(View.VISIBLE);
                ((RelativeLayout) findViewById(R.id.layout_js)).setVisibility(View.GONE);
                ((RelativeLayout) findViewById(R.id.layout_rc)).setVisibility(View.VISIBLE);
                ev_location.setHint("Address");
                tv_signup.setEnabled(true);
                flag=1;
                break;

            case R.id.tv_signup:
                if (ev_first_name1.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "First name cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (ev_last_name1.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Last name cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==1 && ev_organisation.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Organisation name cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==1 && ev_designation.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Designation cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==0 && (sp_qualification.getSelectedItem().toString()).equals("Qualification"))
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Select qualification", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==0 && (sp_year_passing.getSelectedItem().toString()).equals("Year Of Passing"))
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Select year of passing", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==0 && (sp_experience.getSelectedItem().toString()).equals("Experience"))
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Select experience", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (ev_email.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Email id cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==1 && (ev_email.getEditableText().toString().trim().contains("gmail") || ev_email.getEditableText().toString().trim().contains("yahoo")))
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Please use your business email", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (ev_location.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Location cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (ev_mobile.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Mobile number cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (flag==0 && ev_skills.getEditableText().toString().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Skills cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (ev_password1.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Password cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (ev_password2.getEditableText().toString().trim().length() == 0)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Confirm password cannot be empty", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if (!((ev_password1.getEditableText().toString().trim()).equals(ev_password2.getEditableText().toString().trim())))
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Password & confirm passwords should be the same", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if(flag==0 && SharedPref.getString(getApplicationContext(),"resume_file_name") == null)
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Please upload your resume", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if(SharedPref.getString(getApplicationContext(),"profile_file_name") == null || (!SharedPref.getString(getApplicationContext(),"profile_file_name").startsWith("user") && !SharedPref.getString(getApplicationContext(),"profile_file_name").startsWith("avatars")))
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Please upload your profile picture", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                else if(startProgress()) {
                    users u=new users(ev_email.getEditableText().toString().trim(),ev_password1.getEditableText().toString().trim(),Integer.toString(flag),1);
                    checkForSignupApi(u);
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ev_first_name1.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"f_name",ev_first_name1.getEditableText().toString().trim());
        if (ev_last_name1.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"l_name",ev_last_name1.getEditableText().toString().trim());

        if (ev_skills.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"skills",ev_skills.getEditableText().toString().trim());

        if (ev_designation.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"designation",ev_designation.getEditableText().toString().trim());
        if (ev_organisation.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"organisation",ev_organisation.getEditableText().toString().trim());

        if (ev_email.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"ev_email",ev_email.getEditableText().toString().trim());
        if (ev_location.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"location",ev_location.getEditableText().toString().trim());
        if (ev_mobile.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"phone",ev_mobile.getEditableText().toString().trim());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (SharedPref.getString(getApplicationContext(),"f_name") != null)
            ev_first_name1.setText(SharedPref.getString(getApplicationContext(),"f_name"));
        if (SharedPref.getString(getApplicationContext(),"l_name") != null)
            ev_last_name1.setText(SharedPref.getString(getApplicationContext(),"l_name"));

        if (SharedPref.getString(getApplicationContext(),"skills") != null)
            ev_skills.setText(SharedPref.getString(getApplicationContext(),"skills"));

        if (SharedPref.getString(getApplicationContext(),"designation") != null)
            ev_designation.setText(SharedPref.getString(getApplicationContext(),"designation"));
        if (SharedPref.getString(getApplicationContext(),"organisation") != null)
            ev_organisation.setText(SharedPref.getString(getApplicationContext(),"organisation"));

        if (SharedPref.getString(getApplicationContext(),"ev_email") != null)
            ev_email.setText(SharedPref.getString(getApplicationContext(),"ev_email"));
        if (SharedPref.getString(getApplicationContext(),"location") != null)
            ev_location.setText(SharedPref.getString(getApplicationContext(),"location"));
        if (SharedPref.getString(getApplicationContext(),"phone") != null)
            ev_mobile.setText(SharedPref.getString(getApplicationContext(),"phone"));

        String profile_status=SharedPref.getString(getApplicationContext(),"profile_file_name");
        if(profile_status != null){
            if(profile_status.equals(""))
                uploadProfilePic();
            else{
                iv_profile.setBorderWidth(5);
                iv_profile.setBorderColor(Color.parseColor("#ff33adff"));

                File storageDir = getApplicationContext().getExternalCacheDir();
                if (storageDir == null || !storageDir.exists()) {
                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                    if(!storageDir.exists())
                        storageDir.mkdirs();
                }
                File cacheFile = new File(storageDir,"profile.jpg");
                Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getPath());
                iv_profile.setImageBitmap(bitmap);
            }
        }

        String resume_status=SharedPref.getString(getApplicationContext(),"resume_file_name");
        if(resume_status != null){
            Drawable img = getResources().getDrawable(R.drawable.ic_tick_white);
            tv_resume.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }
    }

    @Override
    protected void onActivityResult(int  requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode==200) {
                Uri mCropImageUri = CommonUtils.getPickImageResultUri(data, getApplicationContext());
                startActivity(new Intent(getApplicationContext(), profilePic.class).setData(mCropImageUri));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED)
            CommonUtils.setSnackBar(getWindow().getDecorView(), "Please allow Analysed to access your Storage memory", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
        else {
            if(requestCode==200)
                startActivityForResult(CommonUtils.getPickImageChooserIntent(getApplicationContext()),200);
            else if(requestCode==201)
                filePicker();
        }
    }

    /************************************************************************************/

    private Boolean startProgress(){
        CommonUtils.hideKeyboard(signup.this);
        if(CommonUtils.alerter(getApplicationContext())) {
            CommonUtils.setSnackBar(getWindow().getDecorView(), "Please check your internet connection", R.drawable.ic_alert_white, "#ff0000", Color.BLACK);
            return false;
        }
        else {
            pcircle.setVisibility(View.VISIBLE);
            tv_signup.setEnabled(false);
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

/*
/var/www/analysed.in/analysed/Pages/jobseeker/documents

/var/www/analysed.in/analysed/Pages/jobseeker
/var/www/analysed.in/analysed/Pages
 */
