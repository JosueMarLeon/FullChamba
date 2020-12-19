package com.example.fullchamba.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fullchamba.R;

public class DashboardFragment extends Fragment {

    CardView cardViewProjectTimeDashboard;
    CardView cardViewProjectMoneyDashboard;
    CardView cardViewPrincipalActivityDashboard;
    CardView cardViewWorktimeDashboard;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardViewProjectTimeDashboard = view.findViewById(R.id.cardViewProjectTimeDashboard);
        cardViewProjectMoneyDashboard = view.findViewById(R.id.cardViewProjectMoneyDashboard);
        cardViewPrincipalActivityDashboard = view.findViewById(R.id.cardViewPrincipalActivityDashboard);
        cardViewWorktimeDashboard = view.findViewById(R.id.cardViewWorktimeDashboard);

        cardViewProjectTimeDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Seleccionó Horas por proyecto", Toast.LENGTH_SHORT).show();
                Log.w("selección", "Horas por proyecto");
            }
        });


        cardViewProjectMoneyDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Seleccionó Ingresos por proyecto", Toast.LENGTH_SHORT).show();
                Log.w("selección", "Ingresos por proyecto");
            }
        });

        cardViewPrincipalActivityDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Seleccionó Actividades principales", Toast.LENGTH_SHORT).show();
                Log.w("selección", "Actividades principales");
            }
        });

        cardViewWorktimeDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Seleccionó Horas trabajadas", Toast.LENGTH_SHORT).show();
                Log.w("selección", "Horas trabajadas");
            }
        });

    }

}