package com.example.aplikasisensus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class inputdata2 extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button btnsubmit;
    EditText prov,kota,kecamantan,kelurahan,rt,rw,jml_kk,jml_pen;


    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata2);
        db = FirebaseFirestore.getInstance();

        prov        = findViewById(R.id.tprovinsi);
        kota        = findViewById(R.id.tkota);
        kecamantan  = findViewById(R.id.tkecamatan);
        kelurahan   = findViewById(R.id.tkelurahan);
        rt          = findViewById(R.id.trt);
        rw          = findViewById(R.id.trw);
        jml_kk      = findViewById(R.id.tjml_kel);
        jml_pen     = findViewById(R.id.tjumlah_rt);


        btnsubmit = findViewById(R.id.btnMasuk);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Create a new user with a first and last name
                final Map<String, Object> user = new HashMap<>();
                user.put("prov", prov.getText().toString());
                user.put("kota", kota.getText().toString());
                user.put("kecamatan", kecamantan.getText().toString());
                user.put("kelurahan", kelurahan.getText().toString());
                user.put("rt", rt.getText().toString());
                user.put("rw", rw.getText().toString());
                user.put("jml_kk", jml_kk.getText().toString());
                user.put("jml_pen", jml_pen.getText().toString());

// Add a new document with a generated ID
                db.collection("sensus")
                        .whereEqualTo("prov", prov.getText().toString())
                        .whereEqualTo("kota", kota.getText().toString())
                        .whereEqualTo("kecamatan", kecamantan.getText().toString())
                        .whereEqualTo("kelurahan", kelurahan.getText().toString())
                        .whereEqualTo("rt", rt.getText().toString())
                        .whereEqualTo("rw", rw.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                QuerySnapshot snapshot = task.getResult();
                                System.out.println("Masuk");
                                System.out.println("Hasil: " + snapshot.getDocuments());
                                if (snapshot.getDocuments().size() > 0) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Duplikasi data ditemukan", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP, 0, 0);
                                    toast.show();
                                } else {

                                    db.collection("sensus")
                                            .add(user)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    Toast toast = Toast.makeText(getApplicationContext(), "Berhasil Membuat Data Baru", Toast.LENGTH_LONG);
                                                    toast.setGravity(Gravity.TOP, 0, 0);
                                                    if (task.isSuccessful()) {
                                                        toast.show();
                                                    } else {
                                                        toast.setText("Gagal menyimpan data");
                                                        toast.show();
                                                    }
                                                }
                                            });
                                }
                            }
                        });
            }
        });
    }
}
