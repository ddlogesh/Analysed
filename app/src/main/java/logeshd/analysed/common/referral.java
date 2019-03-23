package logeshd.analysed.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.common.adapter.listReferral;
import logeshd.analysed.classes.contact;
import logeshd.analysed.jobSeeker.dashboard;

public class referral extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.c_referral);

        ListView l1 = (ListView) findViewById(R.id.list_contact);
        final ArrayList<contact> arr = new ArrayList();
        final listReferral adapter = new listReferral(this, arr);
        adapter.clear();
        adapter.add(new contact("Logesh D", "9881294512", false));
        adapter.add(new contact("Kiran D", "9881294512", false));
        adapter.add(new contact("Sagar D", "9881294512", false));
        adapter.add(new contact("Vivek D", "9881294512", false));
        l1.setAdapter(adapter);

        ((TextView) findViewById(R.id.tv_invite)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout layout_contact = (RelativeLayout) referral.this.findViewById(R.id.layout_contact);
                RelativeLayout layout_code = (RelativeLayout) referral.this.findViewById(R.id.layout_code);
                layout_contact.setVisibility(View.VISIBLE);
                layout_code.setVisibility(View.GONE);
            }
        });

        ((ImageView) findViewById(R.id.iv_app5)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", "Hey check out Analysed at: https://play.google.com/store/apps/details?id=");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        ((TextView) findViewById(R.id.tv_close_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout layout_contact = (RelativeLayout) referral.this.findViewById(R.id.layout_contact);
                RelativeLayout layout_code = (RelativeLayout) referral.this.findViewById(R.id.layout_code);
                layout_contact.setVisibility(View.GONE);
                layout_code.setVisibility(View.VISIBLE);
            }
        });

        ((CheckBox) findViewById(R.id.ck_select_all)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < arr.size(); i++) {
                    ((contact) arr.get(i)).setStatus(isChecked);
                }
                adapter.notifyDataSetChanged();
            }
        });

        ((RelativeLayout) findViewById(R.id.layout_cancel)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });
    }
}
