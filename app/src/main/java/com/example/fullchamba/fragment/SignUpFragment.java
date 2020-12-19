package com.example.fullchamba.fragment;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fullchamba.R;

import org.json.JSONObject;

import static androidx.navigation.Navigation.findNavController;

public class SignUpFragment extends Fragment {

    TextView textViewLogInSignUp;

    EditText editTextNombreSignUp;
    EditText editTextApellidoSignUp;
    EditText editTextCelularSignUp;
    EditText editTextCorreoSignUp;
    EditText editTextClaveSignUp;
    EditText editTextEstadoSignUp;
    EditText editTextCargo_IdSignUp;

    JsonObjectRequest jsonObjectRequest;

    Button buttonSignUpSignUp;

    String endpoint = "https://elliotgaramendi.000webhostapp.com/proyecto/fullchamba/controlador";

    ProgressDialog progressDialog;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewLogInSignUp = view.findViewById(R.id.textViewLogInSignUp);
        textViewLogInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(v).navigate(R.id.logInFragment);
            }
        });

        editTextNombreSignUp = view.findViewById(R.id.editTextNombreSignUp);
        editTextApellidoSignUp = view.findViewById(R.id.editTextApellidoSignUp);
        editTextCelularSignUp = view.findViewById(R.id.editTextCelularSignUp);
        editTextCorreoSignUp = view.findViewById(R.id.editTextCorreoSignUp);
        editTextClaveSignUp = view.findViewById(R.id.editTextClaveSignUp);
        editTextEstadoSignUp = view.findViewById(R.id.editTextEstadoSignUp);
        editTextCargo_IdSignUp = view.findViewById(R.id.editTextCargo_IdSignUp);

        buttonSignUpSignUp = view.findViewById(R.id.buttonSignUpSignUp);
        buttonSignUpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEmpleado(v);
            }
        });

    }

    private void registrarEmpleado(View view) {
        String nombre = editTextNombreSignUp.getText().toString().trim();
        String apellido = editTextApellidoSignUp.getText().toString().trim();
        String celular = editTextCelularSignUp.getText().toString().trim();
        String correo = editTextCorreoSignUp.getText().toString().trim();
        String clave = editTextClaveSignUp.getText().toString().trim();
        String estado = editTextEstadoSignUp.getText().toString().trim();
        String cargo_id = editTextCargo_IdSignUp.getText().toString().trim();

        String url = endpoint + "/ControladorEmpleado.php?accion=registrarEmpleado" + "&nombre=" + nombre + "&apellido=" + apellido + "&celular=" + celular + "&correo=" + correo + "&clave=" + clave + "&estado=" + estado + "&cargo_id=" + cargo_id;

        if (nombre.isEmpty()) {
            editTextNombreSignUp.setError("Complete los campos");
        } else if (apellido.isEmpty()) {
            editTextApellidoSignUp.setError("Complete los campos");
        } else if (celular.isEmpty()) {
            editTextCelularSignUp.setError("Complete los campos");
        } else if (correo.isEmpty()) {
            editTextCorreoSignUp.setError("Complete los campos");
        } else if (clave.isEmpty()) {
            editTextClaveSignUp.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            editTextEstadoSignUp.setError("Complete los campos");
        } else if (cargo_id.isEmpty()) {
            editTextCargo_IdSignUp.setError("Complete los campos");
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
                            editTextNombreSignUp.setText("");
                            editTextApellidoSignUp.setText("");
                            editTextCelularSignUp.setText("");
                            editTextCorreoSignUp.setText("");
                            editTextClaveSignUp.setText("");
                            editTextEstadoSignUp.setText("");
                            editTextCargo_IdSignUp.setText("");
                            findNavController(view).navigate(R.id.logInFragment);
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

}