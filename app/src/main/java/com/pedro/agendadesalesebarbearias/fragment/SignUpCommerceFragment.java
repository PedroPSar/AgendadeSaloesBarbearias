package com.pedro.agendadesalesebarbearias.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.activity.CommerceUserMainActivity;
import com.pedro.agendadesalesebarbearias.activity.MainActivity;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.Address;
import com.pedro.agendadesalesebarbearias.model.SalaoBarbearia;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpCommerceFragment extends Fragment {

    private AppCompatEditText edTxtCommerceName;
    private AppCompatEditText edTxtEmail;
    private AppCompatEditText edTxtPassword;
    private AppCompatEditText edTxtTel;
    private AppCompatEditText edTxtOpTime;
    private AppCompatEditText edTxtCloseTime;
    private AppCompatEditText edTxtStreet;
    private AppCompatEditText edTxtNum;
    private AppCompatEditText edTxtDistrict;
    private AppCompatEditText edTxtCity;
    private AppCompatSpinner spinnerState;
    private RadioGroup radioGroup;
    private AppCompatRadioButton radioButtonBeautyParlor;
    private AppCompatRadioButton radioButtonBarberShop;
    private String type = "";
    private String commerceState = "";

    private LinearLayoutCompat llSignIn;
    private Button btnSignUpCommerce;

    public SignUpCommerceFragment() {
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
        edTxtTel = view.findViewById(R.id.editTextTel);
        edTxtOpTime = view.findViewById(R.id.editTextOpTime);
        edTxtCloseTime = view.findViewById(R.id.editTextEdTime);
        edTxtStreet = view.findViewById(R.id.editTextStreet);
        edTxtNum = view.findViewById(R.id.editTextNumber);
        edTxtDistrict = view.findViewById(R.id.editTextDistrict);
        edTxtCity = view.findViewById(R.id.editTextCity);
        spinnerState = view.findViewById(R.id.spinnerState);

        radioGroup = view.findViewById(R.id.radioGroup_type);
        llSignIn = view.findViewById(R.id.ll_signIn);
        btnSignUpCommerce = view.findViewById(R.id.btnSignUpCommerce);

        radioButtonBeautyParlor = view.findViewById(R.id.radioBtn_beautyParlor);
        radioButtonBarberShop = view.findViewById(R.id.radioBtn_barbershop);

        type = SalaoBarbearia.BEAUTY_PARLOR_TYPE;

        loadSpinner();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioBtn_beautyParlor:
                        type = SalaoBarbearia.BEAUTY_PARLOR_TYPE;
                        break;

                    case R.id.radioBtn_barbershop:
                        type = SalaoBarbearia.BARBERSHOP_TYPE;
                        break;
                }
            }
        });

        llSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUpCommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SalaoBarbearia commerce = new SalaoBarbearia();
                commerce.setName(edTxtCommerceName.getText().toString());
                commerce.setEmail(edTxtEmail.getText().toString());
                commerce.setPassword(edTxtPassword.getText().toString());
                commerce.setTel(edTxtTel.getText().toString());
                commerce.setOpeningTime(edTxtOpTime.getText().toString());
                commerce.setClosingTime(edTxtCloseTime.getText().toString());
                commerce.setType(type);

                Address address = new Address();
                address.setStreet(edTxtStreet.getText().toString());
                address.setHouseNumber(edTxtNum.getText().toString());
                address.setDistrict(edTxtDistrict.getText().toString());
                address.setCity(edTxtCity.getText().toString());
                address.setState(commerceState);

                commerce.setAddress(address);

                FirebaseControl.signUpCommerce(getActivity(), commerce);

            }
        });

    }

    private void loadSpinner(){
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, Address.states);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerState.setAdapter(spinnerAdapter);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                commerceState = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
