package com.pfe.agilestore.ui.gallery;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pfe.agilestore.MainActivity;
import com.pfe.agilestore.R;

import java.util.Calendar;
import java.util.HashMap;

public class RendezVousFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl
            ("https://agilestore-17cd5-default-rtdb.firebaseio.com/");

    EditText tvDate;
    EditText tvHeurs;
    EditText tvMinute;
    Button conf;
    DatePickerDialog.OnDateSetListener setListener;
    String Phonetxt;
    TextView wa9t;
    TextView ktiba;

    private RendezVousViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_rendez_vous, container, false);
        tvDate=root.findViewById(R.id.tv_date);
        tvHeurs= root.findViewById(R.id.tv_heurs);
        tvMinute= root.findViewById(R.id.tv_minutes);
        conf =root.findViewById(R.id.conf);
        wa9t=root.findViewById(R.id.wa9t);
        ktiba=root.findViewById(R.id.ktiba);



        if (getArguments() != null){
           String Phonetxt1=getArguments().getString("keytel");
           Phonetxt=Phonetxt1;
        }

        MaterialDatePicker materialDatePicker= MaterialDatePicker.Builder.datePicker().setTitleText("select date").
                setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("users");

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "tag_Picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        tvDate.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap hashMap=new HashMap();
                int hr=tvHeurs.getText().hashCode();
                int min=tvMinute.getText().hashCode();
                String minute=tvMinute.getText().toString();
                String heurs=tvHeurs.getText().toString();

                String time = " "+heurs+" : "+minute+" ";

                    wa9t.setText(time);
                    hashMap.put("RDV",time);
                    databaseReference.child("99663322").child("tickets").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {

                        }
                    });



            }
        });


        return root;
    }
}