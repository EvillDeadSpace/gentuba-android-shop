package com.example.mainactivity.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainactivity.FunctionsToMain.Functions;
import com.example.mainactivity.R;
import com.example.mainactivity.databinding.ActivityMainBinding;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    FirebaseAuth auth;
    private ActivityMainBinding binding;

    TextView textView;
    Button button;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        auth = FirebaseAuth.getInstance();

        button = findViewById(R.id.logout);
        textView = findViewById(R.id.userDetails);

        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(Profile.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText("Welcome " + user.getEmail());
        }

        button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Profile.this, Login.class);
            startActivity(intent);
            finish();
        });



    }
}