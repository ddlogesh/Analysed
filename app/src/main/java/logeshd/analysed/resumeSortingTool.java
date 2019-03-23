package logeshd.analysed;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import logeshd.analysed.adapter.recycleResumeChoice;
import logeshd.analysed.adapter.recycleResumeSelected;
import logeshd.analysed.classes.RecyclerTouchListener;
import logeshd.analysed.classes.RecyclerTouchListener.ClickListener;
import logeshd.analysed.classes.itemDrawer;

public class resumeSortingTool extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_resume_sorting_tool);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_text1 = (TextView) findViewById(R.id.tv_text1);
        TextView tv_text2 = (TextView) findViewById(R.id.tv_text2);
        TextView tv_text3 = (TextView) findViewById(R.id.tv_text3);
        TextView tv_text4 = (TextView) findViewById(R.id.tv_text4);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");

        tv_title.setTypeface(custom_font1);
        tv_text1.setTypeface(custom_font2);
        tv_text2.setTypeface(custom_font2);
        tv_text3.setTypeface(custom_font2);
        tv_text4.setTypeface(custom_font2);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });

        /***********************************************************************************************/

        final ArrayList<itemDrawer> item1 = new ArrayList<>();
        final RecyclerView rv_card1 = (RecyclerView) findViewById(R.id.rv_card1);
        final recycleResumeChoice adapter1 = new recycleResumeChoice(item1);
        LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card1.setLayoutManager(mLayoutManager);
        rv_card1.setAdapter(adapter1);

        item1.add(new itemDrawer("UI/UX"));
        item1.add(new itemDrawer("C"));
        item1.add(new itemDrawer("C++"));
        item1.add(new itemDrawer("Android"));
        item1.add(new itemDrawer("HTML"));
        adapter1.notifyDataSetChanged();

        /**********************************************************************/

        final ArrayList<itemDrawer> item11 = new ArrayList<>();
        final RecyclerView rv_card11 = (RecyclerView) findViewById(R.id.rv_card11);
        final recycleResumeSelected adapter11 = new recycleResumeSelected(item11);
        LayoutManager mLayoutManager11 = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card11.setLayoutManager(mLayoutManager11);
        rv_card11.setAdapter(adapter11);
        adapter11.notifyDataSetChanged();

        rv_card1.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_card1, new ClickListener() {
            public void onClick(View view, int position) {
                itemDrawer items = (itemDrawer) item1.get(position);
                item1.remove(position);
                item11.add(new itemDrawer(items.getTitle()));
                adapter1.notifyDataSetChanged();
                adapter11.notifyDataSetChanged();
                rv_card11.smoothScrollToPosition(item11.size() - 1);
            }

            public void onLongClick(View view, int position) {
            }
        }));

        /***********************************************************************************************/

        final ArrayList<itemDrawer> item2 = new ArrayList<>();
        final RecyclerView rv_card2 = (RecyclerView) findViewById(R.id.rv_card2);
        final recycleResumeChoice adapter2 = new recycleResumeChoice(item2);
        LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card2.setLayoutManager(mLayoutManager2);
        rv_card2.setAdapter(adapter2);

        item2.add(new itemDrawer("B.Tech"));
        item2.add(new itemDrawer("M.Tech"));
        item2.add(new itemDrawer("Ph.d"));
        item2.add(new itemDrawer("B.Sc"));
        item2.add(new itemDrawer("B.E"));
        adapter2.notifyDataSetChanged();

        /**********************************************************************/

        final ArrayList<itemDrawer> item22 = new ArrayList<>();
        final RecyclerView rv_card22 = (RecyclerView) findViewById(R.id.rv_card22);
        final recycleResumeSelected adapter22 = new recycleResumeSelected(item22);
        LayoutManager mLayoutManager22= new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card22.setLayoutManager(mLayoutManager22);
        rv_card22.setAdapter(adapter22);
        adapter22.notifyDataSetChanged();

        rv_card2.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_card2, new ClickListener() {
            public void onClick(View view, int position) {
                itemDrawer items = (itemDrawer) item2.get(position);
                item2.remove(position);
                item22.add(new itemDrawer(items.getTitle()));
                adapter2.notifyDataSetChanged();
                adapter22.notifyDataSetChanged();
                rv_card22.smoothScrollToPosition(item22.size() - 1);
            }

            public void onLongClick(View view, int position) {
            }
        }));

        /***********************************************************************************************/

        final ArrayList<itemDrawer> item3 = new ArrayList<>();
        final RecyclerView rv_card3 = (RecyclerView) findViewById(R.id.rv_card3);
        final recycleResumeChoice adapter3 = new recycleResumeChoice(item3);
        LayoutManager mLayoutManager3 = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card3.setLayoutManager(mLayoutManager3);
        rv_card3.setAdapter(adapter3);

        item3.add(new itemDrawer("3 months"));
        item3.add(new itemDrawer("6 months"));
        item3.add(new itemDrawer("1 year"));
        item3.add(new itemDrawer("2 years"));
        item3.add(new itemDrawer("3 years"));
        adapter3.notifyDataSetChanged();

        /**********************************************************************/

        final ArrayList<itemDrawer> item33 = new ArrayList<>();
        final RecyclerView rv_card33 = (RecyclerView) findViewById(R.id.rv_card33);
        final recycleResumeSelected adapter33 = new recycleResumeSelected(item33);
        LayoutManager mLayoutManager33= new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card33.setLayoutManager(mLayoutManager33);
        rv_card33.setAdapter(adapter33);
        adapter33.notifyDataSetChanged();

        rv_card3.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_card3, new ClickListener() {
            public void onClick(View view, int position) {
                itemDrawer items = (itemDrawer) item3.get(position);
                item3.remove(position);
                item33.add(new itemDrawer(items.getTitle()));
                adapter3.notifyDataSetChanged();
                adapter33.notifyDataSetChanged();
                rv_card33.smoothScrollToPosition(item33.size() - 1);
            }

            public void onLongClick(View view, int position) {
            }
        }));

        /***********************************************************************************************/

        final ArrayList<itemDrawer> item4 = new ArrayList<>();
        final RecyclerView rv_card4 = (RecyclerView) findViewById(R.id.rv_card4);
        final recycleResumeChoice adapter4 = new recycleResumeChoice(item4);
        LayoutManager mLayoutManager4 = new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card4.setLayoutManager(mLayoutManager4);
        rv_card4.setAdapter(adapter4);

        item4.add(new itemDrawer("Hyderabad"));
        item4.add(new itemDrawer("Pune"));
        item4.add(new itemDrawer("Chennai"));
        item4.add(new itemDrawer("Mumbai"));
        item4.add(new itemDrawer("Delhi"));
        adapter4.notifyDataSetChanged();

        /**********************************************************************/

        final ArrayList<itemDrawer> item44 = new ArrayList<>();
        final RecyclerView rv_card44 = (RecyclerView) findViewById(R.id.rv_card44);
        final recycleResumeSelected adapter44 = new recycleResumeSelected(item44);
        LayoutManager mLayoutManager44= new LinearLayoutManager(getApplicationContext(), 0, false);
        rv_card44.setLayoutManager(mLayoutManager44);
        rv_card44.setAdapter(adapter44);
        adapter44.notifyDataSetChanged();

        rv_card4.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_card4, new ClickListener() {
            public void onClick(View view, int position) {
                itemDrawer items = (itemDrawer) item4.get(position);
                item4.remove(position);
                item44.add(new itemDrawer(items.getTitle()));
                adapter4.notifyDataSetChanged();
                adapter44.notifyDataSetChanged();
                rv_card44.smoothScrollToPosition(item44.size() - 1);
            }

            public void onLongClick(View view, int position) {
            }
        }));
    }
}