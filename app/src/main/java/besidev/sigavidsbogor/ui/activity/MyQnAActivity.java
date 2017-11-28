package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import besidev.sigavidsbogor.R;

public class MyQnAActivity extends AppCompatActivity {
    private FloatingActionButton fabAddTanyaJawab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qn_a);
        fabAddTanyaJawab = findViewById(R.id.fabAddQNA);
        fabAddTanyaJawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keTambahTanyaJawab = new Intent(MyQnAActivity.this,AddTanyaJawabActivity.class);
                view.getContext().startActivity(keTambahTanyaJawab);
            }
        });
    }
}
