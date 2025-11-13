package com.example.mainactivity.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.Adapter.ModelAdapter;
import com.example.mainactivity.Adapter.SliderAdapter;
import com.example.mainactivity.Domain.ItemsDomain;
import com.example.mainactivity.Domain.SliderItems;
import com.example.mainactivity.Helper.ManagmentCart;
import com.example.mainactivity.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {


    private ActivityDetailBinding binding;
    private ItemsDomain object;
    private int numberOrder=1;

    private ManagmentCart manangmetCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        manangmetCart = new ManagmentCart(this);

        getBundles();
        initBaners();
        initSize();

    }

    private void initSize() {
        ArrayList<String> list = new ArrayList<>();

        list.add("PRO");
        list.add("Plus");
        list.add("X");
        list.add("XTX");
        binding.recyclerModel.setAdapter(new ModelAdapter(list));
        binding.recyclerModel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private void initBaners() {
        ArrayList<SliderItems> sliderItems = new ArrayList<>();
        for (int i = 0; i < object.getPicUrl().size(); i++) {

            sliderItems.add(new SliderItems(object.getPicUrl().get(i)));
        }
        binding.viewPageSlider.setAdapter(new SliderAdapter(sliderItems, binding.viewPageSlider));
        binding.viewPageSlider.setClipToPadding(false);
        binding.viewPageSlider.setClipChildren(false);
        binding.viewPageSlider.setOffscreenPageLimit(3);
        binding.viewPageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void getBundles() {
        object=(ItemsDomain) getIntent().getSerializableExtra("object");
        binding.title.setText(object.getTitle());
        binding.priceTxt.setText("$" + object.getPrice());
        binding.ratingBar.setRating((float) object.getRating());
        binding.ratingText.setText(object.getRating() + " Rating");
        binding.descriptionTxt.setText(object.getDescription());


        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberinCart(numberOrder);
                manangmetCart.insertItem(object);
            }
        });
        binding.backBtn.setOnClickListener(v -> finish());
    }
}