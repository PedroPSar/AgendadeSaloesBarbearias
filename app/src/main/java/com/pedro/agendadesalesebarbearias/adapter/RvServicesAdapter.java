package com.pedro.agendadesalesebarbearias.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.agendadesalesebarbearias.ChooseProfessionalActivity;
import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.model.Service;

import java.util.ArrayList;

public class RvServicesAdapter extends RecyclerView.Adapter<RvServicesAdapter.RvServicesAdapterViewHolder> {

    private ArrayList<Service> servicesList;
    private Context context;

    public RvServicesAdapter(ArrayList<Service> services, Context context){
        this.servicesList = services;
        this.context = context;
    }

    @NonNull
    @Override
    public RvServicesAdapter.RvServicesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_services_adapter_layout, parent, false);

        return new RvServicesAdapter.RvServicesAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RvServicesAdapter.RvServicesAdapterViewHolder holder, int position) {

        holder.txtServiceName.setText(servicesList.get(position).getName());
        holder.txtServicePrice.setText(servicesList.get(position).getPrice());
        holder.btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChooseProfessionalActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class RvServicesAdapterViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView txtServiceName;
        private AppCompatTextView txtServicePrice;
        private AppCompatButton btnSchedule;

        public RvServicesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtServiceName = itemView.findViewById(R.id.txtServiceName);
            txtServicePrice = itemView.findViewById(R.id.txtServicePrice);
            btnSchedule = itemView.findViewById(R.id.btnSchedule);
        }
    }
}
