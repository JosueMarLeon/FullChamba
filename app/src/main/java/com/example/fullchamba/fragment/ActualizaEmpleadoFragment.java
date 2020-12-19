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

public class ActualizaEmpleadoFragment extends Fragment {

    Empleado empleado;

    EditText editTextIdActualizaEmpleado;
    EditText editTextNombreActualizaEmpleado;
    EditText editTextApellidoActualizaEmpleado;
    EditText editTextCelularActualizaEmpleado;
    EditText editTextCorreoActualizaEmpleado;
    EditText editTextClaveActualizaEmpleado;
    EditText editTextEstadoActualizaEmpleado;
    EditText editTextCargo_IdActualizaEmpleado;

    Button buttonActualizarActualizaEmpleado;

    JsonObjectRequest jsonObjectRequest;

    String endpoint = "https://elliotgaramendi.000webhostapp.com/proyecto/fullchamba/controlador";

    ProgressDialog progressDialog;

    Activity activity;
    ComunicaFragment comunicaFragment;

    public ActualizaEmpleadoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actualiza_empleado, container, false);
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

        editTextIdActualizaEmpleado = view.findViewById(R.id.editTextIdActualizaEmpleado);
        editTextNombreActualizaEmpleado = view.findViewById(R.id.editTextNombreActualizaEmpleado);
        editTextApellidoActualizaEmpleado = view.findViewById(R.id.editTextApellidoActualizaEmpleado);
        editTextCelularActualizaEmpleado = view.findViewById(R.id.editTextCelularActualizaEmpleado);
        editTextCorreoActualizaEmpleado = view.findViewById(R.id.editTextCorreoActualizaEmpleado);
        editTextClaveActualizaEmpleado = view.findViewById(R.id.editTextClaveActualizaEmpleado);
        editTextEstadoActualizaEmpleado = view.findViewById(R.id.editTextEstadoActualizaEmpleado);
        editTextCargo_IdActualizaEmpleado = view.findViewById(R.id.editTextCargo_IdActualizaEmpleado);

        editTextIdActualizaEmpleado.setText(String.valueOf(empleado.getId()));
        editTextNombreActualizaEmpleado.setText(empleado.getNombre());
        editTextApellidoActualizaEmpleado.setText(empleado.getApellido());
        editTextCelularActualizaEmpleado.setText(empleado.getCelular());
        editTextCorreoActualizaEmpleado.setText(empleado.getCorreo());
        editTextClaveActualizaEmpleado.setText(empleado.getClave());
        editTextEstadoActualizaEmpleado.setText(String.valueOf(empleado.getEstado()));
        editTextCargo_IdActualizaEmpleado.setText(String.valueOf(empleado.getCargo_Id()));

        editTextIdActualizaEmpleado.setEnabled(false);
        editTextCorreoActualizaEmpleado.setEnabled(false);
        editTextEstadoActualizaEmpleado.setEnabled(false);
        editTextCargo_IdActualizaEmpleado.setEnabled(false);

        buttonActualizarActualizaEmpleado = view.findViewById(R.id.buttonActualizarActualizaEmpleado);
        buttonActualizarActualizaEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarEmpleado(v);
            }
        });

    }

    private void actualizarEmpleado(View view) {
        String id = editTextIdActualizaEmpleado.getText().toString().trim();
        String nombre = editTextNombreActualizaEmpleado.getText().toString().trim();
        String apellido = editTextApellidoActualizaEmpleado.getText().toString().trim();
        String celular = editTextCelularActualizaEmpleado.getText().toString().trim();
        String correo = editTextCorreoActualizaEmpleado.getText().toString().trim();
        String clave = editTextClaveActualizaEmpleado.getText().toString().trim();
        String estado = editTextEstadoActualizaEmpleado.getText().toString().trim();
        String cargo_id = editTextCargo_IdActualizaEmpleado.getText().toString().trim();

        empleado.setId(Integer.parseInt(id));
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCelular(celular);
        empleado.setCorreo(correo);
        empleado.setClave(clave);
        empleado.setEstado(Integer.parseInt(estado));
        empleado.setCargo_Id(Integer.parseInt(cargo_id));

        String url = endpoint + "/ControladorEmpleado.php?accion=actualizarEmpleado" + "&id=" + id + "&nombre=" + nombre + "&apellido=" + apellido + "&celular=" + celular + "&correo=" + correo + "&clave=" + clave + "&estado=" + estado + "&cargo_id=" + cargo_id;

        if (id.isEmpty()) {
            editTextIdActualizaEmpleado.setError("Complete los campos");
        } else if (nombre.isEmpty()) {
            editTextNombreActualizaEmpleado.setError("Complete los campos");
        } else if (apellido.isEmpty()) {
            editTextApellidoActualizaEmpleado.setError("Complete los campos");
        } else if (celular.isEmpty()) {
            editTextCelularActualizaEmpleado.setError("Complete los campos");
        } else if (correo.isEmpty()) {
            editTextCorreoActualizaEmpleado.setError("Complete los campos");
        } else if (clave.isEmpty()) {
            editTextClaveActualizaEmpleado.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            editTextEstadoActualizaEmpleado.setError("Complete los campos");
        } else if (cargo_id.isEmpty()) {
            editTextCargo_IdActualizaEmpleado.setError("Complete los campos");
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
                            editTextIdActualizaEmpleado.setText("");
                            editTextNombreActualizaEmpleado.setText("");
                            editTextApellidoActualizaEmpleado.setText("");
                            editTextCelularActualizaEmpleado.setText("");
                            editTextCorreoActualizaEmpleado.setText("");
                            editTextClaveActualizaEmpleado.setText("");
                            editTextEstadoActualizaEmpleado.setText("");
                            editTextCargo_IdActualizaEmpleado.setText("");
                            comunicaFragment.enviarEmpleadoPerfil(empleado);
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