package id.pantauharga.android.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.pantauharga.android.R;
import id.pantauharga.android.modelgson.HargaKomoditasItemKomparator;
import id.pantauharga.android.parsers.Parseran;

/**
 * Created by Gulajava Ministudio on 11/7/15.
 */
public class RecyclerDaftarHarga extends RecyclerView.Adapter<RecyclerDaftarHarga.ViewHolder> {

    private List<HargaKomoditasItemKomparator> listharga;
    private Context konteks;
    private final TypedValue typedvalues = new TypedValue();
    private int mBackground;
    private OnItemClickListener mOnItemClickListener;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyy HH:mm");
    private Parseran mParseran;
    private int kodegambar = R.drawable.ic_action_keranjang1;

    public RecyclerDaftarHarga(List<HargaKomoditasItemKomparator> list, Context context) {

        this.listharga = list;
        this.konteks = context;

        konteks.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedvalues, true);
        mBackground = typedvalues.resourceId;

        mParseran = new Parseran(konteks);
        kodegambar = mParseran.acakGambarList();
    }


    //LISTENER ON ITEM CLICK
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View views = LayoutInflater.from(konteks).inflate(R.layout.layout_baris_harga, parent, false);
        views.setBackgroundResource(mBackground);

        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        HargaKomoditasItemKomparator hargaKomoItem = listharga.get(position);
        //ambil lastupadate

        Date init_last_updated = hargaKomoItem.getLastUpdated();

        //Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String sfor = formatter.format(date);

        //ambil nama komoditas
        String namakomoditas = hargaKomoItem.getBarang();
        //ambil type
        int type = hargaKomoItem.getType();
        String nama2 = "";
        if(type == 2) {
            nama2 = namakomoditas+"(Beli)";
        }else if(type == 1){
            nama2 = namakomoditas+"(Jual)";
        } else{
            nama2 = namakomoditas+"(Pantau)";
        }

        //ambil harga
        int hargabarang = hargaKomoItem.getPrice();

        String hargaketerangantampil = "Rp " + mParseran.formatAngkaPisah(hargabarang) + " per kg";


        holder.teks_namakomoditas.setText(nama2);
        holder.teks_keteranganharga.setText(hargaketerangantampil);
        holder.teks_lastup.setText("Last Updated : " + formatDate.format(init_last_updated));
        ImageView gambarlist = holder.gambar_list;
        Picasso.with(konteks).load(kodegambar).into(gambarlist);

        holder.containers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Give some time to the ripple to finish the effect
                if (mOnItemClickListener != null) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            mOnItemClickListener.onItemClick(view, (HargaKomoditasItemKomparator) view.getTag(), position);

                        }
                    }, 250);
                }
            }
        });


        holder.itemView.setTag(hargaKomoItem);
    }


    @Override
    public int getItemCount() {
        return listharga.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView teks_namakomoditas;
        public TextView teks_keteranganharga;
        public ImageView gambar_list;
        public TextView teks_lastup;

        public View containers;


        public ViewHolder(View itemView) {
            super(itemView);

            this.containers = itemView;

            teks_namakomoditas = (TextView) containers.findViewById(R.id.teks_namakomoditas);
            teks_keteranganharga = (TextView) containers.findViewById(R.id.teks_keteranganharga);
            teks_lastup = (TextView) containers.findViewById(R.id.teks_lastup);
            gambar_list = (ImageView) containers.findViewById(R.id.gambar_list);

        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, HargaKomoditasItemKomparator hargaItem, int posisi);
    }


}
