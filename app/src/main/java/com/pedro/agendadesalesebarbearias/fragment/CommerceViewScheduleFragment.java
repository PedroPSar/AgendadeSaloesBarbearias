package com.pedro.agendadesalesebarbearias.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.pedro.agendadesalesebarbearias.R;
import com.pedro.agendadesalesebarbearias.adapter.RvCommerceScheduleAdapter;
import com.pedro.agendadesalesebarbearias.model.Professional;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceViewScheduleFragment extends Fragment {

    private RecyclerView rvEmployeesSchedule;
    private ArrayList<Professional> employeesList;

    public CommerceViewScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commerce_view_schedule, container, false);

        rvEmployeesSchedule = view.findViewById(R.id.rvCommerceSchedule);

        Professional p = new Professional();
        p.setName("Professional");
        p.setWork("Work");

        Professional p2 = new Professional();
        p2.setName("Prof2");
        p2.setWork("Work2");

        employeesList = new ArrayList<>();
        employeesList.add(p);
        employeesList.add(p2);

        RvCommerceScheduleAdapter adapter = new RvCommerceScheduleAdapter(getActivity(), employeesList);
        rvEmployeesSchedule.setAdapter(adapter);
        rvEmployeesSchedule.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
