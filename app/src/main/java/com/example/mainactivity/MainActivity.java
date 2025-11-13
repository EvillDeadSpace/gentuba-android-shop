package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import com.example.mainactivity.FunctionsToMain.Functions;
import com.example.mainactivity.database.BaseActivity;
import com.example.mainactivity.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        TextView seeAllText = findViewById(R.id.seeAllText);

        //Poziv funkcija iz folder Functions za inicijalizaciju kategorija, banera, popularnih proizvoda i bottom navigacije
        Functions functions = new Functions(binding, database);

        functions.initCategory();
        functions.initBaner();
        functions.initPopular(MainActivity.this, binding, 4);

        functions.setupBottomNavigationView(this);
        functions.setupSeeAllTextClickListener(this,seeAllText);
    }
}