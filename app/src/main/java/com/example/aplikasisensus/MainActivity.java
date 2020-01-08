package com.example.aplikasisensus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.tUsername);
        password = (EditText) findViewById(R.id.tPassword);
        btnlogin = (Button) findViewById(R.id.btnMasuk);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameKey = username.getText().toString();
                String passwordkey = password.getText().toString();

                if (usernameKey.equals("admin") && passwordkey.equals("admin")) {

                    Toast.makeText(getApplicationContext(), "Login Success",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,MainMenu2.class);
                    MainActivity.this.startActivity(intent);
                    finish();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Username/Password yang anda masukan salah ")
                            .setNegativeButton("Coba Lagi !",null).create().show();
                }
            }
        });
    }
}
