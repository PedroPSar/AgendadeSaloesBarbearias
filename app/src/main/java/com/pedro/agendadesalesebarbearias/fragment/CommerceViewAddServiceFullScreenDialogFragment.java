package com.pedro.agendadesalesebarbearias.fragment;

import android.os.Bundle;
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
import androidx.fragment.app.DialogFragment;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.control.AppControl;
import com.pedro.agendadesalesebarbearias.control.FirebaseControl;
import com.pedro.agendadesalesebarbearias.model.Service;

public class CommerceViewAddServiceFullScreenDialogFragment extends DialogFragment {

    public static final String TAG = "FullScreenAddServiceDialog";
    private int serviceTime = 0;

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

        final AppCompatEditText editTextServiceName = view.findViewById(R.id.editTextServiceName);
        final AppCompatEditText editTextServicePrice = view.findViewById(R.id.editTextServicePrice);
        AppCompatSpinner spinnerServiceTime = view.findViewById(R.id.spinnerServiceTime);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnAdd = view.findViewById(R.id.btnAdd);

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

                Service service = new Service();
                service.setName(editTextServiceName.getText().toString());
                service.setPrice(editTextServicePrice.getText().toString());
                service.setServiceTime(serviceTime);
                service.setProfessionals(AppControl.professionals);

                // Add Service in commerce database in firebase
                FirebaseControl.setServiceInCurrentCommerce(getActivity(), service);

                dismiss();
            }
        });
        return view;
    }

}
