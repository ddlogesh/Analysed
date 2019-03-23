package logeshd.analysed.recruiter.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.classes.recStatus;

public class listStatus extends ArrayAdapter<recStatus> {
    public listStatus(Context context, ArrayList<recStatus> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        recStatus p = (recStatus) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.r_list_status, parent, false);
        }

        TextView tv_title= convertView.findViewById(R.id.tv_title);
        TextView tv_sub_title= convertView.findViewById(R.id.tv_sub_title);
        TextView tv_live= convertView.findViewById(R.id.tv_live);
        TextView tv_completed= convertView.findViewById(R.id.tv_completed);
        TextView tv_progress= convertView.findViewById(R.id.tv_progress);
        ImageView iv_status= convertView.findViewById(R.id.iv_status);
        RoundCornerProgressBar pb_status = (RoundCornerProgressBar) convertView.findViewById(R.id.pb_status);

        RelativeLayout layout0= convertView.findViewById(R.id.layout0);
        RelativeLayout layout1= convertView.findViewById(R.id.layout1);
        RelativeLayout layout01= convertView.findViewById(R.id.layout01);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_bold.ttf");

        tv_title.setTypeface(custom_font2);
        tv_sub_title.setTypeface(custom_font1);
        tv_live.setTypeface(custom_font3);
        tv_completed.setTypeface(custom_font2);
        tv_progress.setTypeface(custom_font1);

        if(p.getLayoutStatus()){
            layout0.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.GONE);

            String[] colors = new String[]{"button_blue_solid", "button_blue_gradient"};
            layout01.setBackgroundResource(getContext().getResources().getIdentifier(colors[position % 2], "drawable", getContext().getPackageName()));

            tv_title.setText(p.getTitle());
            tv_sub_title.setText(p.getSubTitle());

            if(p.getStatus()==-1){
                tv_live.setVisibility(View.VISIBLE);
                iv_status.setVisibility(View.GONE);
            }
            else{
                tv_live.setVisibility(View.GONE);
                iv_status.setVisibility(View.VISIBLE);

                if(p.getStatus()==1)
                    iv_status.setImageResource(R.drawable.profile_apply_icon);
                else if(p.getStatus()==0)
                    iv_status.setImageResource(R.drawable.profile_apply_icon);  //cancel icon
            }
        }
        else{
            layout0.setVisibility(View.GONE);
            layout1.setVisibility(View.VISIBLE);

            tv_progress.setText(p.getProgress()+"%");
            pb_status.setProgress(p.getProgress());
        }

        return convertView;
    }
}
