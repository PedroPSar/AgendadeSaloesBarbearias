package com.pedro.agendadesalesebarbearias.fragment;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.adapter.RvServicesAdapter;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.SalaoBarbearia;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceViewMainFragment extends Fragment {

    private AppCompatImageView bgImg;
    private AppCompatImageView avatarImg;
    private AppCompatTextView txtRating;
    private AppCompatTextView txtCommerceName;
    private AppCompatImageButton btnEdit;
    private AppCompatImageButton btnAddServices;
    private AppCompatImageButton btnAddEmployess;
    private RecyclerView rvServices;
    private RecyclerView rvEmployess;
    private SalaoBarbearia commerceInfo;

    public CommerceViewMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commerce_view_main, container, false);

        // Get reference
        bgImg = view.findViewById(R.id.bgImg);
        avatarImg = view.findViewById(R.id.avatar_img);
        txtRating = view.findViewById(R.id.txtRating);
        txtCommerceName = view.findViewById(R.id.txtCommerceName);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnAddServices = view.findViewById(R.id.btnAddServices);
        btnAddEmployess = view.findViewById(R.id.btnAddEmployess);
        rvServices = view.findViewById(R.id.rvServices);
        rvEmployess = view.findViewById(R.id.rvEmployees);

        // Load info
        // Load bg img
        FirebaseControl.loadImgFromStorageIntoImageView(getActivity(), FirebaseControl.COMMERCE_DB,
                FirebaseControl.BG_IMG_NAME, bgImg);

        // Load avatar img
        FirebaseControl.loadImgFromStorageIntoImageView(getActivity(), FirebaseControl.COMMERCE_DB,
                FirebaseControl.AVATAR_IMG_NAME, avatarImg);

        commerceInfo = FirebaseControl.getCommerceInfo(getActivity());

        txtRating.setText(String.valueOf(commerceInfo.getRating()));
        txtCommerceName.setText(commerceInfo.getName());

        // Load services rv
        RvServicesAdapter servicesAdapter = new RvServicesAdapter(commerceInfo.getServices(), getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvServices.setAdapter(servicesAdapter);

        return view;
    }

}
