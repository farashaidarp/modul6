package com.example.modul6.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modul6.API.APIRequestData;
import com.example.modul6.API.RetroServer;
import com.example.modul6.Activity.MainActivity;
import com.example.modul6.Model.DataModel;
import com.example.modul6.Model.ResponseModel;
import com.example.modul6.R;
import com.google.android.material.transition.Hold;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private int idMahasiswa;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;//inflate data dari cardview
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);//set posisi data
        holder.tvID.setText(String.valueOf(dm.getId()));
        holder.tvNama.setText(dm.getNama());
        holder.tvAlamat.setText(dm.getJurusan());
        holder.tvTelepon.setText(dm.getEmail());
        //meletakkan/set text dari setiap textview
    }

    @Override
    public int getItemCount() {
        return listData.size();
        //brp data yang ditarik
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvID, tvNama, tvAlamat, tvTelepon;


        public HolderData(@NonNull View itemView) {

            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvTelepon = itemView.findViewById(R.id.tv_telepon);
            tvID = itemView.findViewById(R.id.tv_id);

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Kamu mau ngapain beb?");
                    dialogPesan.setCancelable(true);//biar bisa di cancel
                    idMahasiswa = Integer.parseInt(tvID.getText().toString());

                    dialogPesan.setPositiveButton("Hapus beb!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                            ((MainActivity)ctx).retrieveData();
                        }
                    });

                    dialogPesan.setNegativeButton("Edit syg!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });


        }
        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idMahasiswa);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "Kode : "+kode+"|Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
