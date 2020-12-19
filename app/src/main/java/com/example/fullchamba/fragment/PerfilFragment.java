package com.example.fullchamba.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fullchamba.R;
import com.example.fullchamba.entidad.Empleado;
import com.example.fullchamba.interfaces.ComunicaFragment;

public class PerfilFragment extends Fragment {

    Empleado empleado;

    TextView textViewIdPerfil;
    TextView textViewNombrePerfil;
    TextView textViewApellidoPerfil;
    TextView textViewCelularPerfil;
    TextView textViewCorreoPerfil;
    TextView textViewClavePerfil;
    TextView textViewEstadoPerfil;
    TextView textViewCargo_IdPerfil;

    Activity activity;
    ComunicaFragment comunicaFragment;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        empleado = null;

        if (bundle != null) {
            empleado = (Empleado) bundle.getSerializable("empleado");
            Log.w("respuesta", "Datos encontrados");
        } else {
            empleado = new Empleado();
            Log.w("respuesta", "Datos no encontrados");
        }

        textViewIdPerfil = view.findViewById(R.id.textViewIdPerfil);
        textViewNombrePerfil = view.findViewById(R.id.textViewNombrePerfil);
        textViewApellidoPerfil = view.findViewById(R.id.textViewApellidoPerfil);
        textViewCelularPerfil = view.findViewById(R.id.textViewCelularPerfil);
        textViewCorreoPerfil = view.findViewById(R.id.textViewCorreoPerfil);
        textViewClavePerfil = view.findViewById(R.id.textViewClavePerfil);
        textViewEstadoPerfil = view.findViewById(R.id.textViewEstadoPerfil);
        textViewCargo_IdPerfil = view.findViewById(R.id.textViewCargo_IdPerfil);

        textViewIdPerfil.setText(String.valueOf(empleado.getId()));
        textViewNombrePerfil.setText(empleado.getNombre());
        textViewApellidoPerfil.setText(empleado.getApellido());
        textViewCelularPerfil.setText(empleado.getCelular());
        textViewCorreoPerfil.setText(empleado.getCorreo());
        textViewClavePerfil.setText(empleado.getClave());
        textViewEstadoPerfil.setText(String.valueOf(empleado.getEstado()));
        textViewCargo_IdPerfil.setText(String.valueOf(empleado.getCargo_Id()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicaFragment.comunicarActualizaEmpleado();
                Toast.makeText(getContext(), "Seleccionó Actualizar", Toast.LENGTH_SHORT).show();
                Log.w("selección", "Actualizar");
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.activity = (Activity) context;
            comunicaFragment = (ComunicaFragment) this.activity;
        }

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}