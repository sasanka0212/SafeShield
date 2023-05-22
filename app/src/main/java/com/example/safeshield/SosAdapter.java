package com.example.safeshield;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SosAdapter extends RecyclerView.Adapter<SosAdapter.SosViewAdapter> {

    private Context context;
    private ArrayList<Contact> list;

    public SosAdapter(Context context, ArrayList<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SosViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sos_contacts, parent, false);
        return new SosViewAdapter(view);
    }

    public void filterList(ArrayList<Contact> updateList){
        list = updateList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SosViewAdapter holder, int position) {
        Contact currentItem = list.get(position);
        holder.stationName.setText(currentItem.name);
        holder.stationDistrict.setText(currentItem.district);

        holder.stationCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ currentItem.phone));//change the number
                context.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SosViewAdapter extends RecyclerView.ViewHolder {

        private TextView stationName, stationDistrict;
        private ImageView stationCall;

        public SosViewAdapter(@NonNull View itemView) {
            super(itemView);
            stationName = itemView.findViewById(R.id.stationName);
            stationDistrict = itemView.findViewById(R.id.stationDistrict);
            stationCall = itemView.findViewById(R.id.stationCall);
        }
    }
}
