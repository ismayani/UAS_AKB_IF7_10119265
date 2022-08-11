package com.ismemaya.uasakbif_710119265;

// 10119265_IsmayaniSetyaningrum_IF7

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PenaAdapter extends RecyclerView.Adapter<PenaAdapter.PenaViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    private onClickListenerPena listenerPena;

    public PenaAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;

    }

    @NonNull
    @Override
    public PenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pena_items, parent, false);

        return new PenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenaViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)){

            return;
        }
        String judul = mCursor.getString(mCursor.getColumnIndexOrThrow(PenaDatabase.judul));
        String ttb = mCursor.getString(mCursor.getColumnIndexOrThrow(PenaDatabase.ttb));
        String kategori = mCursor.getString(mCursor.getColumnIndexOrThrow(PenaDatabase.kategori));
        String isi = mCursor.getString(mCursor.getColumnIndexOrThrow(PenaDatabase.isi));
        long id = mCursor.getLong(mCursor.getColumnIndexOrThrow(PenaDatabase.id_pena));

        holder.itemView.setTag(id);
        holder.Judul.setText(judul);
        holder.Ttb.setText(ttb);
        holder.Kategori.setText(kategori);
        holder.Isi.setText(isi);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class PenaViewHolder extends RecyclerView.ViewHolder {


        private TextView Judul, Ttb, Kategori, Isi;


        public PenaViewHolder(@NonNull View itemView) {
            super(itemView);

            Judul = itemView.findViewById(R.id.Judul);
            Ttb = itemView.findViewById(R.id.Ttb);
            Kategori = itemView.findViewById(R.id.Kategori);
            Isi = itemView.findViewById(R.id.Isi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long position = (long) itemView.getTag();
                    //  Toast.makeText(itemView.getContext(), "id click from adapter : " + position, Toast.LENGTH_SHORT).show();
                    listenerPena.onItemClikPena(position);
                }
            });
        }
    }

    public interface onClickListenerPena{
        void onItemClikPena(long id);
    }
    public void setOnClickListenerPena(onClickListenerPena listenerPena) {
        this.listenerPena = listenerPena;
    }


    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();

        }
    }
}
