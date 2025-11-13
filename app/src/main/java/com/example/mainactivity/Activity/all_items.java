package com.example.mainactivity.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;

import com.example.mainactivity.FunctionsToMain.Functions;
import com.example.mainactivity.R;
import com.example.mainactivity.database.BaseActivity;
import com.example.mainactivity.databinding.ActivityMainBinding;

public class all_items extends BaseActivity {
    ActivityMainBinding binding;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_items);

        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        Functions functions = new Functions(
                binding,
                database
        );
        functions.initPopular(this, binding, 12);


    }
}
