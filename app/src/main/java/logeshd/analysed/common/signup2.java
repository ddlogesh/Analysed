package logeshd.analysed.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;

public class signup2 extends AppCompatActivity {

    AVLoadingIndicatorView pcircle;
    CircleImageView iv_profile;
    EditText ev_email,ev_first_name1,ev_last_name1,ev_location,ev_mobile,ev_skills,ev_password1,ev_password2,ev_designation,ev_organisation;
    ImageView iv_job_seekers,iv_recruiter,iv_edit;
    TextView tab_login,tab_signup,tv_role,tv_signup,tv_terms1,tv_terms2,tv_tour,tv_resume;
    Spinner sp_qualification,sp_year_passing,sp_experience;
    int flag=-1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup2);

    }


}

/*
/var/www/analysed.in/analysed/Pages/jobseeker/documents

/var/www/analysed.in/analysed/Pages/jobseeker
/var/www/analysed.in/analysed/Pages
 */
