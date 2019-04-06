package logeshd.analysed.recruiter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;
import logeshd.analysed.apis.databases;
import logeshd.analysed.classes.database;

public class listDatabase extends ArrayAdapter<databases> {
    public listDatabase(Context context, ArrayList<databases> history) {
        super(context, 0, history);
    }

    public View getView(int position, View cView, ViewGroup parent) {
        final databases p = (databases) getItem(position);
        if (cView == null) {
            cView = LayoutInflater.from(getContext()).inflate(R.layout.r_list_database, parent, false);
        }
        final View convertView = cView;

        View v_line = convertView.findViewById(R.id.v_line);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_position = (TextView) convertView.findViewById(R.id.tv_position);
        //TextView tv_view = (TextView) convertView.findViewById(R.id.tv_view);
        CircleImageView iv_profile = (CircleImageView) convertView.findViewById(R.id.iv_profile);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        tv_name.setTypeface(custom_font1);
        tv_position.setTypeface(custom_font1);
        //tv_view.setTypeface(custom_font2);

        tv_name.setText(p.getFname() + " " + p.getLname());
        tv_position.setText(p.getPosition());

        RequestOptions rp=new RequestOptions().centerCrop().placeholder(R.drawable.icon_profile).error(R.drawable.icon_profile);
        Glide.with(getContext()).load("http://analysed.in/analysed/Pages/jobseeker/"+p.getPicture()).apply(rp).into(iv_profile);

        if (position == 0)
            v_line.setVisibility(View.GONE);
        else
            v_line.setVisibility(View.VISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = convertView.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.r_alert_database, viewGroup, false);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                CircleImageView iv_profile = (CircleImageView) dialogView.findViewById(R.id.iv_profile);
                ImageView iv_cancel = (ImageView) dialogView.findViewById(R.id.iv_cancel);
                TextView tv_name = (TextView) dialogView.findViewById(R.id.tv_name);
                TextView tv_email = (TextView) dialogView.findViewById(R.id.tv_email);
                TextView tv_qualification = (TextView) dialogView.findViewById(R.id.tv_qualification);
                TextView tv_year_passing = (TextView) dialogView.findViewById(R.id.tv_year_passing);
                TextView tv_experience = (TextView) dialogView.findViewById(R.id.tv_experience);
                TextView tv_skill = (TextView) dialogView.findViewById(R.id.tv_skill);
                TextView tv_hire = (TextView) dialogView.findViewById(R.id.tv_hire);

                RequestOptions rp=new RequestOptions().centerCrop().placeholder(R.drawable.icon_profile).error(R.drawable.icon_profile);
                Glide.with(getContext()).load("http://analysed.in/analysed/Pages/jobseeker/"+p.getPicture()).apply(rp).into(iv_profile);
                tv_name.setText(p.getFname() + " " + p.getLname());
                tv_email.setText(p.getEmail());
                tv_qualification.setText(p.getQualification());
                tv_year_passing.setText(p.getYearofpassing());
                tv_experience.setText(p.getExperience() + " year(s)");
                tv_skill.setText(p.getSkills());

                iv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

        return convertView;
    }
}
