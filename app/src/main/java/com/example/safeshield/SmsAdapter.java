package com.example.safeshield;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsViewAdapter> {

    Context context;
    ArrayList<ContactFace> list;
    SharedPreferences pref;

    public SmsAdapter(Context context, ArrayList<ContactFace> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SmsViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sms_list, parent, false);
        return new SmsViewAdapter(view);
    }

    public void filterList(ArrayList<ContactFace> updateList){
        list = updateList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SmsViewAdapter holder, int position) {
        ContactFace currentItem = list.get(position);
        holder.textName.setText(currentItem.name);
        holder.textPhone.setText(currentItem.phoneNo);

        holder.sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefMsg = context.getSharedPreferences("location", MODE_PRIVATE);
                String add = prefMsg.getString("loc", "");
                pref = context.getSharedPreferences("location", MODE_PRIVATE);
                String msg = pref.getString("msg", "");
                String lat = pref.getString("lat", "");
                String lng = pref.getString("lng", "");
                String gmap = "https://maps.google.com/?q=" + lat + "," + lng ;
                Uri uri = Uri.parse("smsto:" + currentItem.phoneNo);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", msg + ".  " + add + " " + gmap);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SmsViewAdapter extends RecyclerView.ViewHolder {

        TextView textName, textPhone;
        ImageView sendSms;

        public SmsViewAdapter(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textPhone = itemView.findViewById(R.id.textPhone);
            sendSms = itemView.findViewById(R.id.sendSms);
        }
    }

}
