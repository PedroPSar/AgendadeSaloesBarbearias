package com.pedro.agendadesalesebarbearias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.model.Professional;

import java.util.ArrayList;

public class RvEmployeesAdapter extends RecyclerView.Adapter<RvEmployeesAdapter.RvEmployeesAdapterViewHolder> {

    private ArrayList<Professional> employeesList;
    private Context context;

    public RvEmployeesAdapter(ArrayList<Professional> employeesList, Context context){
        this.employeesList = employeesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RvEmployeesAdapter.RvEmployeesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_employees_adapter_layout, parent, false);

        return new RvEmployeesAdapter.RvEmployeesAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RvEmployeesAdapter.RvEmployeesAdapterViewHolder holder, int position) {

        holder.txtEmployeeName.setText(employeesList.get(position).getName());
        holder.txtEmployeeWork.setText(employeesList.get(position).getWork());

    }

    @Override
    public int getItemCount() {
        if(employeesList != null){
            return employeesList.size();
        }else{
            return 0;
        }

    }

    public class RvEmployeesAdapterViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView txtEmployeeName;
        private AppCompatTextView txtEmployeeWork;

        public RvEmployeesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtEmployeeName = itemView.findViewById(R.id.txtEmployeeName);
            txtEmployeeWork = itemView.findViewById(R.id.txtEmployeeWork);

        }
    }
}
