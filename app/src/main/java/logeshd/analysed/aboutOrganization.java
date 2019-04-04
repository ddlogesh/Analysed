package logeshd.analysed;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import logeshd.analysed.recruiter.dashboard;

public class aboutOrganization extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_about_organization);

        TextView tv_title4 = (TextView) findViewById(R.id.tv_title4);
        TextView tv_profile_name = (TextView) findViewById(R.id.tv_profile_name);
        TextView tv_company = (TextView) findViewById(R.id.tv_company);
        TextView tv_designation = (TextView) findViewById(R.id.tv_designation);
        TextView tv_title1 = (TextView) findViewById(R.id.tv_title1);
        TextView tv_profile_desc = (TextView) findViewById(R.id.tv_profile_desc);
        TextView tv_title2 = (TextView) findViewById(R.id.tv_title2);
        TextView tv_title3 = (TextView) findViewById(R.id.tv_title3);
        TextView tv_link = (TextView) findViewById(R.id.tv_link);
        TextView tv_hire = (TextView) findViewById(R.id.tv_hire);
        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");

        tv_title4.setTypeface(custom_font2);
        tv_profile_name.setTypeface(custom_font3);
        tv_company.setTypeface(custom_font1);
        tv_designation.setTypeface(custom_font1);
        tv_title1.setTypeface(custom_font3);
        tv_profile_desc.setTypeface(custom_font1);
        tv_title2.setTypeface(custom_font3);
        tv_title3.setTypeface(custom_font3);
        tv_link.setTypeface(custom_font2);
        tv_hire.setTypeface(custom_font2);

        iv_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });
    }
}
