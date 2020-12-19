package com.example.fullchamba.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fullchamba.R;
import com.example.fullchamba.adaptador.AdapterTarea;
import com.example.fullchamba.entidad.Empleado;
import com.example.fullchamba.entidad.Tarea;
import com.example.fullchamba.interfaces.ComunicaFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TareaFragment extends Fragment {

    FloatingActionButton floatingActionButtonAgregarFragmentTarea;

    Activity activity;
    ComunicaFragment comunicaFragment;

    Empleado empleado;
    Tarea tarea;

    RecyclerView recyclerView;
    ArrayList<Tarea> tareaArrayList;

    AdapterTarea adapterTarea;

    JsonObjectRequest jsonObjectRequest;
    JSONArray jsonArray;

    String endpoint = "https://elliotgaramendi.000webhostapp.com/proyecto/fullchamba/controlador";

    ProgressDialog progressDialog;

    public TareaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarea, container, false);
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

        floatingActionButtonAgregarFragmentTarea = view.findViewById(R.id.floatingActionButtonAgregarFragmentTarea);
        floatingActionButtonAgregarFragmentTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicaFragment.enviarEmpleadoRegistroTarea(empleado);
                Toast.makeText(getContext(), "Seleccionó Agregar", Toast.LENGTH_SHORT).show();
                Log.w("selección", "Agregar");
            }
        });

        recyclerView = view.findViewById(R.id.recyclerViewFragmentTarea);
        tareaArrayList = new ArrayList<>();

        listarTareasEmpleado();

    }

    private void listarTareasEmpleado() {
        String url = endpoint + "/ControladorTarea.php?accion=listarTareasEmpleado&id=" + empleado.getId();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando");
        progressDialog.show();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tareaArrayList.clear();
                try {
                    boolean error = response.getBoolean("error");
                    String mensaje = response.getString("mensaje");
                    jsonArray = response.getJSONArray("data");
                    Log.w("error", String.valueOf(error));
                    Log.w("mensaje", mensaje);
                    Log.w("data", jsonArray.toString());
                    if (!error) {
                        for (int fila = 0; fila < jsonArray.length(); fila++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(fila);
                            tarea = new Tarea();
                            tarea.setId(jsonObject.getInt("id"));
                            tarea.setNombre(jsonObject.getString("nombre"));
                            tarea.setComplejidad(jsonObject.getString("complejidad"));
                            tarea.setHoras(jsonObject.getDouble("horas"));
                            tarea.setFecha(jsonObject.getString("fecha"));
                            tarea.setEstado(jsonObject.getInt("estado"));
                            tarea.setProyecto_id(jsonObject.getInt("proyecto_id"));
                            tarea.setActividad_id(jsonObject.getInt("actividad_id"));
                            tarea.setEmpleado_id(jsonObject.getInt("empleado_id"));
                            tarea.setCliente(jsonObject.getString("cliente"));
                            tarea.setProyecto(jsonObject.getString("proyecto"));
                            tarea.setActividad(jsonObject.getString("actividad"));
                            tarea.setCosto(jsonObject.getDouble("costo"));
                            tareaArrayList.add(tarea);
                        }

                        adapterTarea = new AdapterTarea(getContext(), tareaArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapterTarea);
                        adapterTarea.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String nombre = tareaArrayList.get(recyclerView.getChildAdapterPosition(v)).getNombre();
                                Toast.makeText(getContext(), "Seleccionó: " + nombre, Toast.LENGTH_SHORT).show();
                                Log.w("selección", nombre);

                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                                CharSequence[] charSequences = {"Actualizar", "Detalles", "Eliminar"};
                                builder.setTitle(tareaArrayList.get(recyclerView.getChildAdapterPosition(v)).getNombre());
                                builder.setItems(charSequences, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                comunicaFragment.enviarTareaActualizaTarea(tareaArrayList.get(recyclerView.getChildAdapterPosition(v)));
                                                Toast.makeText(getContext(), "Seleccionó Actualizar", Toast.LENGTH_SHORT).show();
                                                Log.w("selección", "Actualizar");
                                                break;
                                            case 1:
                                                comunicaFragment.enviarTareaDetalleTarea(tareaArrayList.get(recyclerView.getChildAdapterPosition(v)));
                                                Toast.makeText(getContext(), "Seleccionó Detalles", Toast.LENGTH_SHORT).show();
                                                Log.w("selección", "Detalles");
                                                break;
                                            case 2:
                                                eliminarTareaEmpleado(tareaArrayList.get(recyclerView.getChildAdapterPosition(v)));
                                                Toast.makeText(getContext(), "Seleccionó Eliminar", Toast.LENGTH_SHORT).show();
                                                Log.w("selección", "Eliminar");
                                                break;
                                        }
                                    }
                                });
                                builder.create().show();
                            }
                        });
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

    private void eliminarTareaEmpleado(Tarea tarea) {

        String url = endpoint + "/ControladorTarea.php?accion=eliminarTareaEmpleado" + "&id=" + tarea.getId();

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