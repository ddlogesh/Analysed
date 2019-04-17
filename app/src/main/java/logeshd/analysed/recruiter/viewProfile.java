package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import logeshd.analysed.R;
import logeshd.analysed.jobSeeker.completeProfile;

public class viewProfile extends AppCompatActivity implements View.OnClickListener {

    EditText ev_first_name1,ev_last_name1,ev_email,ev_organisation,ev_address,ev_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_view_profile);

        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        TextView tv_profession = (TextView) findViewById(R.id.tv_profession);
        TextView tv_first_name1 = (TextView) findViewById(R.id.tv_first_name1);
        ev_first_name1 = (EditText) findViewById(R.id.ev_first_name1);
        TextView tv_last_name1 = (TextView) findViewById(R.id.tv_last_name1);
        ev_last_name1 = (EditText) findViewById(R.id.ev_last_name1);
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        ev_email = (EditText) findViewById(R.id.ev_email);
        TextView tv_organisation = (TextView) findViewById(R.id.tv_organisation);
        ev_organisation = (EditText) findViewById(R.id.ev_organisation);
        TextView tv_address = (TextView) findViewById(R.id.tv_address);
        ev_address = (EditText) findViewById(R.id.ev_address);
        TextView tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        ev_mobile = (EditText) findViewById(R.id.ev_mobile);

        ImageView iv_first_name1 = (ImageView) findViewById(R.id.iv_first_name1);   iv_first_name1.setOnClickListener(this);
        ImageView iv_last_name1 = (ImageView) findViewById(R.id.iv_last_name1);     iv_last_name1.setOnClickListener(this);
        ImageView iv_organisation = (ImageView) findViewById(R.id.iv_organisation); iv_organisation.setOnClickListener(this);
        ImageView iv_address = (ImageView) findViewById(R.id.iv_address);           iv_address.setOnClickListener(this);
        ImageView iv_mobile = (ImageView) findViewById(R.id.iv_mobile);             iv_mobile.setOnClickListener(this);
        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);                 iv_home.setOnClickListener(this);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_name.setTypeface(custom_font2);
        tv_profession.setTypeface(custom_font1);
        tv_first_name1.setTypeface(custom_font3);
        ev_first_name1.setTypeface(custom_font1);
        tv_last_name1.setTypeface(custom_font3);
        ev_last_name1.setTypeface(custom_font1);
        tv_email.setTypeface(custom_font3);
        ev_email.setTypeface(custom_font1);
        tv_organisation.setTypeface(custom_font3);
        ev_organisation.setTypeface(custom_font1);
        tv_address.setTypeface(custom_font3);
        ev_address.setTypeface(custom_font1);
        tv_mobile.setTypeface(custom_font3);
        ev_mobile.setTypeface(custom_font1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home:
                startActivity(new Intent(getApplicationContext(),dashboard.class));
                break;
            case R.id.iv_first_name1:
                if(ev_first_name1.isEnabled()){
                    ev_first_name1.setEnabled(false);
                    Drawable img = getResources().getDrawable(R.drawable.ic_edit);
                    ev_first_name1.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);

                }
                else{
                    ev_first_name1.setEnabled(true);
                    Drawable img = getResources().getDrawable(R.drawable.ic_tick);
                    ev_first_name1.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                }
                break;
        }
    }
}
