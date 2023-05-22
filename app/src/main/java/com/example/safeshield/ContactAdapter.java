package com.example.safeshield;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactFace> arrContacts;
    //constructor of ContactAdapter
    ContactAdapter(ArrayList<ContactFace> arrContacts, Context context){
        this.arrContacts = arrContacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int pos) {
        ContactFace currentPosition = arrContacts.get(pos);

        viewHolder.txtName.setText(currentPosition.name);
        viewHolder.txtPhone.setText(currentPosition.phoneNo);

        viewHolder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ currentPosition.phoneNo));//change the number
                context.startActivity(callIntent);
            }
        });
        viewHolder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_baseline_delete_24)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper dbHelper = new DBHelper(context);
                                dbHelper.deleteContact(currentPosition.phoneNo);
                                arrContacts.remove(pos);
                                notifyItemRemoved(pos);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){ }
                        });
                builder.show();
                return true;
            }
        });
        viewHolder.updateContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpadateContactActivity.class);
                intent.putExtra("name", currentPosition.name);
                intent.putExtra("phone", currentPosition.phoneNo);
                intent.putExtra("email", currentPosition.email);
                context.startActivity(intent);
                notifyItemChanged(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llRow;
        TextView txtName, txtPhone;
        ImageView updateContactInfo;
        public ViewHolder(View view) {
            super(view);
            llRow = view.findViewById(R.id.llRow);
            txtName = view.findViewById(R.id.txtName);
            txtPhone = view.findViewById(R.id.txtPhone);
            updateContactInfo = view.findViewById(R.id.updateContactInfo);
        }
    }
}
