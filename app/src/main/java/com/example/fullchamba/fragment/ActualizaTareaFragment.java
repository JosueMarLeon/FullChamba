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
import com.example.fullchamba.entidad.Tarea;
import com.example.fullchamba.interfaces.ComunicaFragment;

import org.json.JSONObject;

public class ActualizaTareaFragment extends Fragment {

    Tarea tarea;

    EditText editTextIdActualizaTarea;
    EditText editTextNombreActualizaTarea;
    EditText editTextComplejidadActualizaTarea;
    EditText editTextHorasActualizaTarea;
    EditText editTextFechaActualizaTarea;
    EditText editTextEstadoActualizaTarea;
    EditText editTextProyecto_IdActualizaTarea;
    EditText editTextActividad_IdActualizaTarea;
    EditText editTextEmpleado_IdActualizaTarea;

    Button buttonActualizarActualizaTarea;

    JsonObjectRequest jsonObjectRequest;

    String endpoint = "https://elliotgaramendi.000webhostapp.com/proyecto/fullchamba/controlador";

    ProgressDialog progressDialog;

    Activity activity;
    ComunicaFragment comunicaFragment;

    public ActualizaTareaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actualiza_tarea, container, false);
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

        editTextIdActualizaTarea = view.findViewById(R.id.editTextIdActualizaTarea);
        editTextNombreActualizaTarea = view.findViewById(R.id.editTextNombreActualizaTarea);
        editTextComplejidadActualizaTarea = view.findViewById(R.id.editTextComplejidadActualizaTarea);
        editTextHorasActualizaTarea = view.findViewById(R.id.editTextHorasActualizaTarea);
        editTextFechaActualizaTarea = view.findViewById(R.id.editTextFechaActualizaTarea);
        editTextEstadoActualizaTarea = view.findViewById(R.id.editTextEstadoActualizaTarea);
        editTextProyecto_IdActualizaTarea = view.findViewById(R.id.editTextProyecto_IdActualizaTarea);
        editTextActividad_IdActualizaTarea = view.findViewById(R.id.editTextActividad_IdActualizaTarea);
        editTextEmpleado_IdActualizaTarea = view.findViewById(R.id.editTextEmpleado_IdActualizaTarea);

        editTextIdActualizaTarea.setText(String.valueOf(tarea.getId()));
        editTextNombreActualizaTarea.setText(tarea.getNombre());
        editTextComplejidadActualizaTarea.setText(tarea.getComplejidad());
        editTextHorasActualizaTarea.setText(String.valueOf(tarea.getHoras()));
        editTextFechaActualizaTarea.setText(tarea.getFecha());
        editTextEstadoActualizaTarea.setText(String.valueOf(tarea.getEstado()));
        editTextProyecto_IdActualizaTarea.setText(String.valueOf(tarea.getProyecto_id()));
        editTextActividad_IdActualizaTarea.setText(String.valueOf(tarea.getActividad_id()));
        editTextEmpleado_IdActualizaTarea.setText(String.valueOf(tarea.getEmpleado_id()));

        editTextIdActualizaTarea.setEnabled(false);
        editTextEmpleado_IdActualizaTarea.setEnabled(false);

        buttonActualizarActualizaTarea = view.findViewById(R.id.buttonActualizarActualizaTarea);
        buttonActualizarActualizaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarTareaEmpleado(v);
            }
        });

    }

    private void actualizarTareaEmpleado(View view) {
        String id = editTextIdActualizaTarea.getText().toString().trim();
        String nombre = editTextNombreActualizaTarea.getText().toString().trim();
        String complejidad = editTextComplejidadActualizaTarea.getText().toString().trim();
        String horas = editTextHorasActualizaTarea.getText().toString().trim();
        String fecha = editTextFechaActualizaTarea.getText().toString().trim();
        String estado = editTextEstadoActualizaTarea.getText().toString().trim();
        String proyecto_id = editTextProyecto_IdActualizaTarea.getText().toString().trim();
        String actividad_id = editTextActividad_IdActualizaTarea.getText().toString().trim();
        String empleado_id = editTextEmpleado_IdActualizaTarea.getText().toString().trim();

        String url = endpoint + "/ControladorTarea.php?accion=actualizarTareaEmpleado" + "&id=" + id + "&nombre=" + nombre + "&complejidad=" + complejidad + "&horas=" + horas + "&fecha=" + fecha + "&estado=" + estado + "&proyecto_id=" + proyecto_id + "&actividad_id=" + actividad_id + "&empleado_id=" + empleado_id;

        if (id.isEmpty()) {
            editTextIdActualizaTarea.setError("Complete los campos");
        } else if (nombre.isEmpty()) {
            editTextNombreActualizaTarea.setError("Complete los campos");
        } else if (complejidad.isEmpty()) {
            editTextComplejidadActualizaTarea.setError("Complete los campos");
        } else if (horas.isEmpty()) {
            editTextHorasActualizaTarea.setError("Complete los campos");
        } else if (fecha.isEmpty()) {
            editTextFechaActualizaTarea.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            editTextEstadoActualizaTarea.setError("Complete los campos");
        } else if (proyecto_id.isEmpty()) {
            editTextProyecto_IdActualizaTarea.setError("Complete los campos");
        } else if (actividad_id.isEmpty()) {
            editTextActividad_IdActualizaTarea.setError("Complete los campos");
        } else if (empleado_id.isEmpty()) {
            editTextEmpleado_IdActualizaTarea.setError("Complete los campos");
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
                            editTextIdActualizaTarea.setText("");
                            editTextNombreActualizaTarea.setText("");
                            editTextComplejidadActualizaTarea.setText("");
                            editTextHorasActualizaTarea.setText("");
                            editTextFechaActualizaTarea.setText("");
                            editTextEstadoActualizaTarea.setText("");
                            editTextProyecto_IdActualizaTarea.setText("");
                            editTextActividad_IdActualizaTarea.setText("");
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