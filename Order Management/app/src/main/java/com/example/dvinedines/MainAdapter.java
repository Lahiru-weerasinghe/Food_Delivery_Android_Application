package com.example.dvinedines;

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

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, final int position, @NonNull @NotNull MainModel model) {
        holder.name.setText(model.getName());
        holder.menucode.setText(model.getMenucode());
        holder.address.setText(model.getAddress());
        holder.phone.setText(model.getPhone());
        holder.email.setText(model.getEmail());
        holder.no_of_oders.setText(model.getNo_of_oders());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1700)
                        .create();

               //dialogPlus.show();
                View view = dialogPlus.getHolderView();

                //EditText name = view.findViewById(R.id.txtName);

                EditText menu = view.findViewById(R.id.txtMenuCode);
                EditText address = view.findViewById(R.id.txtAddress);
                EditText phone = view.findViewById(R.id.txtMobile);
                //EditText email = view.findViewById(R.id.txtEmail);
                EditText no_of_orders = view.findViewById(R.id.txtOrders);
                //EditText date = view.findViewById(R.id.txtDate);
                EditText time = view.findViewById(R.id.txtTime);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                //name.setText(model.getName());
                menu.setText(model.getMenucode());
                address.setText(model.getAddress());
                phone.setText(model.getPhone());
                //email.setText(model.getEmail());
                no_of_orders.setText(model.getNo_of_oders());
                //date.setText(model.getDate());
                time.setText(model.getTime());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        //map.put("name",name.getText().toString());
                        map.put("menocode",menu.getText().toString());
                        map.put("address",address.getText().toString());
                        map.put("phone",phone.getText().toString());
                        //map.put("email",email.getText().toString());
                        map.put("no_of_oders",no_of_orders.getText().toString());
                        //map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("orders")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });



                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                        builder.setTitle("Are you sure?");
                        builder.setMessage("Deleted data can't be Undo.");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("orders")
                                        .child(getRef(position).getKey()).removeValue();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.name.getContext(),"Cancelled.", Toast.LENGTH_SHORT).show();

                            }
                        });

                        builder.show();
                    }
                });
            }
        });


    }


    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView name,menucode,address,phone,email,no_of_oders,date,time;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.nametext);
            menucode = (TextView)itemView.findViewById(R.id.menucodetext);
            address = (TextView)itemView.findViewById(R.id.addresstext);
            phone = (TextView)itemView.findViewById(R.id.phonetext);
            email = (TextView)itemView.findViewById(R.id.emailtext);
            no_of_oders = (TextView)itemView.findViewById(R.id.no_of_oderstext);
            date = (TextView)itemView.findViewById(R.id.datetext);
            time = (TextView)itemView.findViewById(R.id.timetext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}
