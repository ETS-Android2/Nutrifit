package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentAgeBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AgeFragment extends Fragment {
    EditText age;
    ImageView incage,decage;
    private ExtendedFloatingActionButton next,back;
    private FragmentAgeBinding binding;
    RegisterMain registerMain;

    int age1;
    String age2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAgeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        age = rootView.findViewById(R.id.fragment_age_currentAge);
        incage=rootView.findViewById(R.id.fragment_age_incrementAge);
        decage=rootView.findViewById(R.id.fragment_age_decrementAge);
        next= rootView.findViewById(R.id.age_next);
        back=rootView.findViewById(R.id.age_back);


        incage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(age.getText().toString().equals(""))
                    age1 = 1;
                else
                    age1= Integer.parseInt(age.getText().toString()) + 1;
                age2=String.valueOf(age1);
                age.setText(age2);
            }
        });
        decage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(age1>0) {
                    age1= Integer.parseInt(age.getText().toString()) - 1;
                    age2=String.valueOf(age1);
                    age.setText(age2);
                }
            }
        });

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                age2=age.getText().toString();
                if(age2.equals("0") || age2.equals(""))
                {
                    Toast.makeText(getContext(), "Age cannot be 0", Toast.LENGTH_SHORT).show();
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new WeightFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                }
                registerMain.myEdit.putString("age", age.getText().toString());
                registerMain.myEdit.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new GenderFragment()).addToBackStack(null);
                fragmentManager.commit();

            }
        });


        return rootView;
    }
}