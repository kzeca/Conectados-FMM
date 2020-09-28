package com.example.conectados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha, txtConfirmar, txtNome;
    private Button btnCriar, btnVoltar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setThings();
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setThings(){
        txtEmail = findViewById(R.id.activity_register_et_email);
        txtSenha = findViewById(R.id.activity_register_et_password);
        txtConfirmar = findViewById(R.id.activity_register_et_confirmar);
        txtNome = findViewById(R.id.activity_register_et_name);
        btnCriar = findViewById(R.id.activity_register_bt_create);
        btnVoltar = findViewById(R.id.activity_register_bt_back);
    }
}
