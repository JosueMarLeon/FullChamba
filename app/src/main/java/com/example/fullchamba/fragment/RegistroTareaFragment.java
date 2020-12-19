package com.example.fullchamba.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fullchamba.R;
import com.example.fullchamba.entidad.Empleado;
import com.example.fullchamba.interfaces.ComunicaFragment;

import org.json.JSONObject;

public class RegistroTareaFragment extends Fragment {

    Empleado empleado;

    EditText editTextNombreRegistroTarea;
    EditText editTextComplejidadRegistroTarea;
    EditText editTextHorasRegistroTarea;
    EditText editTextFechaRegistroTarea;
    EditText editTextEstadoRegistroTarea;
    EditText editTextProyecto_IdRegistroTarea;
    EditText editTextActividad_IdRegistroTarea;
    EditText editTextEmpleado_IdRegistroTarea;

    Button buttonRegistrarRegistroTarea;

    JsonObjectRequest jsonObjectRequest;

    String endpoint = "https://elliotgaramendi.000webhostapp.com/proyecto/fullchamba/controlador";

    ProgressDialog progressDialog;

    Activity activity;
    ComunicaFragment comunicaFragment;

    public RegistroTareaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_tarea, container, false);
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

        editTextNombreRegistroTarea = view.findViewById(R.id.editTextNombreRegistroTarea);
        editTextComplejidadRegistroTarea = view.findViewById(R.id.editTextComplejidadRegistroTarea);
        editTextHorasRegistroTarea = view.findViewById(R.id.editTextHorasRegistroTarea);
        editTextFechaRegistroTarea = view.findViewById(R.id.editTextFechaRegistroTarea);
        editTextEstadoRegistroTarea = view.findViewById(R.id.editTextEstadoRegistroTarea);
        editTextProyecto_IdRegistroTarea = view.findViewById(R.id.editTextProyecto_IdRegistroTarea);
        editTextActividad_IdRegistroTarea = view.findViewById(R.id.editTextActividad_IdRegistroTarea);
        editTextEmpleado_IdRegistroTarea = view.findViewById(R.id.editTextEmpleado_IdRegistroTarea);

        editTextEmpleado_IdRegistroTarea.setText(String.valueOf(empleado.getId()));

        editTextEmpleado_IdRegistroTarea.setEnabled(false);

        buttonRegistrarRegistroTarea = view.findViewById(R.id.buttonRegistrarRegistroTarea);
        buttonRegistrarRegistroTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarTareaEmpleado(v);
            }
        });

    }

    private void registrarTareaEmpleado(View view) {
        String nombre = editTextNombreRegistroTarea.getText().toString().trim();
        String complejidad = editTextComplejidadRegistroTarea.getText().toString().trim();
        String horas = editTextHorasRegistroTarea.getText().toString().trim();
        String fecha = editTextFechaRegistroTarea.getText().toString().trim();
        String estado = editTextEstadoRegistroTarea.getText().toString().trim();
        String proyecto_id = editTextProyecto_IdRegistroTarea.getText().toString().trim();
        String actividad_id = editTextActividad_IdRegistroTarea.getText().toString().trim();
        String empleado_id = editTextEmpleado_IdRegistroTarea.getText().toString().trim();

        String url = endpoint + "/ControladorTarea.php?accion=registrarTareaEmpleado" + "&nombre=" + nombre + "&complejidad=" + complejidad + "&horas=" + horas + "&fecha=" + fecha + "&estado=" + estado + "&proyecto_id=" + proyecto_id + "&actividad_id=" + actividad_id + "&empleado_id=" + empleado_id;

        if (nombre.isEmpty()) {
            editTextNombreRegistroTarea.setError("Complete los campos");
        } else if (complejidad.isEmpty()) {
            editTextComplejidadRegistroTarea.setError("Complete los campos");
        } else if (horas.isEmpty()) {
            editTextHorasRegistroTarea.setError("Complete los campos");
        } else if (fecha.isEmpty()) {
            editTextFechaRegistroTarea.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            editTextEstadoRegistroTarea.setError("Complete los campos");
        } else if (proyecto_id.isEmpty()) {
            editTextProyecto_IdRegistroTarea.setError("Complete los campos");
        } else if (actividad_id.isEmpty()) {
            editTextActividad_IdRegistroTarea.setError("Complete los campos");
        } else if (empleado_id.isEmpty()) {
            editTextEmpleado_IdRegistroTarea.setError("Complete los campos");
        } else {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Cargando");
            progressDialog.show();
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        boolean error = response.getBoolean("error");
                        String mensaje = response.getString("mensaje");
                        boolean data = response.getBoolean("data");
                        Log.w("error", String.valueOf(error));
                        Log.w("mensaje", mensaje);
                        Log.w("data", String.valueOf(data));
                        if (!error) {
                            editTextNombreRegistroTarea.setText("");
                            editTextComplejidadRegistroTarea.setText("");
                            editTextHorasRegistroTarea.setText("");
                            editTextFechaRegistroTarea.setText("");
                            editTextEstadoRegistroTarea.setText("");
                            editTextProyecto_IdRegistroTarea.setText("");
                            editTextActividad_IdRegistroTarea.setText("");
                            editTextEmpleado_IdRegistroTarea.setText("");
                            comunicaFragment.comunicarTareaFragment();
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Error: " + error + ", Mensaje: " + mensaje, Toast.LENGTH_SHORT).show();
                            Log.w("respuesta", "Error: " + error + ", Mensaje: " + mensaje);
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Error: " + error + ", Mensaje: " + mensaje, Toast.LENGTH_SHORT).show();
                            Log.w("respuesta", "Error: " + error + ", Mensaje: " + mensaje);
                        }
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.w("exception", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("error", String.valueOf(error.getMessage()));
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(jsonObjectRequest);
        }
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