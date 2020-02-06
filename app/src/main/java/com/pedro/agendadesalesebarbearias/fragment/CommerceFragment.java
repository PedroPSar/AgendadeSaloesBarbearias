package com.pedro.agendadesalesebarbearias.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceFragment extends Fragment {

    private AppCompatEditText edTxtCommerceName;
    private AppCompatEditText edTxtEmail;
    private AppCompatEditText edTxtPassword;

    private LinearLayoutCompat llSignIn;

    public CommerceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_commerce, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edTxtCommerceName = view.findViewById(R.id.editTxtCommerceName);
        edTxtEmail = view.findViewById(R.id.editTxtEmail);
        edTxtPassword = view.findViewById(R.id.editTextPassword);

        llSignIn = view.findViewById(R.id.ll_signIn);

        llSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((AppCompatRadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioBtn_beautyParlor:
                if (checked)

                break;
            case R.id.radioBtn_barbershop:
                if (checked)

                break;
        }
    }

}
