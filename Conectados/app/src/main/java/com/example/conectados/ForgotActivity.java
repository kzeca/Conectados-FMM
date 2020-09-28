package com.example.conectados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    private EditText txtEmail;
    private Button btnEnviar, btnVoltar;
    private FirebaseAuth firebaseAuth;

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
                finish();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtEmail.getText().toString().isEmpty()
                        && txtEmail.getText().toString().contains("@")){
                    firebaseAuth.sendPasswordResetEmail(txtEmail.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),
                                                "Email para redefinição enviado!(Confira a caixa de spam)",
                                                Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else Toast.makeText(getApplicationContext(),
                                            "Informe um email válido",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                }else{
                    Toast.makeText(getApplicationContext(), "Ops! Algo de errado não está certo!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setThings() {
        txtEmail = findViewById(R.id.activity_forgot_et_email);
        btnEnviar = findViewById(R.id.activity_forgot_bt_enviar);
        btnVoltar = findViewById(R.id.activity_forgot_bt_voltar);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
