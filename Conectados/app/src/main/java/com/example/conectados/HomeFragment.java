package com.example.conectados;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class HomeFragment extends Fragment {
    private FirebaseUser firebaseUser;
    private TextView txtWelcome;
    private ImageView qrCode;
    private Button btnSair;
    private DatabaseReference databaseReference;

    private void generateQRCode() {
        QRGEncoder qrgEncoder = new QRGEncoder(firebaseUser.getUid(), null,
                QRGContents.Type.TEXT, 500);
        Bitmap bitmap = qrgEncoder.getBitmap();
        qrCode.setImageBitmap(bitmap);

    }

    private void setObjects(View view) {
        txtWelcome = view.findViewById(R.id.activity_home_tv_welcome);
        btnSair = view.findViewById(R.id.activity_home_bt_sair);
        qrCode = view.findViewById(R.id.activity_home_iv_qrCode);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setObjects(view);
        generateQRCode();
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().getFragmentManager().popBackStack();
                startActivity(intent);
                //FirebaseAuth.getInstance().signOut();
            }
        });
        return view;
    }
}
