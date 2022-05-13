package com.pfe.agilestore.ui.authenfication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pfe.agilestore.ApiClient;
import com.pfe.agilestore.MainActivity;
import com.pfe.agilestore.R;
import com.google.firebase.database.DatabaseReference;
import com.pfe.agilestore.RegisterRequest;
import com.pfe.agilestore.RegisterResponse;
import com.pfe.agilestore.ui.gallery.RendezVousFragment;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signupActivity extends AppCompatActivity {


    private static final String url="http://192.168.1.5/api/users.json";
    EditText name,Phone,Password,coPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name =findViewById(R.id.name);
        Phone =findViewById(R.id.Phone);
        Password =findViewById(R.id.Password);
        coPassword =findViewById(R.id.coPassword);
        final Button signup = findViewById(R.id.signup);
        final TextView deja =findViewById(R.id.deja);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameTxt = name.getText().toString();
                final String PhoneTxt = Phone.getText().toString();
                final String PasswordTxt = Password.getText().toString();
                final String copasswordTxt = coPassword.getText().toString();


                if(nameTxt.isEmpty() || PhoneTxt.isEmpty() || PasswordTxt.isEmpty() || copasswordTxt.isEmpty() ){
                    Toast.makeText(signupActivity.this,"complèter vos informations",Toast.LENGTH_SHORT).show();
                }else if(!PasswordTxt.equals(copasswordTxt)){
                    Toast.makeText(signupActivity.this,"vérifier votre mot de passe",Toast.LENGTH_SHORT).show();
                }else if( !(PhoneTxt.matches("[259][0-9]{7}"))){
                                Toast.makeText(signupActivity.this,"numéro incorrécte",Toast.LENGTH_SHORT).show();
                }else{
                    RegisterRequest registerRequest=new RegisterRequest();
                    registerRequest.setFullname(nameTxt);
                    registerRequest.setPassword(PasswordTxt);
                    registerRequest.setTel(PhoneTxt);
                    Log.d("requestachref",nameTxt+PasswordTxt+PhoneTxt);
                    registerUser(registerRequest);

                }

            }
        });

        deja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall= ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(signupActivity.this,"succée",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signupActivity.this, loginActivity.class));
                    finish();

                }else{
                    Toast.makeText(signupActivity.this,"failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(signupActivity.this,"??????",Toast.LENGTH_SHORT).show();

            }
        });
    }
}