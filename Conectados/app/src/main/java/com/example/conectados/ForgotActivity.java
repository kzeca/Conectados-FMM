package com.example.conectados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    private EditText txtEmail;
    private Button btnEnviar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        setThings();
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setThings() {
        txtEmail = findViewById(R.id.activity_forgot_et_email);
        btnEnviar = findViewById(R.id.activity_forgot_bt_enviar);
        btnVoltar = findViewById(R.id.activity_forgot_bt_voltar);
    }
}
