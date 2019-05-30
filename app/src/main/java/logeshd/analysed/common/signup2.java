package logeshd.analysed.common;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;
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

public class signup2 extends AppCompatActivity implements View.OnClickListener {

    AVLoadingIndicatorView pcircle;
    CircleImageView iv_profile;
    EditText ev_email,ev_first_name1,ev_last_name1,ev_password;
    ImageView iv_edit,iv_back,iv_eye,iv_first_name1,iv_last_name1,iv_email,iv_password;
    TextView tv_total,tv_upload_text,tv_next;

    Dialog dialog;

    int flag;
    static boolean flag_eye=true;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup2);

        flag = SharedPref.getInt(getApplicationContext(),"signup","user_role");

        ev_first_name1 = findViewById(R.id.ev_first_name1);
        ev_last_name1 = findViewById(R.id.ev_last_name1);
        ev_email = findViewById(R.id.ev_email);
        ev_password = findViewById(R.id.ev_password);

        tv_total = findViewById(R.id.tv_total);
        tv_upload_text = findViewById(R.id.tv_upload_text);
        tv_next = findViewById(R.id.tv_next);           tv_next.setOnClickListener(this);

        iv_back = findViewById(R.id.iv_back);           iv_back.setOnClickListener(this);
        iv_edit = findViewById(R.id.iv_edit);           iv_edit.setOnClickListener(this);
        iv_eye = findViewById(R.id.iv_eye);             iv_eye.setOnClickListener(this);
        iv_profile = findViewById(R.id.iv_profile);

        pcircle = findViewById(R.id.pcircle);

        if(flag==0) {
            tv_total.setText("3");
            tv_upload_text.setText("Upload a profile picture");
            iv_profile.setImageResource(R.drawable.ic_user);
        }
        else {
            tv_total.setText("2");
            tv_upload_text.setText("Upload a company logo");
            iv_profile.setImageResource(R.drawable.ic_bulb);
        }
    }

    /************************************************************************************/

    private class animations extends AsyncTask<Void,Void,Void> {
        Handler handler=new Handler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            iv_first_name1 = findViewById(R.id.iv_first_name1);
            iv_last_name1 = findViewById(R.id.iv_last_name1);
            iv_email = findViewById(R.id.iv_email);
            iv_password = findViewById(R.id.iv_password);

            tv_next.setVisibility(View.INVISIBLE);
            iv_profile.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    iv_profile.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.BounceIn).duration(800).playOn(iv_profile);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            tv_next.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.SlideInUp).duration(500).playOn(tv_next);
                        }
                    },500);
                }
            },300);

            return null;
        }
    }

    private void uploadProfilePic() {
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

                            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());
                            iv_profile.setImageBitmap(bitmap);

                            pcircle.setVisibility(View.GONE);
                            iv_edit.setEnabled(true);
                            iv_profile.setEnabled(true);
                            tv_next.setEnabled(true);
                        }
                        else {
                            Log.d("ddlogesh", a.getMessage());
                            pcircle.setVisibility(View.GONE);
                            iv_edit.setEnabled(true);
                            iv_profile.setEnabled(true);
                            tv_next.setEnabled(true);
                            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tProfile picture uploading failed", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                        }
                    }
                    else {
                        Log.d("ddlogesh", "Failed");
                        pcircle.setVisibility(View.GONE);
                        iv_edit.setEnabled(true);
                        iv_profile.setEnabled(true);
                        tv_next.setEnabled(true);
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tServer error, please try again later!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                    }
                }

                @Override
                public void onFailure(Call<status> call, Throwable t) {
                    Log.d("ddlogesh", "Nope: " + t.getMessage());
                    pcircle.setVisibility(View.GONE);
                    iv_edit.setEnabled(true);
                    iv_profile.setEnabled(true);
                    tv_next.setEnabled(true);
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tServer error, please try again later!", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
                }
            });
        }
        else{
            Log.d("ddlogesh", "Failed");
            pcircle.setVisibility(View.GONE);
            iv_edit.setEnabled(true);
            iv_profile.setEnabled(true);
            tv_next.setEnabled(true);
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tProfile picture uploading failed", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
        }
    }

    /************************************************************************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_next:
                if (ev_first_name1.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tFirst name cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_first_name1);
                }
                else if (ev_last_name1.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tLast name cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_last_name1);
                }
                else if (ev_email.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tEmail id cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_email);
                }
                else if (flag==1 && (ev_email.getEditableText().toString().trim().contains("gmail") || ev_email.getEditableText().toString().trim().contains("yahoo"))) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease use your business email", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_email);
                }
                else if (ev_password.getEditableText().toString().trim().length() == 0) {
                    CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPassword cannot be empty", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Swing).duration(500).playOn(iv_password);
                }
                else if(SharedPref.getString(getApplicationContext(),"profile_file_name") == null || (!SharedPref.getString(getApplicationContext(),"profile_file_name").startsWith("user") && !SharedPref.getString(getApplicationContext(),"profile_file_name").startsWith("avatars"))){
                    if(flag==0)
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease upload your profile picture", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    else
                        CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease upload your company logo", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
                    YoYo.with(Techniques.Tada).duration(500).playOn(iv_profile);
                }
                else {
                    if(flag==0)
                        startActivity(new Intent(getApplicationContext(), signup3_j.class));
                    else
                        startActivity(new Intent(getApplicationContext(), signup3_r.class));
                    Bungee.slideLeft(signup2.this);
                }
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
            case R.id.iv_profile:
            case R.id.iv_edit:
                if ((ContextCompat.checkSelfPermission(signup2.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(signup2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
                    startActivityForResult(CommonUtils.getPickImageChooserIntent(getApplicationContext()),200);
                else
                    ActivityCompat.requestPermissions(signup2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                break;

            case R.id.tv_ok:
                SharedPref.removeAll(getApplicationContext(),"signup");
                SharedPref.removeAll(getApplicationContext(),"login");

                startActivity(new Intent(getApplicationContext(), login.class));
                Bungee.slideDown(signup2.this);
                break;
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ev_first_name1.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","f_name",ev_first_name1.getEditableText().toString().trim());
        if (ev_last_name1.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","l_name",ev_last_name1.getEditableText().toString().trim());

        if (ev_email.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_email",ev_email.getEditableText().toString().trim());
        if (ev_password.getEditableText().toString().trim().length() > 0)
            SharedPref.putString(getApplicationContext(),"signup","ev_password",ev_password.getEditableText().toString().trim());
    }

    @Override
    protected void onResume() {
        super.onResume();

        new animations().execute();

        if (SharedPref.getString(getApplicationContext(),"signup","f_name") != null)
            ev_first_name1.setText(SharedPref.getString(getApplicationContext(),"signup","f_name"));
        if (SharedPref.getString(getApplicationContext(),"signup","l_name") != null)
            ev_last_name1.setText(SharedPref.getString(getApplicationContext(),"signup","l_name"));

        if (SharedPref.getString(getApplicationContext(),"signup","ev_email") != null)
            ev_email.setText(SharedPref.getString(getApplicationContext(),"signup","ev_email"));
        if (SharedPref.getString(getApplicationContext(),"signup","ev_password") != null)
            ev_password.setText(SharedPref.getString(getApplicationContext(),"signup","ev_password"));

        String profile_status=SharedPref.getString(getApplicationContext(),"profile_file_name");
        if(profile_status != null){
            if(profile_status.equals("")) {
                if (startProgress())
                    uploadProfilePic();
            }
            else{
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
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease allow Analysed to access your Storage memory", R.drawable.ic_alert_white, "#21242C", Color.WHITE);
        else {
            if(requestCode==200)
                startActivityForResult(CommonUtils.getPickImageChooserIntent(getApplicationContext()),200);
        }
    }

    @Override
    public void onBackPressed() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.t_alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView iv_icon = dialog.findViewById(R.id.iv_icon);

        TextView tv_ok = dialog.findViewById(R.id.tv_ok);           tv_ok.setOnClickListener(this);
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);   tv_cancel.setOnClickListener(this);
        TextView tv_title = dialog.findViewById(R.id.tv_title);
        TextView tv_message = dialog.findViewById(R.id.tv_message);

        tv_title.setText("Exit");
        tv_message.setText("Your changes will be lost and cannot be undone, would you like to continue?");
        tv_ok.setText("Yes, go back!");
        tv_ok.setBackgroundResource(R.drawable.button_red_solid_less_corner);

        iv_icon.setImageResource(R.drawable.ic_exit_white);
        iv_icon.setBackgroundResource(R.drawable.circle_red_solid);

        dialog.show();
    }

    /************************************************************************************/

    private Boolean startProgress(){
        CommonUtils.hideKeyboard(signup2.this);
        if(CommonUtils.alerter(getApplicationContext())) {
            CommonUtils.setSnackBar(getWindow().getDecorView(), "\tPlease check your internet connection", R.drawable.ic_alert_white, "#ff0000", Color.WHITE);
            return false;
        }
        else {
            pcircle.setVisibility(View.VISIBLE);
            iv_edit.setEnabled(false);
            iv_profile.setEnabled(false);
            tv_next.setEnabled(false);
            return true;
        }
    }
}


/*
/var/www/analysed.in/analysed/Pages/jobseeker/documents
/var/www/analysed.in/analysed/Pages/jobseeker
/var/www/analysed.in/analysed/Pages
*/
