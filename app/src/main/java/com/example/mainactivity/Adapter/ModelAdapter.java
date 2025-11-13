package com.example.mainactivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.databinding.ViewholderSizeBinding;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.Viewholder> {
    ArrayList<String> items;
    Context context;

    int selectPosition = -1;

    public ModelAdapter(ArrayList<String> items) {
        this.items = items;
    }

    int lastSelectPosition = -1;
    @NonNull
    @Override
    public ModelAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderSizeBinding binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false);


        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.Viewholder holder, int position) {
    holder.binding.textTxt.setText(items.get(position));
    holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            lastSelectPosition = selectPosition;
            selectPosition = position;
            notifyItemChanged(lastSelectPosition);
            notifyItemChanged(selectPosition);
        }
    });

        if (selectPosition == position){
            holder.binding.textTxt.setBackgroundResource(R.drawable.size_selected);
            holder.binding.textTxt.setTextColor(context.getResources().getColor(R.color.purple));
        }else {
            holder.binding.textTxt.setBackgroundResource(R.drawable.size_unselected);
            holder.binding.textTxt.setTextColor(context.getResources().getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderSizeBinding binding;
        public Viewholder(ViewholderSizeBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
