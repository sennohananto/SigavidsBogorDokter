package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.models.RekamMedis;

public class DetailRekamMedis extends AppCompatActivity {
    private TextView tvKep, tvDiag, tvTind, tvInstansi, tvDokter, tvKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rekam_medis);

        tvKep = (TextView) findViewById(R.id.tvKepentinganRM);
        tvDiag = (TextView) findViewById(R.id.tvDiagnosaRM);
        tvTind = (TextView) findViewById(R.id.tvTindakanRM);
        tvInstansi = (TextView) findViewById(R.id.tvInstansiRM);
        tvDokter = (TextView) findViewById(R.id.tvDokterPemeriksaRM);
        tvKeterangan = (TextView) findViewById(R.id.tvKeteranganRM);

        Intent i = getIntent();
        RekamMedis rm = (RekamMedis) i.getSerializableExtra("RM");

        tvKep.setText(rm.getKeperluan());
        tvDiag.setText(rm.getDiagnosa());
        tvTind.setText(rm.getTindakan());
        tvInstansi.setText(rm.getInstansi());
        tvDokter.setText(rm.getNamaLengkap());
        tvKeterangan.setText(rm.getKeterangan());
    }
}
