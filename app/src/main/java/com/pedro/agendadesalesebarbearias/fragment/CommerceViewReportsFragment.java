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
public class CommerceViewReportsFragment extends Fragment {


    public CommerceViewReportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commerce_view_reports, container, false);

        return view;
    }

}
