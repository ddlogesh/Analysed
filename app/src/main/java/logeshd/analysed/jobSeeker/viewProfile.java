package logeshd.analysed.jobSeeker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Text;

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
import logeshd.analysed.apis.status;
import logeshd.analysed.common.profilePic;
import logeshd.analysed.common.signup;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewProfile extends AppCompatActivity implements View.OnClickListener {

    AVLoadingIndicatorView pcircle;
    TextView tv_name,tv_profession,tv_resume,tv_resume1,ev_Qualification,ev_yearofpassing,ev_Experience;
    EditText ev_fname,ev_lname,ev_email,ev_location,ev_PhNumber;
    Spinner sp_Qualification,sp_yearofpassing,sp_Experience;
    CircleImageView iv_dp;
    ImageView iv_edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_view_profile);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_profession = (TextView) findViewById(R.id.tv_profession);
        tv_resume = (TextView) findViewById(R.id.tv_resume);        tv_resume.setOnClickListener(this);
        tv_resume1 = (TextView) findViewById(R.id.tv_resume1);      tv_resume1.setOnClickListener(this);
        TextView tv_text1 = (TextView) findViewById(R.id.tv_text1); tv_text1.setOnClickListener(this);
        TextView tv_progress = (TextView) findViewById(R.id.tv_progress);

        TextView tv_fname = (TextView) findViewById(R.id.tv_fname);
        ev_fname = (EditText) findViewById(R.id.ev_fname);
        TextView tv_lname = (TextView) findViewById(R.id.tv_lname);
        ev_lname = (EditText) findViewById(R.id.ev_lname);
        TextView tv_Qualification = (TextView) findViewById(R.id.tv_Qualification);
        ev_Qualification = (TextView) findViewById(R.id.ev_Qualification);
        TextView tv_yearofpassing = (TextView) findViewById(R.id.tv_yearofpassing);
        ev_yearofpassing = (TextView) findViewById(R.id.ev_yearofpassing);
        TextView tv_Experience = (TextView) findViewById(R.id.tv_Experience);
        ev_Experience = (TextView) findViewById(R.id.ev_Experience);
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        ev_email = (EditText) findViewById(R.id.ev_email);
        TextView tv_location = (TextView) findViewById(R.id.tv_location);
        ev_location = (EditText) findViewById(R.id.ev_location);
        TextView tv_PhNumber = (TextView) findViewById(R.id.tv_PhNumber);
        ev_PhNumber = (EditText) findViewById(R.id.ev_PhNumber);

        TextView tv_saved_count = (TextView) findViewById(R.id.tv_saved_count);
        TextView tv_title2 = (TextView) findViewById(R.id.tv_title2);
        TextView tv_applied_count = (TextView) findViewById(R.id.tv_applied_count);
        TextView tv_title3 = (TextView) findViewById(R.id.tv_title3);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_name.setTypeface(custom_font2);
        tv_profession.setTypeface(custom_font1);
        tv_text1.setTypeface(custom_font1);
        tv_progress.setTypeface(custom_font1);
        tv_fname.setTypeface(custom_font2);
        ev_fname.setTypeface(custom_font1);
        tv_lname.setTypeface(custom_font2);
        ev_lname.setTypeface(custom_font1);
        tv_Qualification.setTypeface(custom_font2);
        ev_Qualification.setTypeface(custom_font1);
        tv_yearofpassing.setTypeface(custom_font2);
        ev_yearofpassing.setTypeface(custom_font1);
        tv_Experience.setTypeface(custom_font2);
        ev_Experience.setTypeface(custom_font1);
        tv_email.setTypeface(custom_font2);
        ev_email.setTypeface(custom_font1);
        tv_location.setTypeface(custom_font2);
        ev_location.setTypeface(custom_font1);
        tv_PhNumber.setTypeface(custom_font2);
        tv_name.setTypeface(custom_font1);
        tv_saved_count.setTypeface(custom_font3);
        tv_title2.setTypeface(custom_font2);
        tv_applied_count.setTypeface(custom_font3);
        tv_title3.setTypeface(custom_font2);

        ImageView iv_first_name1 = (ImageView) findViewById(R.id.iv_fname);             iv_first_name1.setOnClickListener(this);
        ImageView iv_last_name1 = (ImageView) findViewById(R.id.iv_lname);              iv_last_name1.setOnClickListener(this);
        ImageView iv_Qualification = (ImageView) findViewById(R.id.iv_Qualification);   iv_Qualification.setOnClickListener(this);
        ImageView iv_yearofpassing = (ImageView) findViewById(R.id.iv_yearofpassing);     iv_yearofpassing.setOnClickListener(this);
        ImageView iv_Experience = (ImageView) findViewById(R.id.iv_Experience);         iv_Experience.setOnClickListener(this);
        ImageView iv_location = (ImageView) findViewById(R.id.iv_location);             iv_location.setOnClickListener(this);
        ImageView iv_PhNumber = (ImageView) findViewById(R.id.iv_PhNumber);                 iv_PhNumber.setOnClickListener(this);
        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);                     iv_home.setOnClickListener(this);
        iv_edit = (ImageView) findViewById(R.id.iv_edit);                               iv_edit.setOnClickListener(this);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        iv_dp = (CircleImageView) findViewById(R.id.iv_dp);
        iv_dp.setBorderWidth(5);
        iv_dp.setBorderColor(Color.parseColor("#ff33adff"));

        new updates().execute();
    }

    /************************************************************************************/

    public class updates extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            tv_name.setText(SharedPref.getString(getApplicationContext(),"f_name") + " " + SharedPref.getString(getApplicationContext(),"l_name"));
            tv_profession.setText(SharedPref.getString(getApplicationContext(),"qualification"));
            ev_fname.setText(SharedPref.getString(getApplicationContext(),"f_name"));
            ev_lname.setText(SharedPref.getString(getApplicationContext(),"l_name"));
            ev_Qualification.setText(SharedPref.getString(getApplicationContext(),"qualification"));
            ev_yearofpassing.setText(SharedPref.getString(getApplicationContext(),"year_passing"));
            ev_Experience.setText(SharedPref.getString(getApplicationContext(),"experience"));
            ev_email.setText(SharedPref.getString(getApplicationContext(),"user_name"));
            ev_location.setText(SharedPref.getString(getApplicationContext(),"location"));
            ev_PhNumber.setText(SharedPref.getString(getApplicationContext(),"phone"));

            sp_Qualification = (Spinner) findViewById(R.id.sp_Qualification);
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("Under graduate");
            list1.add("Post graduate");
            list1.add("Ph.D");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(viewProfile.this, R.layout.t_spinner_item_light_black, list1) {
                public int getCount() {
                    return 3;
                }
            };
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_Qualification.setAdapter(adapter);
            sp_Qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_yearofpassing = (Spinner) findViewById(R.id.sp_yearofpassing);
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
            ArrayAdapter adapter2 = new ArrayAdapter<String>(viewProfile.this, R.layout.t_spinner_item_light_black, list2) {
                public int getCount() {
                    return 25;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_yearofpassing.setAdapter(adapter2);
            sp_yearofpassing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_Experience = (Spinner) findViewById(R.id.sp_Experience);
            ArrayList<String> list3 = new ArrayList<>();
            list3.add("0 Year");
            list3.add("0-2 Years");
            list3.add("2-4 Years");
            list3.add("4+ Years");
            adapter2 = new ArrayAdapter<String>(viewProfile.this, R.layout.t_spinner_item_light_black, list3) {
                public int getCount() {
                    return 4;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_Experience.setAdapter(adapter2);
            sp_Experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            return null;
        }
    }

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
            RequestBody userRole = RequestBody.create(MediaType.parse("text/plain"), "0");
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", imageFile.getName(), fileReqBody);

            MainRepository.getService().uploadImageApi(part, userRole).enqueue(new Callback<status>() {
                @Override
                public void onResponse(Call<status> call, Response<status> response) {
                    status a = response.body();
                    if (a != null) {
                        if (a.getCode() == 1) {
                            Log.d("ddlogesh", a.getMessage());
                            MainRepository.getService().editProfileApi("picture","user-images/" + imageFile.getName(),"0", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                            SharedPref.remove(getApplicationContext(), "profile_file_name");

                            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());
                            iv_dp.setImageBitmap(bitmap);

                            pcircle.setVisibility(View.GONE);
                            iv_edit.setEnabled(true);
                        }
                        else {
                            Log.d("ddlogesh", a.getMessage());
                            pcircle.setVisibility(View.GONE);
                            iv_edit.setEnabled(true);
                            CommonUtils.setSnackBar(getWindow().getDecorView(), "Profile picture uploading failed", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                        }
                    }
                    else {
                        Log.d("ddlogesh", "Failed");
                        pcircle.setVisibility(View.GONE);
                        iv_edit.setEnabled(true);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh", "Nope: " + t.getMessage());
                    pcircle.setVisibility(View.GONE);
                    iv_edit.setEnabled(true);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "Server error, please try again later!", R.drawable.ic_alert_white, "#ffa779c4", Color.WHITE);
                }
            });
        }
        else{
            Log.d("ddlogesh", "Failed");
            pcircle.setVisibility(View.GONE);
            iv_edit.setEnabled(true);
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
                            MainRepository.getService().editProfileApi("resumename",chosen_file.getName(),"0", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                            MainRepository.getService().editProfileApi("resume",getFileContents(f),"0", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });

                            SharedPref.putString(getApplicationContext(), "resume_file_name", chosen_file.getName());
                            SharedPref.putString(getApplicationContext(), "resume_file_content", getFileContents(f));

                            pcircle.setVisibility(View.GONE);

                            CommonUtils.setSnackBar(getWindow().getDecorView(), "Resume uploading success", R.drawable.ic_tick_white, "#ffa779c4", Color.WHITE);
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

    public void filePicker(){
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.STORAGE_DIR);
        properties.error_dir = new File(DialogConfigs.STORAGE_DIR);
        properties.offset = new File(DialogConfigs.STORAGE_DIR);
        properties.extensions = new String[]{"doc","docx","odt","pdf","txt","rtf"};

        final FilePickerDialog dialog = new FilePickerDialog(viewProfile.this,properties);
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

    public void downloadResume(){
        File storageDir = getApplicationContext().getExternalCacheDir();
        if (storageDir == null || !storageDir.exists()) {
            storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
            if(!storageDir.exists())
                storageDir.mkdirs();
        }

        File storageDir1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Analysed");
        if(!storageDir1.exists())
            storageDir1.mkdirs();

        final File cacheFile = new File(storageDir,SharedPref.getString(getApplicationContext(),"resume_file_name"));
        final File downloadFile = new File(storageDir1,SharedPref.getString(getApplicationContext(),"resume_file_name"));
        try {
            FileInputStream inStream = new FileInputStream(cacheFile);
            FileOutputStream outStream = new FileOutputStream(downloadFile);
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inStream.close();
            outStream.close();

            CommonUtils.setSnackBar(getWindow().getDecorView(), "Resume downloaded successfully", R.drawable.ic_tick_white, "#ffa779c4", Color.WHITE);
        }
        catch (Exception e) {
            Log.d("ddlogesh", e.getMessage());
        }
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

    @Override
    protected void onResume() {
        super.onResume();

        String profile_status=SharedPref.getString(getApplicationContext(),"profile_file_name");
        if(profile_status != null){
            if(profile_status.equals(""))
                uploadProfilePic();
        }
        else{
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
            }
            catch (Exception e){
                e.printStackTrace();
                Log.d("ddlogesh","Nope: "+e.getMessage());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home:
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                break;
            case R.id.iv_edit:
                if ((ContextCompat.checkSelfPermission(viewProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(viewProfile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
                    startActivityForResult(CommonUtils.getPickImageChooserIntent(getApplicationContext()),200);
                else
                    ActivityCompat.requestPermissions(viewProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                break;
            case R.id.tv_resume:
                if ((ContextCompat.checkSelfPermission(viewProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(viewProfile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
                    filePicker();
                else
                    ActivityCompat.requestPermissions(viewProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 201);
                break;
            case R.id.tv_resume1:
                downloadResume();
                break;
            case R.id.tv_text1:
                startActivity(new Intent(getApplicationContext(),completeProfile.class));
                break;
            default:
                final ImageView iv=(ImageView)v;
                
                if(iv.getTag().equals("Qualification") || iv.getTag().equals("yearofpassing") || iv.getTag().equals("Experience")) {
                    int sp_id = getResources().getIdentifier("sp_" + iv.getTag(), "id", getPackageName());
                    int ev_id = getResources().getIdentifier("ev_" + iv.getTag(), "id", getPackageName());
                    final Spinner sp=findViewById(sp_id);
                    final TextView tv=findViewById(ev_id);

                    String key="";
                    if(iv.getTag().toString().equals("Qualification"))
                        key="qualification";
                    else if(iv.getTag().toString().equals("yearofpassing"))
                        key="year_passing";
                    else if(iv.getTag().toString().equals("Experience"))
                        key="experience";

                    if(sp.getVisibility()==View.VISIBLE){
                        sp.setVisibility(View.GONE);
                        tv.setText(sp.getSelectedItem().toString());
                        Drawable img = getResources().getDrawable(R.drawable.ic_edit);
                        tv.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                        
                        String selected=sp.getSelectedItem().toString();
                        if (selected.equals("0-2 Years"))
                            selected = "1";
                        else if (selected.equals("2-4 Years"))
                            selected = "2";
                        else if (selected.equals("4+ Years"))
                            selected = "3";
                        else if (selected.equals("0 Year"))
                            selected = "0";
                        else if (selected.equals("Under graduate")) {
                            tv_profession.setText(selected);
                            selected = "UG";
                        }
                        else if (selected.equals("Post graduate")) {
                            tv_profession.setText(selected);
                            selected = "PG";
                        }
                        else if (selected.equals("Ph.D")) {
                            tv_profession.setText(selected);
                            selected = "PHD";
                        }

                        SharedPref.putString(getApplicationContext(),key,sp.getSelectedItem().toString());

                        MainRepository.getService().editProfileApi(iv.getTag().toString(),selected,"0", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                    else{
                        sp.setVisibility(View.VISIBLE);
                        tv.setText("");
                        Drawable img = getResources().getDrawable(R.drawable.ic_tick);
                        tv.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    }
                }
                else {
                    int id = getResources().getIdentifier("ev_" + iv.getTag(), "id", getPackageName());
                    final EditText et=findViewById(id);
                    
                    if(et.isEnabled()){
                        et.setEnabled(false);
                        Drawable img = getResources().getDrawable(R.drawable.ic_edit);
                        et.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);

                        MainRepository.getService().editProfileApi(iv.getTag().toString(),et.getEditableText().toString().trim(),"0", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        String key="";
                        if(iv.getTag().toString().equals("fname"))
                            key="f_name";
                        else if(iv.getTag().toString().equals("lname"))
                            key="l_name";
                        else
                            key=iv.getTag().toString();
                        SharedPref.putString(getApplicationContext(),key,et.getEditableText().toString());

                        if((iv.getTag().toString().equals("fname")) || (iv.getTag().toString().equals("lname")))
                            tv_name.setText(SharedPref.getString(getApplicationContext(),"f_name") + " " + SharedPref.getString(getApplicationContext(),"l_name"));
                    }
                    else{
                        et.setEnabled(true);
                        Drawable img = getResources().getDrawable(R.drawable.ic_tick);
                        et.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    }
                }
                break;
        }
    }

    /************************************************************************************/

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
}
