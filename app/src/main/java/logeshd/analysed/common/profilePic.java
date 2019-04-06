package logeshd.analysed.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;

public class profilePic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_profile_pic);

        TextView b=(TextView)findViewById(R.id.cancel);
        TextView bt_ok=(TextView)findViewById(R.id.ok);

        final CropImageView mCropImageView = (CropImageView)findViewById(R.id.CropImageView);
        mCropImageView.setAspectRatio(1,1);

        Uri mCropImageUri=getIntent().getData();
        mCropImageView.setImageUriAsync(mCropImageUri);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePic.super.onBackPressed();
            }
        });

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File storageDir = getApplicationContext().getExternalCacheDir();
                if (storageDir == null || !storageDir.exists()) {
                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/.Analysed");
                    if(!storageDir.exists())
                        storageDir.mkdirs();
                }
                Bitmap bitmap=mCropImageView.getCroppedImage(500, 500);

                final File cacheFile = new File(storageDir,"profile.jpg");
                try {
                    OutputStream fOut = new FileOutputStream(cacheFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.close();
                    SharedPref.putString(getApplicationContext(),"profile_file_name","");
                }
                catch (Exception e) {
                    Log.d("ddlogesh", e.getMessage());
                }

                profilePic.super.onBackPressed();
            }
        });
    }
}