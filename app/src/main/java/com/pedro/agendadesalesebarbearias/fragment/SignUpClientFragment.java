package com.pedro.agendadesalesebarbearias.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedro.agendadesalesebarbearias.R;


 public class SignUpClientFragment extends Fragment {

    public SignUpClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up_client, container, false);


        // Inflate the layout for this fragment
        return view;
    }


}
