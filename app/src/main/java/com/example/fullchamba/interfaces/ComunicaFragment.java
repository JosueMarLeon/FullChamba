package com.example.fullchamba.interfaces;

import com.example.fullchamba.entidad.Empleado;
import com.example.fullchamba.entidad.Tarea;

public interface ComunicaFragment {

    public void enviarEmpleadoRegistroTarea(Empleado empleado);

    public void comunicarTareaFragment();

    public void comunicarActualizaEmpleado();

    public void enviarEmpleadoPerfil(Empleado empleado);

    public void enviarTareaActualizaTarea(Tarea tarea);

    public void enviarTareaDetalleTarea(Tarea tarea);

}
