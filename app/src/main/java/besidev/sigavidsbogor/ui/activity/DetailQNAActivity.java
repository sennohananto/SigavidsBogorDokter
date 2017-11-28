package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daasuu.bl.BubbleLayout;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.models.TanyaJawab;

public class DetailQNAActivity extends AppCompatActivity {
    private BubbleLayout bubleQ, bubleA;
    private TextView tvQ, tvA;
    private TanyaJawab tanyaJawab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_qna);
        Intent intent = getIntent();
        tanyaJawab = (TanyaJawab) intent.getSerializableExtra("QNA");
        tvQ = findViewById(R.id.tvBubleQ);
        tvA = findViewById(R.id.tvBubleA);
        bubleA = findViewById(R.id.bubleA);
        bubleQ = findViewById(R.id.bubleQ);

        this.setTitle(tanyaJawab.getJudulTanyaJawab());
        tvQ.setText(tanyaJawab.getIsiTanyaJawab());
        if(!tanyaJawab.getJawaban().equals("")){
            tvA.setText(tanyaJawab.getJawaban());
            bubleA.setVisibility(View.VISIBLE);
        }
    }
}
