package com.example.conectados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private TextView btnCriar, btnEsqueceu;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

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
                finish();
            }
        });
        btnEsqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtEmail.getText().toString().isEmpty()
                        || txtSenha.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos",
                            Toast.LENGTH_SHORT).show();
                } else {
                    userLogin();
                }
            }
        });
    }

    private void userLogin() {
        final String email = txtEmail.getText().toString();
        final String senha = txtSenha.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Email e/ou senha incorretos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void setThings() {
        txtEmail = findViewById(R.id.activity_login_et_email);
        txtSenha = findViewById(R.id.activity_login_et_password);
        btnCriar = findViewById(R.id.activity_login_tv_criar);
        btnEsqueceu = findViewById(R.id.activity_login_tv_esqueceu);
        btnLogin = findViewById(R.id.activity_login_bt_login);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onStart() {
        if(firebaseUser != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }
}
