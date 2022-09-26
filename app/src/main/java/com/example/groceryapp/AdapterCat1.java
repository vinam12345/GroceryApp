package com.example.groceryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.models.ModelCat1;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterCat1 extends RecyclerView.Adapter<AdapterCat1.ExViewHolder> {

    Context context;
    List<ModelCat1> modelCat1s;
    FirebaseDatabase fdatabase= FirebaseDatabase.getInstance();
    DatabaseReference dreference= fdatabase.getReference();
    public AdapterCat1(Context context, List<ModelCat1> modelCat1s) {
        this.context = context;
        this.modelCat1s = modelCat1s;
    }

    @NonNull
    @Override
    public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new ExViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.title.setText(modelCat1s.get(position).getTitle());
        holder.imageView.setImageResource(modelCat1s.get(position).getImages());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowProductDetails.class);
                intent.putExtra("ghfhg",modelCat1s.get(position).getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = holder.title.getText().toString();
                String desc = holder.desc.getText().toString();
                String price = holder.newPrice.getText().toString();
                Integer image = Integer.parseInt(holder.imageView.toString());
                ModelCat1 model = new ModelCat1(title,desc,price,image);

                model.setTitle(title);
                model.setDes(desc);
                model.setPrice(price);
                model.setImages(image);
                dreference.child("Product").push().setValue(model, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                        if (error == null){
                            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelCat1s.size();
    }

    public class ExViewHolder extends RecyclerView.ViewHolder{

        TextView title,desc,newPrice;
        ImageView imageView;
        Button btnBuy;
        public ExViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            newPrice = itemView.findViewById(R.id.newprice);
            imageView=itemView.findViewById(R.id.image);
            btnBuy = itemView.findViewById(R.id.btnbuy);
        }
    }
}
