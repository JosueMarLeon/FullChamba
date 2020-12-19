package com.example.fullchamba.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.fullchamba.PrincipalActivity;
import com.example.fullchamba.R;
import com.example.fullchamba.entidad.Empleado;

import org.json.JSONObject;

import static androidx.navigation.Navigation.findNavController;

public class LogInFragment extends Fragment {

    TextView textViewSignUpLogIn;

    EditText editTextCelularLogIn;
    EditText editTextClaveLogIn;

    JsonObjectRequest jsonObjectRequest;
    JSONObject jsonObject;

    Empleado empleado;

    Button buttonLogInLogIn;

    String endpoint = "https://elliotgaramendi.000webhostapp.com/proyecto/fullchamba/controlador";

    ProgressDialog progressDialog;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewSignUpLogIn = view.findViewById(R.id.textViewSignUpLogIn);

        textViewSignUpLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(v).navigate(R.id.signUpFragment);
            }
        });

        editTextCelularLogIn = view.findViewById(R.id.editTextCelularLogIn);
        editTextClaveLogIn = view.findViewById(R.id.editTextClaveLogIn);

        buttonLogInLogIn = view.findViewById(R.id.buttonLogInLogIn);
        buttonLogInLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionEmpleado(v);
            }

        });

    }

    private void iniciarSesionEmpleado(View view) {
        String celular = editTextCelularLogIn.getText().toString().trim();
        String clave = editTextClaveLogIn.getText().toString().trim();

        String url = endpoint + "/ControladorEmpleado.php?accion=iniciarSesionEmpleado" + "&celular=" + celular + "&clave=" + clave;

        if (celular.isEmpty()) {
            editTextCelularLogIn.setError("Complete los campos");
        } else if (clave.isEmpty()) {
            editTextClaveLogIn.setError("Complete los campos");
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
                        jsonObject = response.getJSONObject("data");
                        Log.w("error", String.valueOf(error));
                        Log.w("mensaje", mensaje);
                        Log.w("data", jsonObject.toString());
                        if (!error) {
                            editTextCelularLogIn.setText("");
                            editTextClaveLogIn.setText("");

                            empleado = new Empleado();

                            empleado.setId(jsonObject.getInt("id"));
                            empleado.setNombre(jsonObject.getString("nombre"));
                            empleado.setApellido(jsonObject.getString("apellido"));
                            empleado.setCelular(jsonObject.getString("celular"));
                            empleado.setCorreo(jsonObject.getString("correo"));
                            empleado.setClave(jsonObject.getString("clave"));
                            empleado.setEstado(jsonObject.getInt("estado"));
                            empleado.setCargo_Id(jsonObject.getInt("cargo_id"));

                            Intent intent = new Intent(getContext(), PrincipalActivity.class);
                            intent.putExtra("id", empleado.getId());
                            intent.putExtra("nombre", empleado.getNombre());
                            intent.putExtra("apellido", empleado.getApellido());
                            intent.putExtra("celular", empleado.getCelular());
                            intent.putExtra("correo", empleado.getCorreo());
                            intent.putExtra("clave", empleado.getClave());
                            intent.putExtra("estado", empleado.getEstado());
                            intent.putExtra("cargo_id", empleado.getCargo_Id());
                            startActivity(intent);

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