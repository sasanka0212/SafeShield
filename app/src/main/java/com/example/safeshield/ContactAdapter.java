package com.example.safeshield;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactFace> arrContacts;
    int lastIndex = -1;
    //constructor of ContactAdapter
    ContactAdapter(ArrayList<ContactFace> arrContacts, Context context){
        this.arrContacts = arrContacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_contact,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int pos) {
        viewHolder.txtName.setText(arrContacts.get(pos).contactName);
        viewHolder.txtPhone.setText(arrContacts.get(pos).phoneNo);
        viewHolder.imgCall.setImageResource(R.drawable.ic_baseline_add_ic_call_24);
        viewHolder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_contact);
                TextView edtName = dialog.findViewById(R.id.txtName);
                TextView edtPhone = dialog.findViewById(R.id.edtPhone);
                Button btnSave = dialog.findViewById(R.id.btnSave);
                TextView txtAction = dialog.findViewById(R.id.txtAction);

                txtAction.setText("Update Contact");
                edtName.setText(arrContacts.get(pos).contactName);
                edtPhone.setText(arrContacts.get(pos).phoneNo);
                btnSave.setText("Update");

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", phone = "";
                        if(!edtName.getText().toString().equals("") && !edtPhone.getText().toString().equals("")){
                            name = edtName.getText().toString();
                            phone = edtPhone.getText().toString();
                            arrContacts.set(pos, new ContactFace(name, phone));
                        }else{
                            Toast.makeText(context, "Please enter full details...", Toast.LENGTH_SHORT).show();
                        }
                        notifyItemChanged(pos);
                        dialog.dismiss();
                    }
                });
                dialog.show();
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
        setAnim(viewHolder.itemView, pos);
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llRow;
        TextView txtName, txtPhone;
        ImageView imgCall;
        public ViewHolder(View view) {
            super(view);
            llRow = view.findViewById(R.id.llRow);
            txtName = view.findViewById(R.id.txtName);
            txtPhone = view.findViewById(R.id.txtPhone);
            imgCall = view.findViewById(R.id.imgCall);
        }
    }
    public void setAnim(View view, int pos){
        if(pos>lastIndex){
            Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(anim);
            lastIndex = pos;
        }
    }
}
