package logeshd.analysed.common;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import logeshd.analysed.R;

public class signup3_j extends AppCompatActivity {

    AVLoadingIndicatorView pcircle;
    Spinner sp_qualification,sp_year_passing,sp_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_signup3_j);

        pcircle = findViewById(R.id.pcircle);

        new signUpUpdates().execute();
    }

    public class signUpUpdates extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            sp_qualification = (Spinner) findViewById(R.id.sp_qualification);
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("Under graduate");
            list1.add("Post graduate");
            list1.add("Ph.D");
            list1.add("Qualification");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup3_j.this, R.layout.t_spinner_item_light_black, list1) {
                public int getCount() {
                    return 3;
                }
            };
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_qualification.setAdapter(adapter);
            sp_qualification.setSelection(3);
            sp_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 3) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_year_passing = (Spinner) findViewById(R.id.sp_year_passing);
            ArrayList<String> list2 = new ArrayList<>();
            list2.add("1997");
            list2.add("1998");
            list2.add("1999");
            list2.add("2000");
            list2.add("2001");
            list2.add("2002");
            list2.add("2003");
            list2.add("2004");
            list2.add("2005");
            list2.add("2006");
            list2.add("2007");
            list2.add("2008");
            list2.add("2009");
            list2.add("2010");
            list2.add("2011");
            list2.add("2012");
            list2.add("2013");
            list2.add("2014");
            list2.add("2015");
            list2.add("2016");
            list2.add("2017");
            list2.add("2018");
            list2.add("2019");
            list2.add("2020");
            list2.add("2021");
            list2.add("Year Of Passing");
            ArrayAdapter adapter2 = new ArrayAdapter<String>(signup3_j.this, R.layout.t_spinner_item_light_black, list2) {
                public int getCount() {
                    return 25;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_year_passing.setAdapter(adapter2);
            sp_year_passing.setSelection(25);
            sp_year_passing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 25) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /************************************************************************************/

            sp_experience = (Spinner) findViewById(R.id.sp_experience);
            ArrayList<String> list3 = new ArrayList<>();
            list3.add("0 Year");
            list3.add("0-2 Years");
            list3.add("2-4 Years");
            list3.add("4+ Years");
            list3.add("Experience");
            adapter2 = new ArrayAdapter<String>(signup3_j.this, R.layout.t_spinner_item_light_black, list3) {
                public int getCount() {
                    return 4;
                }
            };
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_experience.setAdapter(adapter2);
            sp_experience.setSelection(4);
            sp_experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 4) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            return null;
        }
    }
}
