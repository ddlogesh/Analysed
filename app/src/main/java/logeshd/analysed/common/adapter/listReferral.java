package logeshd.analysed.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.classes.contact;

public class listReferral extends ArrayAdapter<contact> {
    public listReferral(Context context, ArrayList<contact> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        contact p = (contact) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.c_list_referral, parent, false);
        }

        TextView tv_full_name= convertView.findViewById(R.id.tv_full_name);
        TextView tv_mobile= convertView.findViewById(R.id.tv_mobile);

        tv_full_name.setText(p.getName());
        tv_mobile.setText(p.getMobile());

        String[] colors = new String[]{"contact_blue_solid", "contact_green_solid", "contact_red_solid", "contact_violet_solid", "contact_yellow_solid"};
        TextView tv_first_name = (TextView) convertView.findViewById(R.id.tv_first_name);
        tv_first_name.setText(p.getName().substring(0, 1));
        tv_first_name.setBackgroundResource(getContext().getResources().getIdentifier(colors[position % 4], "drawable", getContext().getPackageName()));

        CheckBox ck_select = convertView.findViewById(R.id.ck_select);
        ck_select.setChecked(p.getStatus());

        View v_line = convertView.findViewById(R.id.v_line);
        if (position == 0) {
            v_line.setVisibility(View.GONE);
        }
        else {
            v_line.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
