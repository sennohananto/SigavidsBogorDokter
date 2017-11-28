package besidev.sigavidsbogor.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.adapter.CustAdapterRekamMedis;
import besidev.sigavidsbogor.api.RekamMedisServices;
import besidev.sigavidsbogor.api.RekamMedisServices;
import besidev.sigavidsbogor.api.RetrofitBuilder;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.helpers.PreferenceManager;
import besidev.sigavidsbogor.models.RekamMedis;
import besidev.sigavidsbogor.models.RekamMedis;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static besidev.sigavidsbogor.SigavidsApp.getAppContext;

public class RekamMedisActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RekamMedis[] kumpRekamMedis;
    private CustAdapterRekamMedis custAdapterRekamMedis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_medis);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayRM);
        recyclerView = findViewById(R.id.recvRM);
        inisialisasiRetrofit();
    }

    private void inisialisasiRetrofit(){
        RekamMedisServices service = RetrofitBuilder.retrofit.create(RekamMedisServices.class);
        Call<List<RekamMedis>> call = service.getRekamMedisMasyarakat(PreferenceManager.getEmail(getApplicationContext()));
        call.enqueue(new Callback<List<RekamMedis>>() {
            @Override
            public void onResponse(Call<List<RekamMedis>> call, Response<List<RekamMedis>> response) {
                List<RekamMedis> listRekamMedis = response.body();
                if (listRekamMedis != null) {
                    kumpRekamMedis = new RekamMedis[listRekamMedis.size()];
                }
                int i = 0;
                for (RekamMedis p : listRekamMedis){
                    AppHelpers.LogCat(p.getKeperluan());
                    kumpRekamMedis[i] = p;
                    i++;
                }
                AppHelpers.LogCat("OnResponse dijalankan");
                custAdapterRekamMedis = new CustAdapterRekamMedis(getApplicationContext(),kumpRekamMedis);
                LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(custAdapterRekamMedis);
            }

            @Override
            public void onFailure(Call<List<RekamMedis>> call, Throwable throwable) {
                Toast.makeText(getAppContext(),"Koneksi Gagal!, silakan coba lagi",Toast.LENGTH_LONG).show();
            }
        });
    }
}
