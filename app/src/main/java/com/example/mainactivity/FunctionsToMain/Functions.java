package com.example.mainactivity.FunctionsToMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mainactivity.Activity.Auth.Profile;
import com.example.mainactivity.Activity.CartActivity;
import com.example.mainactivity.Adapter.CategoryAdapter;
import com.example.mainactivity.Adapter.PopularAdapter;
import com.example.mainactivity.Domain.CategoryDomain;
import com.example.mainactivity.Domain.ItemsDomain;
import com.example.mainactivity.MainActivity;
import com.example.mainactivity.R;
import com.example.mainactivity.Activity.all_items;
import com.example.mainactivity.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.example.mainactivity.Adapter.SliderAdapter;
import com.example.mainactivity.Domain.SliderItems;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Functions {

    private ActivityMainBinding binding;
    private FirebaseDatabase database;

    public Functions(ActivityMainBinding binding, FirebaseDatabase database) {
        this.binding = binding;
        this.database = database;
    }

    public void initCategory() {

        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarOfficial.setVisibility(View.VISIBLE);

        ArrayList<CategoryDomain> items = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        items.add(issue.getValue(CategoryDomain.class));

                    }
                    if (!items.isEmpty()){
                        // Postavlja LayoutManager za RecyclerView kako bi bio horizontalan.
                        binding.recyclerViewOfficial.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
                        binding.recyclerViewOfficial.setAdapter(new CategoryAdapter(items));
                        binding.recyclerViewOfficial.setNestedScrollingEnabled(true);
                    }
                    binding.progressBarOfficial.setVisibility(View.GONE);
                    // Sakriva progress bar nakon učitavanja podataka.
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void initBaner() {
        DatabaseReference myRef = database.getReference("Banner");
        binding.progressBarBanner.setVisibility(View.VISIBLE);
        ArrayList<SliderItems> items = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        items.add(issue.getValue(SliderItems.class));

                    }
                    banners(items);
                    binding.progressBarBanner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void banners(ArrayList<SliderItems> items) {
        binding.viewPagerSlider.setAdapter(new SliderAdapter(items, binding.viewPagerSlider));
        binding.viewPagerSlider.setClipToPadding(false);
        binding.viewPagerSlider.setClipChildren(false);
        binding.viewPagerSlider.setOffscreenPageLimit(3);
        binding.viewPagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        binding.viewPagerSlider.setPageTransformer(compositePageTransformer);


    }

    public void setupBottomNavigationView(final Context context) {
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokrenite odgovarajuću aktivnost za profil (ProfileActivity)
                Intent intent = new Intent(context, Profile.class);
                context.startActivity(intent);
            }
        });
        binding.homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokrenite odgovarajuću aktivnost za profil (ProfileActivity)
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        binding.allItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokrenite odgovarajuću aktivnost za sve stavke (AllItemsActivity)
                Intent intent = new Intent(context, all_items.class);
                context.startActivity(intent);
            }
        });
        binding.Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokrenite odgovarajuću aktivnost za sve stavke (AllItemsActivity)
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public static void setupSeeAllTextClickListener(final Context context, final TextView seeAllText) {
        seeAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, all_items.class);
                context.startActivity(intent);
            }
        });
    }



    public void initPopular(Context context, ActivityMainBinding binding, Integer maxItems) {
        DatabaseReference myRef = database.getReference("Items");

        binding.progressBarPopular.setVisibility(View.VISIBLE);
        ArrayList<ItemsDomain> items = new ArrayList<>();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        if (count >= maxItems) {
                            break;
                        }
                        ItemsDomain item = issue.getValue(ItemsDomain.class);
                        if (item != null) {
                            items.add(item);
                            Log.d("initPopular", "ajde ucitaj se: " + item.getTitle());
                            count++;
                        } else {
                            Log.e("initPopular", "nemoj ovo da vidim : " + issue.toString());
                        }
                    }
                    if (!items.isEmpty()) {
                        RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recyclerViewMostPopular);
                        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                        recyclerView.setAdapter(new PopularAdapter(items));
                        recyclerView.setNestedScrollingEnabled(true);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        binding.recyclerViewMostPopular.setVisibility(View.GONE);
                        Log.d("initPopular", "No items found");
                    }
                } else {
                    binding.recyclerViewMostPopular.setVisibility(View.GONE);
                    Log.e("initPopular", "Snapshot does not exist");
                }
                binding.progressBarPopular.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBarPopular.setVisibility(View.GONE);
                Log.e("initPopular", "Database error: " + error.getMessage());
            }
        });
    }

}
