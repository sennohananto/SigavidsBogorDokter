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
import besidev.sigavidsbogor.models.TanyaJawab;
import besidev.sigavidsbogor.ui.activity.DetailQNAActivity;

/**
 * Created by Senno Hananto on 28/11/2017.
 */

public class CustAdapterQNA extends RecyclerView.Adapter<CustAdapterQNA.MyViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private TanyaJawab kumpTanyaJawab[];
    private TextView tvJudulTanyaJawab;
    private int i = 0;
    private String url;

    public CustAdapterQNA(Context context, TanyaJawab[] kumpTanyaJawab) {
        this.context = context;
        this.layoutInflater = (LayoutInflater.from(context));
        this.kumpTanyaJawab = kumpTanyaJawab;
    }

    @Override
    public CustAdapterQNA.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tanyajawab_row,null,true);
        CustAdapterQNA.MyViewHolder holder = new CustAdapterQNA.MyViewHolder(v);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        tvJudulTanyaJawab.setText(kumpTanyaJawab[holder.getAdapterPosition()].getJudulTanyaJawab());
        i++;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keDetailTanyaJawab = new Intent(view.getContext(), DetailQNAActivity.class);
                keDetailTanyaJawab.putExtra("QNA",kumpTanyaJawab[position]);
                view.getContext().startActivity(keDetailTanyaJawab);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kumpTanyaJawab.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            tvJudulTanyaJawab = itemView.findViewById(R.id.tvJudulQNA);
        }
    }
}
