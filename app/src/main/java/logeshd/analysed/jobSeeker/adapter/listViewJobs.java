package logeshd.analysed.jobSeeker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.health.ServiceHealthStats;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import logeshd.analysed.R;
import logeshd.analysed.apis.joblistings;
import logeshd.analysed.apis.jobseekers;
import logeshd.analysed.common.login;
import logeshd.analysed.recruiter.createTask;
import logeshd.analysed.recruiter.jobListings;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.CommonUtils;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

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
                final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.r_alert_job_listings, viewGroup, false);

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
                final TextView tv_share = (TextView) dialogView.findViewById(R.id.tv_share);
                final TextView tv_close = (TextView) dialogView.findViewById(R.id.tv_close);

                tv_position.setText(p.getPosition());
                tv_qualification.setText(p.getQual_req());
                tv_location.setText(p.getJob_location());
                tv_experience.setText(p.getExp_req());
                tv_skill.setText(p.getSkills_req());
                tv_package.setText(p.getPackages());
                tv_timings.setText(p.getJob_time());
                tv_desc.setText(p.getJob_description());

                if (SharedPref.getBoolean(getContext(), "is_task")){
                    tv_share.setVisibility(View.VISIBLE);
                    tv_close.setVisibility(View.GONE);
                    tv_share.setText("Create Task");
                }
                else{
                    if(p.getEndon()==null) {
                        tv_share.setText("Share job");
                        tv_share.setVisibility(View.VISIBLE);
                        tv_close.setVisibility(View.VISIBLE);
                    }
                    else {
                        tv_share.setVisibility(View.GONE);
                        tv_close.setVisibility(View.GONE);
                    }
                }

                iv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                tv_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(tv_share.getText().equals("Create Task")){
                            tv_share.setEnabled(false);
                            MainRepository.getService().getJobseekers(p.getId()).enqueue(new Callback<List<jobseekers>>() {
                                @Override
                                public void onResponse(Call<List<jobseekers>> call, Response<List<jobseekers>> response) {
                                    List<jobseekers> dlist=response.body();
                                    if(dlist!=null && dlist.size()>0){
                                        SharedPref.putInt(getContext(),"seekers_count",dlist.size());
                                        for(int i=1;i<=dlist.size();i++){
                                            SharedPref.putString(getContext(),"seekers_name"+Integer.toString(i),dlist.get(i-1).getFname());
                                            SharedPref.putString(getContext(),"seekers_email"+Integer.toString(i),dlist.get(i-1).getEmail());
                                        }
                                        alertDialog.dismiss();
                                        getContext().startActivity(new Intent(getContext(), createTask.class));
                                    }
                                    else
                                        Toast.makeText(getContext(),"No job seekers applied", Toast.LENGTH_SHORT).show();
                                    tv_share.setEnabled(true);
                                }

                                @Override
                                public void onFailure(Call<List<jobseekers>> call, Throwable t) {
                                    Log.d("ddlogesh",t.getMessage());
                                    tv_share.setEnabled(true);
                                }
                            });
                        }
                        else{
                            //TODO:
                            //Share functionality
                        }
                    }
                });

                tv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainRepository.getService().closeJob(p.getId()).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        Toast.makeText(getContext(), "Job Closed!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(getContext(),jobListings.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                getContext().startActivity(intent);
                                Bungee.fade(getContext());
                            }
                        },1000);
                    }
                });
            }
        });

        return convertView;
    }
}
