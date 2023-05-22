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

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewAdapter> {

    private Context context;
    private ArrayList<Hcontact> list;

    public HospitalAdapter(Context context, ArrayList<Hcontact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HospitalViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospital_contacts, parent, false);
        return new HospitalViewAdapter(view);
    }

    public void filterList(ArrayList<Hcontact> updateList){
        list = updateList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewAdapter holder, int position) {
        Hcontact currentItem = list.get(position);
        holder.hospitalName.setText(currentItem.hospitalName);
        holder.hospitalPlace.setText(currentItem.place);
        holder.hospitalDistrict.setText(currentItem.district);

        holder.hospitalCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ currentItem.contactNo));//change the number
                context.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HospitalViewAdapter extends RecyclerView.ViewHolder {

        TextView hospitalName, hospitalPlace, hospitalDistrict;
        ImageView hospitalCall;
        public HospitalViewAdapter(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalPlace = itemView.findViewById(R.id.hospitalPlace);
            hospitalDistrict = itemView.findViewById(R.id.hospitalDistrict);
            hospitalCall = itemView.findViewById(R.id.hospitalCall);
        }
    }

}
