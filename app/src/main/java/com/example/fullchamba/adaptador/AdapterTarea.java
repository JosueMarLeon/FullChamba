package com.example.fullchamba.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fullchamba.R;
import com.example.fullchamba.entidad.Tarea;

import java.util.ArrayList;

public class AdapterTarea extends RecyclerView.Adapter<AdapterTarea.ViewHolder> implements View.OnClickListener {

    Context context;
    ArrayList<Tarea> tareaArrayList;

    View.OnClickListener onClickListener;

    public AdapterTarea(Context context, ArrayList<Tarea> tareaArrayList) {
        this.context = context;
        this.tareaArrayList = tareaArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_tarea, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarea tarea = tareaArrayList.get(position);
        String nombre = tarea.getNombre();
        String actividad = tarea.getActividad();
        String cliente = tarea.getCliente();
        String proyecto = tarea.getProyecto();
        String fecha = tarea.getFecha();
        Double costo = tarea.getCosto();
        Double horas = tarea.getHoras();

        holder.textViewNombreElementoTarea.setText(nombre);
        holder.textViewActividadElementoTarea.setText(actividad);
        holder.textViewClienteElementoTarea.setText(cliente);
        holder.textViewProyectoElementoTarea.setText(proyecto);
        holder.textViewFechaElementoTarea.setText(fecha);
        holder.textViewCostoElementoTarea.setText(String.valueOf(costo));
        holder.textViewHoraElementoTarea.setText(String.valueOf(horas));
    }

    @Override
    public int getItemCount() {
        return tareaArrayList.size();
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewImagenElementoTarea;

        TextView textViewNombreElementoTarea;
        TextView textViewActividadElementoTarea;
        TextView textViewClienteElementoTarea;
        TextView textViewProyectoElementoTarea;
        TextView textViewFechaElementoTarea;
        TextView textViewCostoElementoTarea;
        TextView textViewHoraElementoTarea;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewImagenElementoTarea = itemView.findViewById(R.id.imageViewImagenElementoTarea);

            textViewNombreElementoTarea = itemView.findViewById(R.id.textViewNombreElementoTarea);
            textViewActividadElementoTarea = itemView.findViewById(R.id.textViewActividadElementoTarea);
            textViewClienteElementoTarea = itemView.findViewById(R.id.textViewClienteElementoTarea);
            textViewProyectoElementoTarea = itemView.findViewById(R.id.textViewProyectoElementoTarea);
            textViewFechaElementoTarea = itemView.findViewById(R.id.textViewFechaElementoTarea);
            textViewHoraElementoTarea = itemView.findViewById(R.id.textViewHoraElementoTarea);
            textViewCostoElementoTarea = itemView.findViewById(R.id.textViewCostoElementoTarea);

        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
