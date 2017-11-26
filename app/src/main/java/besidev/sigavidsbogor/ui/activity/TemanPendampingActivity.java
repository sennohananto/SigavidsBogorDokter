package besidev.sigavidsbogor.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.adapter.CustAdapterTemanPendamping;
import besidev.sigavidsbogor.api.RetrofitBuilder;
import besidev.sigavidsbogor.api.TemanPendampingServices;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.models.TemanPendamping;
import besidev.sigavidsbogor.models.TemanPendamping;
import cn.refactor.library.ShapeImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static besidev.sigavidsbogor.SigavidsApp.getAppContext;

public class TemanPendampingActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private TemanPendamping[] kumpTemanPendamping;
    private CustAdapterTemanPendamping adapterTemanPendamping;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_pendamping);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayTP);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_green_light,android.R.color.holo_orange_light,android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inisialisasiRetrofit();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
        recyclerView = findViewById(R.id.rvTemanPendamping);

        inisialisasiRetrofit();
    }

    private void inisialisasiRetrofit(){
        swipeRefreshLayout.setRefreshing(true);
        TemanPendampingServices services = RetrofitBuilder.retrofit.create(TemanPendampingServices.class);
        Call<List<TemanPendamping>> call = services.getTemanPendamping();
        call.enqueue(new Callback<List<TemanPendamping>>() {
            @Override
            public void onResponse(Call<List<TemanPendamping>> call, Response<List<TemanPendamping>> response) {
                List<TemanPendamping> listTemanPendamping = response.body();
                if (listTemanPendamping != null) {
                    kumpTemanPendamping = new TemanPendamping[listTemanPendamping.size()];
                }
                int i = 0;
                for (TemanPendamping p : listTemanPendamping){
                    AppHelpers.LogCat(p.getNamaLengkap());
                    kumpTemanPendamping[i] = p;
                    i++;
                }
                AppHelpers.LogCat("OnResponse dijalankan");
                adapterTemanPendamping = new CustAdapterTemanPendamping(getApplicationContext(),kumpTemanPendamping);
                LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(adapterTemanPendamping);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<TemanPendamping>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
