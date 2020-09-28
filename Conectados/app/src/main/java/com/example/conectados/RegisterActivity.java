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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.awt.font.TextAttribute;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha, txtConfirmar, txtNome;
    private Button btnCriar, btnVoltar;
    FirebaseAuth firebaseAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setObjects();
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
                if(txtEmail.getText().toString().isEmpty()
                || txtSenha.getText().toString().isEmpty()
                || txtConfirmar.getText().toString().isEmpty()
                || txtNome.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if(!txtSenha.getText().toString().equals(txtConfirmar.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Os campos de senha não coincidem!",
                                Toast.LENGTH_SHORT).show();
                    } else{
                        registerUser();
                    }
                }
            }
        });
    }

    private void registerUser() {
        final String nome = txtNome.getText().toString();
        final String email = txtEmail.getText().toString();
        final String senha = txtSenha.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            DatabaseReference database = FirebaseDatabase.getInstance()
                                    .getReference("Alunos")
                                    .child(firebaseAuth.getCurrentUser().getUid());
                            database.child("name").setValue(nome);
                            Toast.makeText(getApplicationContext(), "Seus dados foram salvos!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void setObjects(){
        txtEmail = findViewById(R.id.activity_register_et_email);
        txtSenha = findViewById(R.id.activity_register_et_password);
        txtConfirmar = findViewById(R.id.activity_register_et_confirmar);
        txtNome = findViewById(R.id.activity_register_et_name);
        btnCriar = findViewById(R.id.activity_register_bt_create);
        btnVoltar = findViewById(R.id.activity_register_bt_back);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
