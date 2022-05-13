package com.pfe.agilestore.ui.authenfication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pfe.agilestore.MainActivity;
import com.pfe.agilestore.R;
import com.pfe.agilestore.ui.gallery.RendezVousFragment;
import com.pfe.agilestore.ui.home.ServicesFragment;

public class loginActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl
            ("https://agilestore-17cd5-default-rtdb.firebaseio.com/");

    private EditText phone,mdp;
    private Button conx,newcompte;
    private FirebaseAuth mAth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        phone = findViewById(R.id.Phone);
        mdp = findViewById(R.id.Password);
        conx = findViewById(R.id.connexion);
        newcompte = findViewById(R.id.newCompte);



        mAth = FirebaseAuth.getInstance();
        conx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = mdp.getText().toString();
                String tel = phone.getText().toString();



                if(TextUtils.isEmpty(tel) || TextUtils.isEmpty(pass)){
                    Toast.makeText(loginActivity.this,"complète vos informations",Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(tel)){

                                Bundle datas = new Bundle();
                                RendezVousFragment kh =new RendezVousFragment();
                                ServicesFragment bo =new ServicesFragment();
                                datas.putString("keytel",tel);
                                kh.setArguments(datas);
                                bo.setArguments(datas);

                                final String getPassword=snapshot.child(tel).child("Password").getValue(String.class);
                                if (getPassword.equals(pass)) {
                                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                                    finish();
                                }else{

                                   Toast.makeText(loginActivity.this,"Mot de passe incorrécte",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(loginActivity.this, "vérifier votre numéro", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });


        newcompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,signupActivity.class));
            }
        });





    }

}