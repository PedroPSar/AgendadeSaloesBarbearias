package com.pedro.agendadesalesebarbearias.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedro.agendadesalesebarbearias.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceViewMainFragment extends Fragment {


    public CommerceViewMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commerce_view_main, container, false);

        return view;
    }

}