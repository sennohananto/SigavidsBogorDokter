package besidev.sigavidsbogor.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.models.Berita;
import besidev.sigavidsbogor.ui.activity.InAppbrowser;

/**
 * Created by Senno Hananto on 25/11/2017.
 */

public class CustAdapterBerita extends RecyclerView.Adapter<CustAdapterBerita.MyViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private Berita kumpBerita[];
    private TextView tvJudulBerita;
    private int i = 0;
    private String url;

    public CustAdapterBerita(Context context, Berita[] kumpBerita) {
        this.context = context;
        this.layoutInflater = (LayoutInflater.from(context));
        this.kumpBerita = kumpBerita;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita_row,null,true);
        MyViewHolder holder = new MyViewHolder(v);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        tvJudulBerita.setText(kumpBerita[holder.getAdapterPosition()].getJudulBerita());
        i++;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent keInApp = new Intent(context, InAppbrowser.class);
//                keInApp.putExtra("url",kumpBerita[holder.getAdapterPosition()].getUrlBerita());
//                view.getContext().startActivity(keInApp);
//                context.startActivity(keInApp);
                if(kumpBerita[holder.getAdapterPosition()].getUrlBerita().substring(0,3).equals("http")){
                    url = kumpBerita[holder.getAdapterPosition()].getUrlBerita();
                }else{
                    url = "http://"+kumpBerita[holder.getAdapterPosition()].getUrlBerita();
                }

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kumpBerita.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            tvJudulBerita = (TextView) itemView.findViewById(R.id.tvJudulBerita);
        }
    }
}
