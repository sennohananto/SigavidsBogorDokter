package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.adapter.CustAdapterQNA;
import besidev.sigavidsbogor.api.TanyaJawabServices;
import besidev.sigavidsbogor.api.RetrofitBuilder;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.helpers.PreferenceManager;
import besidev.sigavidsbogor.models.TanyaJawab;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static besidev.sigavidsbogor.SigavidsApp.getAppContext;

public class QnaActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TanyaJawab kumpTanyaJawab[];
    private CustAdapterQNA custAdapterQNA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);
        swipeRefreshLayout = findViewById(R.id.swipeLayQNA);

        recyclerView = findViewById(R.id.recv_qna);
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

        inisialisasiRetrofit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.qnamenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.myqna) {
            Intent keMyQNA = new Intent(QnaActivity.this,MyQnAActivity.class);
            startActivity(keMyQNA);
        }
        return super.onOptionsItemSelected(item);
    }

    private void inisialisasiRetrofit(){
        swipeRefreshLayout.setRefreshing(true);
        TanyaJawabServices service = RetrofitBuilder.retrofit.create(TanyaJawabServices.class);
        Call<List<TanyaJawab>> call = service.getTanyaJawabPublik();
        call.enqueue(new Callback<List<TanyaJawab>>() {
            @Override
            public void onResponse(Call<List<TanyaJawab>> call, Response<List<TanyaJawab>> response) {
                List<TanyaJawab> listTanyaJawab = response.body();
                if (listTanyaJawab != null) {
                    kumpTanyaJawab = new TanyaJawab[listTanyaJawab.size()];
                }
                int i = 0;
                for (TanyaJawab p : listTanyaJawab){
                    AppHelpers.LogCat(p.getJudulTanyaJawab());
                    kumpTanyaJawab[i] = p;
                    i++;
                }
                AppHelpers.LogCat("OnResponse dijalankan");
                custAdapterQNA = new CustAdapterQNA(getApplicationContext(),kumpTanyaJawab);
                LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(custAdapterQNA);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<TanyaJawab>> call, Throwable throwable) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getAppContext(),"Koneksi Gagal!, silakan coba lagi",Toast.LENGTH_LONG).show();
            }
        });
    }
}
