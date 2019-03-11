package logeshd.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class aboutOrganization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_organization);

        TextView tv_title4=findViewById(R.id.tv_title4);
        TextView tv_profile_name=findViewById(R.id.tv_profile_name);
        TextView tv_company=findViewById(R.id.tv_company);
        TextView tv_designation=findViewById(R.id.tv_designation);

        TextView tv_title1=findViewById(R.id.tv_title1);
        TextView tv_time=findViewById(R.id.tv_time);
        TextView tv_profile_desc=findViewById(R.id.tv_profile_desc);

        TextView tv_title2=findViewById(R.id.tv_title2);
        TextView tv_title3=findViewById(R.id.tv_title3);
        TextView tv_link=findViewById(R.id.tv_link);

        TextView tv_hire=findViewById(R.id.tv_hire);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");

        tv_title4.setTypeface(custom_font3);
        tv_profile_name.setTypeface(custom_font3);
        tv_company.setTypeface(custom_font1);
        tv_designation.setTypeface(custom_font1);

        tv_title1.setTypeface(custom_font3);
        tv_time.setTypeface(custom_font2);
        tv_profile_desc.setTypeface(custom_font1);

        tv_title2.setTypeface(custom_font3);
        tv_title3.setTypeface(custom_font3);
        tv_link.setTypeface(custom_font2);

        tv_hire.setTypeface(custom_font3);
    }
}
