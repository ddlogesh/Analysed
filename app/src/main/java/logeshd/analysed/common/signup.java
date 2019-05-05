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

public class signup extends AppCompatActivity {

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

    }
}

/*
/var/www/analysed.in/analysed/Pages/jobseeker/documents

/var/www/analysed.in/analysed/Pages/jobseeker
/var/www/analysed.in/analysed/Pages
 */
