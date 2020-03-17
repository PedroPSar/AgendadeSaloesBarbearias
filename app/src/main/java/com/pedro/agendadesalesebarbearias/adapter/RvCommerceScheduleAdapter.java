package com.pedro.agendadesalesebarbearias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.model.Professional;

import java.util.ArrayList;

public class RvCommerceScheduleAdapter extends RecyclerView.Adapter<RvCommerceScheduleAdapter.RvCommerceScheduleHolder> {

    private ArrayList<Professional> employeesList;
    private Context context;

    public RvCommerceScheduleAdapter(Context context, ArrayList<Professional> employeesList) {
        this.employeesList = employeesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RvCommerceScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_commerce_schedule, parent, false);


        return new RvCommerceScheduleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RvCommerceScheduleHolder holder, int position) {

        holder.employeeName.setText(employeesList.get(position).getName());
        holder.employeeWork.setText(employeesList.get(position).getWork());

    }

    @Override
    public int getItemCount() {
        if(employeesList != null){
            return employeesList.size();
        }else{
            return 0;
        }
    }

    public class RvCommerceScheduleHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView employeeName;
        private AppCompatTextView employeeWork;
        private CalendarView calendarView;

        public RvCommerceScheduleHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.lblEmployeeName);
            employeeWork = itemView.findViewById(R.id.lblEmployeeWork);
            calendarView = itemView.findViewById(R.id.calendarView);
        }
    }
}


