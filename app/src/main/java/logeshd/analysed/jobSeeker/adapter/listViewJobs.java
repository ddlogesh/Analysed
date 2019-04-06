package logeshd.analysed.jobSeeker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;
import logeshd.analysed.apis.joblistings;

public class listViewJobs extends ArrayAdapter<joblistings> {
    public listViewJobs(Context context, ArrayList<joblistings> history) {
        super(context, 0, history);
    }

    public View getView(int position, View cView, ViewGroup parent) {
        final joblistings p = (joblistings) getItem(position);
        if (cView == null)
            cView = LayoutInflater.from(getContext()).inflate(R.layout.r_list_job_listings, parent, false);

        final View convertView = cView;

        TextView tv_month = (TextView) convertView.findViewById(R.id.tv_month);
        TextView tv_day = (TextView) convertView.findViewById(R.id.tv_day);
        TextView tv_job_title = (TextView) convertView.findViewById(R.id.tv_job_title);
        TextView tv_location = (TextView) convertView.findViewById(R.id.tv_location);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.layout);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");

        tv_job_title.setTypeface(custom_font1);
        tv_location.setTypeface(custom_font2);

        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            Date d;
            if(p.getEndon() != null)
                d = format1.parse(p.getEndon());
            else
                d = format1.parse(p.getCreatedon());
            tv_month.setText((String)DateFormat.format("MMM",d));
            tv_day.setText((String)DateFormat.format("dd",d));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        tv_job_title.setText(p.getPosition());
        tv_location.setText(p.getJob_location());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = convertView.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.r_alert_job_listings, viewGroup, false);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                ImageView iv_cancel = (ImageView) dialogView.findViewById(R.id.iv_cancel);
                TextView tv_position = (TextView) dialogView.findViewById(R.id.tv_position);
                TextView tv_qualification = (TextView) dialogView.findViewById(R.id.tv_qualification);
                TextView tv_location = (TextView) dialogView.findViewById(R.id.tv_location);
                TextView tv_experience = (TextView) dialogView.findViewById(R.id.tv_experience);
                TextView tv_skill = (TextView) dialogView.findViewById(R.id.tv_skill);
                TextView tv_package= (TextView) dialogView.findViewById(R.id.tv_package);
                TextView tv_timings = (TextView) dialogView.findViewById(R.id.tv_timings);
                TextView tv_desc = (TextView) dialogView.findViewById(R.id.tv_desc);
                TextView tv_share = (TextView) dialogView.findViewById(R.id.tv_share);
                TextView tv_close = (TextView) dialogView.findViewById(R.id.tv_close);

                tv_position.setText(p.getPosition());
                tv_qualification.setText(p.getQual_req());
                tv_location.setText(p.getJob_location());
                tv_experience.setText(p.getExp_req());
                tv_skill.setText(p.getSkills_req());
                tv_package.setText(p.getPackages());
                tv_timings.setText(p.getJob_time());
                tv_desc.setText(p.getJob_description());

                if(p.getEndon()==null)
                    tv_close.setVisibility(View.VISIBLE);
                else
                    tv_close.setVisibility(View.GONE);

                iv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                tv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

        return convertView;
    }
}
