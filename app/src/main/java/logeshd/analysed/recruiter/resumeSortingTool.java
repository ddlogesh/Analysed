package logeshd.analysed.recruiter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import logeshd.analysed.R;
import logeshd.analysed.apis.databases;
import logeshd.analysed.recruiter.adapter.listDatabase;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.utils.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resumeSortingTool extends AppCompatActivity implements View.OnClickListener{

    AVLoadingIndicatorView pcircle;
    TextView tv_no_data;
    ImageView iv_search;
    EditText ev_skill,ev_location;
    Spinner sp_qualification,sp_experience;
    ListView l1;

    List<databases> dlist;
    listDatabase adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_resume_sorting_tool);

        pcircle = (AVLoadingIndicatorView) findViewById(R.id.pcircle);
        tv_no_data=findViewById(R.id.tv_no_data);
        l1 = (ListView) findViewById(R.id.lv_database);

        sp_qualification = (Spinner) findViewById(R.id.sp_qualification);
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Under graduate");
        list1.add("Post graduate");
        list1.add("Ph.D");
        list1.add("Qualification");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(resumeSortingTool.this, R.layout.t_spinner_item_light_white, list1) {
            public int getCount() {
                return 3;
            }
        };
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_qualification.setAdapter(adapter1);
        sp_qualification.setSelection(3);
        sp_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 3) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ffffff"));
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
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(resumeSortingTool.this, R.layout.t_spinner_item_light_white, list3) {
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
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ffffff"));
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /************************************************************************************/

        MainRepository.getService().getDatabase(SharedPref.getString(getApplicationContext(),"user_name")).enqueue(new Callback<List<databases>>() {
            @Override
            public void onResponse(Call<List<databases>> call, Response<List<databases>> response) {
                adapter = new listDatabase(resumeSortingTool.this, new ArrayList<databases>());

                dlist=response.body();
                if(dlist!=null) {
                    l1.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                }
                else {
                    l1.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                }
                pcircle.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<databases>> call, Throwable t) {
                Log.d("ddlogesh",t.getMessage());
                l1.setVisibility(View.GONE);
                tv_no_data.setVisibility(View.VISIBLE);
                pcircle.setVisibility(View.GONE);
            }
        });

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        ev_skill = (EditText) findViewById(R.id.ev_skill);
        ev_location = (EditText) findViewById(R.id.ev_location);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/lato.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/lato_bold.ttf");

        tv_title.setTypeface(custom_font2);
        ev_skill.setTypeface(custom_font1);
        ev_location.setTypeface(custom_font1);

        iv_search = (ImageView) findViewById(R.id.iv_search);           iv_search.setOnClickListener(this);
        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);     iv_home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
                String skill = ev_skill.getEditableText().toString().trim().toLowerCase();
                String location = ev_location.getEditableText().toString().trim().toLowerCase();

                String experience = sp_experience.getSelectedItem().toString();
                if(experience.equals("0-2 Years"))
                    experience = "1";
                else if(experience.equals("2-4 Years"))
                    experience = "2";
                else if(experience.equals("4+ Years"))
                    experience = "3";
                else if(experience.equals("0 Year"))
                    experience = "0";
                else
                    experience = "";

                String qualification = sp_qualification.getSelectedItem().toString();
                if (qualification.startsWith("U"))
                    qualification = "ug";
                else if (qualification.equals("Ph.D"))
                    qualification = "phd";
                else if (qualification.startsWith("P"))
                    qualification = "pg";
                else
                    qualification = "";

                if(skill.length()==0)
                    Toast.makeText(this, "Skill cannot be empty!", Toast.LENGTH_SHORT).show();
                else if(qualification.length()==0)
                    Toast.makeText(this, "Select qualification!", Toast.LENGTH_SHORT).show();
                else if(experience.length()==0)
                    Toast.makeText(this, "Select experience!", Toast.LENGTH_SHORT).show();
                else if(location.length()==0)
                    Toast.makeText(this, "Location cannot be empty!", Toast.LENGTH_SHORT).show();
                else{
                    if (dlist != null) {
                        adapter.clear();

                        for (databases d : dlist) {
                            String sk = d.getSkills().toLowerCase();
                            String qual = d.getQualification().toLowerCase();
                            String exp = d.getExperience().toLowerCase();
                            String loc = d.getLocation().toLowerCase();

                            if (sk.contains(skill) && qual.contains(qualification) && exp.equals(experience) && loc.contains(location))
                                adapter.add(new databases(d.getId(), d.getFname(), d.getLname(), d.getPicture(), d.getPosition(), d.getQualification(), d.getYearofpassing(), d.getExperience(), d.getLocation(), d.getEmail(), d.getSkills()));
                        }
                        if (adapter.isEmpty()) {
                            l1.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);
                        }
                        else {
                            l1.setAdapter(adapter);
                            l1.setVisibility(View.VISIBLE);
                            tv_no_data.setVisibility(View.GONE);
                        }
                    }
                    else {
                        l1.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                    }
                }

                break;
            case R.id.iv_home:
                onBackPressed();
                break;
        }
    }
}