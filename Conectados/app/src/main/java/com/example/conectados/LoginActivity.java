package com.example.conectados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private TextView btnCriar, btnEsqueceu;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setThings();
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnEsqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setThings(){
        txtEmail = findViewById(R.id.activity_login_et_email);
        txtSenha = findViewById(R.id.activity_login_et_password);
        btnCriar = findViewById(R.id.activity_login_tv_criar);
        btnEsqueceu = findViewById(R.id.activity_login_tv_esqueceu);
        btnLogin = findViewById(R.id.activity_login_bt_login);
    }
}
