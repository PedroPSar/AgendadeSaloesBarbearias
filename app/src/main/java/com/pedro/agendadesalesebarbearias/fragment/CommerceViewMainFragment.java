package com.pedro.agendadesalesebarbearias.fragment;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.adapter.RvEmployeesAdapter;
import com.pedro.agendadesalesebarbearias.adapter.RvServicesAdapter;
import com.pedro.agendadesalesebarbearias.control.AppControl;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.Professional;
import com.pedro.agendadesalesebarbearias.model.SalaoBarbearia;
import com.pedro.agendadesalesebarbearias.model.Service;


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
    private RecyclerView rvEmployees;
    private SalaoBarbearia commerceInfo;
    private int serviceTime = 0;

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
        rvEmployees = view.findViewById(R.id.rvEmployees);

        // Load info
        // Load bg img
        FirebaseControl.loadImgFromStorageIntoImageView(getActivity(), FirebaseControl.COMMERCE_DB,
                FirebaseControl.BG_IMG_NAME, bgImg);

        // Load avatar img
        FirebaseControl.loadImgFromStorageIntoImageView(getActivity(), FirebaseControl.COMMERCE_DB,
                FirebaseControl.AVATAR_IMG_NAME, avatarImg);

        commerceInfo = FirebaseControl.getCommerceInfo(getActivity());

        // Load rating and commerce name
        txtRating.setText(String.valueOf(commerceInfo.getRating()));
        txtCommerceName.setText(commerceInfo.getName());

        // Load services rv
        RvServicesAdapter servicesAdapter = new RvServicesAdapter(commerceInfo.getServices(), getActivity());
        RecyclerView.LayoutManager servicesLM = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvServices.setLayoutManager(servicesLM);
        rvServices.setAdapter(servicesAdapter);

        // Load employess
        RvEmployeesAdapter employeesAdapter = new RvEmployeesAdapter(commerceInfo.getProfessional(), getActivity());
        RecyclerView.LayoutManager  employeesLM = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvEmployees.setLayoutManager(employeesLM);
        rvEmployees.setAdapter(employeesAdapter);

        // Button addServices open Dialog
        btnAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = CommerceViewAddServiceFullScreenDialogFragment.newInstance();
                dialogFragment.show(getActivity().getSupportFragmentManager(),
                        CommerceViewAddServiceFullScreenDialogFragment.TAG);

            }
        });

        btnAddEmployess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                builder.setTitle(getActivity().getString(R.string.dialog_add_employees_title));

                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View dialogView = layoutInflater.inflate(R.layout.dialog_add_employee_layout, null);

                final AppCompatEditText editTextEmployeeName= dialogView.findViewById(R.id.editTextEmployeeName);
                final AppCompatEditText editTextEmployeeWork = dialogView.findViewById(R.id.editTextEmployeeWork);
                Button btnCancel = dialogView.findViewById(R.id.btnCancel);
                Button btnAdd = dialogView.findViewById(R.id.btnAdd);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Professional employee = new Professional();
                        employee.setName(editTextEmployeeName.getText().toString());
                        employee.setWork(editTextEmployeeWork.getText().toString());

                        FirebaseControl.setEmployeeInCurrentCommerce(getActivity(), employee);
                    }
                });

                builder.setView(dialogView);
                builder.show();
            }
        });

        return view;
    }

}
