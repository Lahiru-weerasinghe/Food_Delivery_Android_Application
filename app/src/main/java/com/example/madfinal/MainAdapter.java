package com.example.madfinal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {


    public MainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    //get data and fill in update form pur them in holder
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull @NotNull MainModel model) {
       //fields must be updated
        holder.fname.setText(model.getFname());
        holder.email.setText(model.getEmail());
        holder.mobile.setText(model.getMobile());
        holder.menu.setText(model.getMenu());
        holder.date.setText(model.getDate());

        //Update data
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //get pop update details
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.fname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1400)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();
                EditText fname = view.findViewById(R.id.txtName);
                EditText email = view.findViewById(R.id.txtEmail);
                EditText mobile = view.findViewById(R.id.txtMobile);
                EditText menu = view.findViewById(R.id.txtMenu);
                EditText date = view.findViewById(R.id.txtDate);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                //update new details form
                fname.setText(model.getFname());
                email.setText(model.getEmail());
                mobile.setText(model.getMobile());
                menu.setText(model.getMenu());
                date.setText(model.getDate());

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //put update detials in database
                        Map<String, Object> map = new HashMap<>();
                        map.put("fname",fname.getText().toString());
                        map.put("mobile",mobile.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("menu",menu.getText().toString());

                        //access database
                        FirebaseDatabase.getInstance().getReference().child("ResDetails")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    //success
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.fname.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();;
                                    }
                                })
                                //fail
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.fname.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });
                        //Delete data
                        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //alert view
                                AlertDialog.Builder builder = new AlertDialog.Builder(holder.fname.getContext());
                                builder.setTitle("Are you Sure?");
                                builder.setMessage("Deleted data can't be undo");

                                        //if ok pressed
                                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("ResDetails")
                                                //dropvalue
                                                .child(getRef(position).getKey()).removeValue();
                                    }
                                });

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(holder.fname.getContext(), "Cancelled",Toast.LENGTH_SHORT).show();

                                    }
                                });

                                builder.show();
                            }
                        });
    }
    @NotNull
    @NonNull
    @Override

    //Read data
    //new recycle view
    public myViewHolder onCreateViewHolder(@NonNull @NotNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rditem,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView fname,mobile,email,date,menu;

        Button btnEdit,btnDelete;

        //vieving it
        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            fname=(TextView)itemView.findViewById(R.id.nametext);
            mobile=(TextView)itemView.findViewById(R.id.mobiletext);
            email=(TextView)itemView.findViewById(R.id.emailtext);
            date=(TextView)itemView.findViewById(R.id.datetext);
            menu=(TextView)itemView.findViewById(R.id.menutext);
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);

        }
    }
}
