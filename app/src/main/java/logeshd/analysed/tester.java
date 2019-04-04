package logeshd.analysed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import logeshd.analysed.apis.status;
import logeshd.analysed.classes.demo;
import logeshd.analysed.service.MainRepository;
import logeshd.analysed.service.MainService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tester extends AppCompatActivity {

    MainService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_tester);

        MainRepository repo = new MainRepository();
        service=repo.getService();

        //Call<List<demo>> repos = service.loadChanges();
        //Call<List<demo>> repos = service.getSingle("one");

        /*Call<status> a=service.uploader(new demo("ones","kamal"));
        a.enqueue(new Callback<status>() {
            @Override
            public void onResponse(Call<status> call, Response<status> response) {
                status a=response.body();
                if(a!=null)
                    Log.d("ddlogesh", a.getMessage());
            }

            @Override
            public void onFailure(Call<status> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
            }
        });*/
    }
}

        /*repos.enqueue(new Callback<List<demo>>() {
            @Override
            public void onResponse(Call<List<demo>> call, Response<List<demo>> response) {
                List<demo> d=response.body();
                if(d!=null)
                    Log.d("ddlogesh",d.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<demo>> call, Throwable t) {
                Log.d("ddlogesh","Nope " + t.getMessage());
            }
        });*/
