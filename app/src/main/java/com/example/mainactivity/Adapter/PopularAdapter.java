package com.example.mainactivity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.mainactivity.Activity.DetailActivity;
import com.example.mainactivity.Domain.ItemsDomain;
import com.example.mainactivity.databinding.ViewholderPoplarBinding;

import java.util.ArrayList;

public class PopularAdapter  extends RecyclerView.Adapter<PopularAdapter.ViewHolder>{
    ArrayList<ItemsDomain> items;
    Context context;

    public PopularAdapter(ArrayList<ItemsDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderPoplarBinding binding = ViewholderPoplarBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.binding.titleTxt.setText(items.get(position).getTitle());
        holder.binding.priceTxt.setText("$"+items.get(position).getPrice());
        holder.binding.ratingTxt.setText("("+items.get(position).getRating()+")");
        holder.binding.oldPriceTxt.setText("$"+items.get(position).getOldPrice());
        holder.binding.oldPriceTxt.setPaintFlags(holder.binding.oldPriceTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        // Trebao bih ovo popraviti al ne znam
        // holder.binding.ratingBar.getRating((float) items.get(position).getRating());

        RequestOptions options = new RequestOptions();
        options = options.transform(new CenterCrop());

        Glide.with(context).load(items.get(position).getPicUrl().get(0)).apply(options).into(holder.binding.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderPoplarBinding binding;
        public ViewHolder(ViewholderPoplarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
