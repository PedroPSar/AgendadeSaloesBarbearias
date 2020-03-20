package com.pedro.agendadesalesebarbearias.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.adapter.RvEmployeesForAddAdapter;
import com.pedro.agendadesalesebarbearias.control.AppControl;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.Professional;
import com.pedro.agendadesalesebarbearias.model.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommerceViewAddServiceFullScreenDialogFragment extends DialogFragment {

    public static final String TAG = "FullScreenAddServiceDialog";
    private int serviceTime = 0;

    private AppCompatEditText editTextServiceName;
    private AppCompatEditText editTextServicePrice;
    private AppCompatSpinner spinnerServiceTime;
    private Button btnCancel;
    private Button btnAdd;
    private RecyclerView rvEmployees;

    static CommerceViewAddServiceFullScreenDialogFragment newInstance(){
        return new CommerceViewAddServiceFullScreenDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_service_layout, container, false);

        editTextServiceName = view.findViewById(R.id.editTextServiceName);
        editTextServicePrice = view.findViewById(R.id.editTextServicePrice);
        spinnerServiceTime = view.findViewById(R.id.spinnerServiceTime);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnAdd = view.findViewById(R.id.btnAdd);
        rvEmployees = view.findViewById(R.id.rvEmployeesForAdd);

        // Load rvEmployees for Add and Set size in professional array
        FirebaseControl.setEmployeesInRvEmployeesForAdd(getActivity(), rvEmployees);

        // Load Time Spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item,
                Service.TIMES);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServiceTime.setAdapter(arrayAdapter);
        spinnerServiceTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                serviceTime = AppControl.convertServiceTimeInUnit(Service.TIMES[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),
                        getActivity().getString(R.string.toast_time_spinner_nothing_selected_txt),
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppControl.professionals == null){
                    Toast.makeText(getActivity(), getString(R.string.toast_text_employees_is_empty),
                            Toast.LENGTH_SHORT).show();
                }else{

                    List<Professional> list = Arrays.asList(AppControl.professionals);
                    ArrayList<Professional> employeesList = new ArrayList<>();

                    for (Professional p : list) {
                        if(p != null){
                            employeesList.add(p);
                        }
                    }

                    if(employeesList.size() != 0){
                        Service service = new Service();
                        service.setName(editTextServiceName.getText().toString());
                        service.setPrice(editTextServicePrice.getText().toString());
                        service.setServiceTime(serviceTime);
                        service.setProfessionals(employeesList);

                        // Add Service in commerce database in firebase
                        FirebaseControl.setServiceInCurrentCommerce(getActivity(), service);

                        dismiss();
                    }else{
                        Toast.makeText(getActivity(), getString(R.string.toast_text_employees_is_empty),
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        return view;
    }

}
