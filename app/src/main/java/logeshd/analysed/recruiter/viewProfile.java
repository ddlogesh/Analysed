package logeshd.analysed.recruiter;

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
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import logeshd.analysed.common.profilePic;
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
    TextView tv_name,tv_profession;
    EditText ev_fname,ev_lname,ev_email,ev_organisation,ev_phone;
    CircleImageView iv_dp;
    ImageView iv_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_view_profile);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_profession = (TextView) findViewById(R.id.tv_profession);
        TextView tv_fname = (TextView) findViewById(R.id.tv_fname);
        ev_fname = (EditText) findViewById(R.id.ev_fname);
        TextView tv_lname = (TextView) findViewById(R.id.tv_lname);
        ev_lname = (EditText) findViewById(R.id.ev_lname);
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        ev_email = (EditText) findViewById(R.id.ev_email);
        TextView tv_organisation = (TextView) findViewById(R.id.tv_organisation);
        ev_organisation = (EditText) findViewById(R.id.ev_organisation);
        TextView tv_phone = (TextView) findViewById(R.id.tv_phone);
        ev_phone = (EditText) findViewById(R.id.ev_phone);

        ImageView iv_first_name1 = (ImageView) findViewById(R.id.iv_fname);         iv_first_name1.setOnClickListener(this);
        ImageView iv_last_name1 = (ImageView) findViewById(R.id.iv_lname);          iv_last_name1.setOnClickListener(this);
        ImageView iv_organisation = (ImageView) findViewById(R.id.iv_organisation); iv_organisation.setOnClickListener(this);
        ImageView iv_mobile = (ImageView) findViewById(R.id.iv_phone);              iv_mobile.setOnClickListener(this);
        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);                 iv_home.setOnClickListener(this);
        iv_edit = (ImageView) findViewById(R.id.iv_edit);                           iv_edit.setOnClickListener(this);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        iv_dp = (CircleImageView) findViewById(R.id.iv_dp);
        iv_dp.setBorderWidth(5);
        iv_dp.setBorderColor(Color.parseColor("#ff33adff"));

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_name.setTypeface(custom_font2);
        tv_profession.setTypeface(custom_font1);
        tv_fname.setTypeface(custom_font3);
        ev_fname.setTypeface(custom_font1);
        tv_lname.setTypeface(custom_font3);
        ev_lname.setTypeface(custom_font1);
        tv_email.setTypeface(custom_font3);
        ev_email.setTypeface(custom_font1);
        tv_organisation.setTypeface(custom_font3);
        ev_organisation.setTypeface(custom_font1);
        tv_phone.setTypeface(custom_font3);
        ev_phone.setTypeface(custom_font1);

        new updates().execute();
    }

    /************************************************************************************/

    public class updates extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            tv_name.setText(SharedPref.getString(getApplicationContext(),"f_name") + " " + SharedPref.getString(getApplicationContext(),"l_name"));
            tv_profession.setText(SharedPref.getString(getApplicationContext(),"designation"));
            ev_fname.setText(SharedPref.getString(getApplicationContext(),"f_name"));
            ev_lname.setText(SharedPref.getString(getApplicationContext(),"l_name"));
            ev_email.setText(SharedPref.getString(getApplicationContext(),"user_name"));
            ev_organisation.setText(SharedPref.getString(getApplicationContext(),"organisation"));
            ev_phone.setText(SharedPref.getString(getApplicationContext(),"phone"));

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
            RequestBody userRole = RequestBody.create(MediaType.parse("text/plain"), "1");
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", imageFile.getName(), fileReqBody);

            MainRepository.getService().uploadImageApi(part, userRole).enqueue(new Callback<status>() {
                @Override
                public void onResponse(Call<status> call, Response<status> response) {
                    status a = response.body();
                    if (a != null) {
                        if (a.getCode() == 1) {
                            Log.d("ddlogesh", a.getMessage());
                            MainRepository.getService().editProfileApi("picture","avatars/" + imageFile.getName(),"1", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
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
                startActivity(new Intent(getApplicationContext(),dashboard.class));
                break;
            case R.id.iv_edit:
                if ((ContextCompat.checkSelfPermission(viewProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(viewProfile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
                    startActivityForResult(CommonUtils.getPickImageChooserIntent(getApplicationContext()),200);
                else
                    ActivityCompat.requestPermissions(viewProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                break;
            default:
                final ImageView iv=(ImageView)v;
                int id=getResources().getIdentifier("ev_"+iv.getTag(),"id",getPackageName());
                final EditText et=findViewById(id);

                if(et.isEnabled()){
                    et.setEnabled(false);
                    Drawable img = getResources().getDrawable(R.drawable.ic_edit);
                    et.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);

                    MainRepository.getService().editProfileApi(iv.getTag().toString(),et.getEditableText().toString().trim(),"1", SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<String>() {
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
        }
    }
}
