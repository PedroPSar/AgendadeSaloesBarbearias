package com.pedro.agendadesalesebarbearias.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedro.agendadesalesebarbearias.R;

public class ClientViewMainFragment extends Fragment {

    RecyclerView recyclerView;


    public ClientViewMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_client_user_main, container, false);

        recyclerView = view.findViewById(R.id.rv_main);



        return view;
    }

}
