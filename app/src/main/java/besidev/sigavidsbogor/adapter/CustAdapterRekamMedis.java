package besidev.sigavidsbogor.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.models.RekamMedis;
import besidev.sigavidsbogor.ui.activity.DetailRekamMedis;

/**
 * Created by Senno Hananto on 28/11/2017.
 */

public class CustAdapterRekamMedis extends RecyclerView.Adapter<CustAdapterRekamMedis.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private RekamMedis kumpRekamMedis[];
    private TextView tvJudulRekamMedis;
    private int i = 0;

    public CustAdapterRekamMedis(Context context, RekamMedis[] kumpRekamMedis) {
        this.context = context;
        this.layoutInflater = (LayoutInflater.from(context));
        this.kumpRekamMedis = kumpRekamMedis;
    }

    @Override
    public CustAdapterRekamMedis.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rekammedis_row,null,true);
        CustAdapterRekamMedis.MyViewHolder holder = new CustAdapterRekamMedis.MyViewHolder(v);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustAdapterRekamMedis.MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        tvJudulRekamMedis.setText(kumpRekamMedis[holder.getAdapterPosition()].getWaktu());
        i++;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keDetailRekamMedis = new Intent(view.getContext(), DetailRekamMedis.class);
                keDetailRekamMedis.putExtra("RM",kumpRekamMedis[position]);
                view.getContext().startActivity(keDetailRekamMedis);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kumpRekamMedis.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
            tvJudulRekamMedis = (TextView) itemView.findViewById(R.id.tvJudulRekamMedis);
        }
    }
}
