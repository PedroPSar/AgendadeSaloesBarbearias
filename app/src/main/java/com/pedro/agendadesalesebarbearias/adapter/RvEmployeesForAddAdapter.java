package com.pedro.agendadesalesebarbearias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.control.AppControl;
import com.pedro.agendadesalesebarbearias.model.Professional;

import java.util.ArrayList;

public class RvEmployeesForAddAdapter extends RecyclerView.Adapter<RvEmployeesForAddAdapter.RvEmployeesForAddAdapterViewHolder> {

    private ArrayList<Professional> employeesList;
    private Context context;

    public RvEmployeesForAddAdapter(ArrayList<Professional> employeesList, Context context){
        this.employeesList = employeesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RvEmployeesForAddAdapter.RvEmployeesForAddAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_employees_for_add, parent, false);

        return new RvEmployeesForAddAdapter.RvEmployeesForAddAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RvEmployeesForAddAdapter.RvEmployeesForAddAdapterViewHolder holder, final int position) {

        final int fPosition = position;
        // Get info
        final Professional professional = new Professional();
        professional.setName(employeesList.get(position).getName());
        professional.setWork(employeesList.get(position).getWork());

        // Set info
        holder.txtEmployeeName.setText(employeesList.get(position).getName());
        holder.txtEmployeeWork.setText(employeesList.get(position).getWork());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppControl.professionals[position] = professional;
                }else{
                    AppControl.professionals[position] = null;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    public class RvEmployeesForAddAdapterViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView txtEmployeeName;
        private AppCompatTextView txtEmployeeWork;
        private AppCompatCheckBox checkBox;

        public RvEmployeesForAddAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtEmployeeName = itemView.findViewById(R.id.txtEmployeeName);
            txtEmployeeWork = itemView.findViewById(R.id.txtEmployeeWork);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
