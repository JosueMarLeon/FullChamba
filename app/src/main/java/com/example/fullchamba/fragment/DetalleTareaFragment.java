package com.example.fullchamba.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fullchamba.R;
import com.example.fullchamba.entidad.Tarea;

public class DetalleTareaFragment extends Fragment {

    Tarea tarea;

    TextView textViewIdDetalleTarea;
    TextView textViewNombreDetalleTarea;
    TextView textViewComplejidadDetalleTarea;
    TextView textViewActividadDetalleTarea;
    TextView textViewEstadoDetalleTarea;
    TextView textViewClienteDetalleTarea;
    TextView textViewProyectoDetalleTarea;
    TextView textViewFechaDetalleTarea;
    TextView textViewCostoDetalleTarea;
    TextView textViewHoraDetalleTarea;
    TextView textViewTotalDetalleTarea;


    public DetalleTareaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_tarea, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        tarea = null;

        if (bundle != null) {
            tarea = (Tarea) bundle.getSerializable("tarea");
            Log.w("respuesta", "Datos encontrados");
        } else {
            tarea = new Tarea();
            Log.w("respuesta", "Datos no encontrados");
        }

        textViewIdDetalleTarea = view.findViewById(R.id.textViewIdDetalleTarea);
        textViewNombreDetalleTarea = view.findViewById(R.id.textViewNombreDetalleTarea);
        textViewComplejidadDetalleTarea = view.findViewById(R.id.textViewComplejidadDetalleTarea);
        textViewActividadDetalleTarea = view.findViewById(R.id.textViewActividadDetalleTarea);
        textViewEstadoDetalleTarea = view.findViewById(R.id.textViewEstadoDetalleTarea);
        textViewClienteDetalleTarea = view.findViewById(R.id.textViewClienteDetalleTarea);
        textViewProyectoDetalleTarea = view.findViewById(R.id.textViewProyectoDetalleTarea);
        textViewFechaDetalleTarea = view.findViewById(R.id.textViewFechaDetalleTarea);
        textViewCostoDetalleTarea = view.findViewById(R.id.textViewCostoDetalleTarea);
        textViewHoraDetalleTarea = view.findViewById(R.id.textViewHoraDetalleTarea);
        textViewTotalDetalleTarea = view.findViewById(R.id.textViewTotalDetalleTarea);

        textViewIdDetalleTarea.setText(String.valueOf(tarea.getId()));
        textViewNombreDetalleTarea.setText(tarea.getNombre());
        textViewComplejidadDetalleTarea.setText(tarea.getComplejidad());
        textViewActividadDetalleTarea.setText(String.valueOf(tarea.getActividad()));
        textViewEstadoDetalleTarea.setText(String.valueOf(tarea.getEstado()));
        textViewClienteDetalleTarea.setText(tarea.getCliente());
        textViewProyectoDetalleTarea.setText(tarea.getProyecto());
        textViewFechaDetalleTarea.setText(tarea.getFecha());
        textViewCostoDetalleTarea.setText(String.valueOf(tarea.getCosto()));
        textViewHoraDetalleTarea.setText(String.valueOf(tarea.getHoras()));
        textViewTotalDetalleTarea.setText(String.valueOf(tarea.getCosto()*tarea.getHoras()));
    }
}