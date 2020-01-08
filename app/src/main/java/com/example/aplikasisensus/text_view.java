package com.example.aplikasisensus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class text_view extends AppCompatActivity {

    RecyclerviewAdapter adapter;
    private static final String TAG = "MainActivity";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        final ArrayList<String> HasilSensus = new ArrayList<>();
        final RecyclerView recyclerView = findViewById(R.id.MyRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(text_view.this));

        db = FirebaseFirestore.getInstance();
        db.collection("sensus")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                HasilSensus.add(
                                        "\n" + "provinsi : " + document.getData().get("prov").toString() +
                                        "\n" + "kota : " + document.getData().get("kota").toString() +
                                        "\n" + "kecamatan : " + document.getData().get("kecamatan").toString() +
                                        "\n" + "kelurahan : " + document.getData().get("kelurahan").toString() +
                                        "\n" + "RW : " + document.getData().get("rw").toString() +
                                        "\n" + "RT : " + document.getData().get("rt").toString() +
                                        "\n" + "Jumlah Kepala keluarga : " + document.getData().get("jml_kk").toString() +
                                        "\n" + "Jumlah Penduduk : " + document.getData().get("jml_pen").toString() + "\n");
                            }
                            adapter = new RecyclerviewAdapter(text_view.this, HasilSensus);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(text_view.this, "Error getting documents.", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}
