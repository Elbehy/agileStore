package com.pfe.agilestore.ui.home;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pfe.agilestore.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class ServicesFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl
            ("https://agilestore-17cd5-default-rtdb.firebaseio.com/");


    private ServicesViewModel homeViewModel;

    Button ticketbv;
    Button ticketcb;
    Button ticketcp;
    int nbv=0;
    int ncb=0;
    int ncp=0;
    TextView affichbv;
    TextView affichcb;
    TextView affichcp;
    CardView cardbv;
    CardView cardcb;
    CardView cardcp;
    private DatabaseReference rootDatabaseref;
    private String Phonetxt1;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View myFragment = inflater.inflate(R.layout.fragment_servies, container, false);
        affichbv = myFragment.findViewById(R.id.affichbv);
        affichcb = myFragment.findViewById(R.id.affichcb);
        affichcp = myFragment.findViewById(R.id.affichcp);
        ticketbv = myFragment.findViewById(R.id.ticketbv);
        ticketcb = myFragment.findViewById(R.id.ticketcb);
        ticketcp = myFragment.findViewById(R.id.ticketcp);
        cardbv=myFragment.findViewById(R.id.cardbv);
        cardcb=myFragment.findViewById(R.id.cardcb);
        cardcp=myFragment.findViewById(R.id.cardcp);
        final TextView bv= myFragment.findViewById(R.id.bv);
        final TextView cb = myFragment.findViewById(R.id.cb);
        final TextView cp= myFragment.findViewById(R.id.cp);


        Bundle datas =getArguments();
        if (datas != null){
            Phonetxt1=datas.getString("keytel");
        }


        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");

        ticketbv.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View view) {

                nbv++;
                String ktiba1="BV"+Integer.toString(nbv);
                affichbv.setText(ktiba1);
                visible= !visible;
                cardbv.setVisibility(visible ? View.VISIBLE: View.GONE);

                HashMap hashMap=new HashMap();
                hashMap.put("BV",nbv);

                databaseReference.child("99663322").child("tickets").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });


            }
        });

        ticketcb.setOnClickListener(new View.OnClickListener() {
            boolean visibled;
            @Override
            public void onClick(View view) {
                ncb++;
                String ktiba1="CB"+Integer.toString(ncb);
                affichcb.setText(ktiba1);
                visibled= !visibled;
                cardcb.setVisibility(visibled ? View.VISIBLE: View.GONE);

                HashMap hashMap=new HashMap();
                hashMap.put("CB",ncb);

                databaseReference.child("99663322").child("tickets").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
            }
        });

        ticketcp.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View view) {
                ncp++;
                String ktiba1="CP"+Integer.toString(ncp);
                affichcp.setText(ktiba1);
                visible= !visible;
                cardcp.setVisibility(visible ? View.VISIBLE: View.GONE);

                HashMap hashMap=new HashMap();
                hashMap.put("CP",ncp);

                databaseReference.child("99663322").child("tickets").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
            }
        });
        return myFragment;


    }


    }
