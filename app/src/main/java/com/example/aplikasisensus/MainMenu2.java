package com.example.aplikasisensus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainMenu2 extends AppCompatActivity {

    Button btn_msk_data;
    Button btn_lihat;
    TextView user;
    String user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        btn_msk_data = (Button) findViewById(R.id.masuk_data);
        btn_lihat = (Button) findViewById(R.id.lihat);
        user = (TextView) findViewById(R.id.user);
        user1 = getIntent().getExtras().getString("");
        user.setText(user1);

        btn_msk_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenu2.this,inputdata2.class);
                MainMenu2.this.startActivity(intent);
            }
        });

        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenu2.this,text_view.class);
                MainMenu2.this.startActivity(intent);
            }
        });

    }
}
