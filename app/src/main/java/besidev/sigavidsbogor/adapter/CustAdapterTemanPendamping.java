package besidev.sigavidsbogor.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.helpers.AppHelpers;
import besidev.sigavidsbogor.helpers.PreferenceManager;
import besidev.sigavidsbogor.models.TemanPendamping;
import cn.refactor.library.ShapeImageView;

/**
 * Created by Senno Hananto on 25/11/2017.
 */

public class CustAdapterTemanPendamping extends RecyclerView.Adapter<CustAdapterTemanPendamping.MyViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private TemanPendamping kumpTemanPendamping[];
    private TextView tvNamaL, tvNamaP, tvNoTelp, tvPinBB, tvFb, tvWil;
    private ShapeImageView imgTP;
    private int i = 0;

    public CustAdapterTemanPendamping(Context context, TemanPendamping[] kumpTemanPendamping) {
        this.context = context;
        this.layoutInflater = (LayoutInflater.from(context));
        this.kumpTemanPendamping = kumpTemanPendamping;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.temanpendamping_row,null,true);
        MyViewHolder holder = new MyViewHolder(v);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        tvNamaL.setText(kumpTemanPendamping[position].getNamaLengkap());
        tvNamaP.setText(kumpTemanPendamping[position].getNamaPanggilan());
        tvFb.setText(kumpTemanPendamping[position].getFb());
        tvNoTelp.setText(kumpTemanPendamping[position].getNoTelp());
        tvPinBB.setText(kumpTemanPendamping[position].getPinBB());
        tvWil.setText(kumpTemanPendamping[position].getWilayah());
        Picasso.with(context).load(kumpTemanPendamping[position].getUrlGambar()).into(imgTP);

        tvNoTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                if(!kumpTemanPendamping[position].getNoTelp().equals("")){
                    intent.setData(Uri.parse("tel:"+kumpTemanPendamping[position].getNoTelp()));
                    view.getContext().startActivity(intent);
                }else{
                    Toast.makeText(context,"Tidak ada no telp",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return kumpTemanPendamping.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNamaL = (TextView) itemView.findViewById(R.id.tvNamaLengkapTP);
            tvNamaP = (TextView) itemView.findViewById(R.id.tvPanggilanTP);
            tvFb = (TextView) itemView.findViewById(R.id.tvFbTP);
            tvNoTelp = (TextView) itemView.findViewById(R.id.tvNoTelpTP);
            tvPinBB = (TextView) itemView.findViewById(R.id.tvPinBBTP);
            tvWil = (TextView) itemView.findViewById(R.id.tvWilKerjaTP);
            imgTP = (ShapeImageView) itemView.findViewById(R.id.imgTP);
        }
    }
}