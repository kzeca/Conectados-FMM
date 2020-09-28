package com.example.conectados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class HomeActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private TextView txtWelcome;
    private ImageView qrCode;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setObjects();
        generateQRCode();
    }

    private void generateQRCode() {
        QRGEncoder qrgEncoder = new QRGEncoder(firebaseUser.getUid(), null,
                QRGContents.Type.TEXT, 500);
        Bitmap bitmap = qrgEncoder.getBitmap();
        qrCode.setImageBitmap(bitmap);

    }

    private void setObjects() {
        txtWelcome = findViewById(R.id.activity_home_tv_welcome);
        qrCode = findViewById(R.id.activity_home_iv_qrCode);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nome = dataSnapshot.child("Alunos").child(firebaseUser.getUid())
                        .child("name").getValue(String.class);
                txtWelcome.setText("Bem-vindo, "+nome+"!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
