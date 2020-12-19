<?php

require_once("../modelo/Tarea.php");

if (isset($_REQUEST["accion"])) {
  $accion = $_REQUEST["accion"];

  if ($accion == "registrarTareaEmpleado") {
    if (isset($_REQUEST["nombre"]) & isset($_REQUEST["complejidad"]) & isset($_REQUEST["horas"]) & isset($_REQUEST["fecha"]) & isset($_REQUEST["estado"]) & isset($_REQUEST["proyecto_id"]) & isset($_REQUEST["actividad_id"]) & isset($_REQUEST["empleado_id"])) {
      $nombre = $_REQUEST["nombre"];
      $complejidad = $_REQUEST["complejidad"];
      $horas = $_REQUEST["horas"];
      $fecha = $_REQUEST["fecha"];
      $estado = $_REQUEST["estado"];
      $proyecto_id = $_REQUEST["proyecto_id"];
      $actividad_id = $_REQUEST["actividad_id"];
      $empleado_id = $_REQUEST["empleado_id"];

      $tarea = new Tarea();

      $response = $tarea->registrarTareaEmpleado($nombre, $complejidad, $horas, $fecha, $estado, $proyecto_id, $actividad_id, $empleado_id);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "No se envió los parámetros requeridos";
      $response["solucion"] = "Envíe los parámetros requeridos";
    }
  } else if ($accion == "listarTareasEmpleado") {
    if (isset($_REQUEST["id"])) {
      $id = $_REQUEST["id"];

      $tarea = new Tarea();

      $response = $tarea->listarTareasEmpleado($id);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "No se envió los parámetros requeridos";
      $response["solucion"] = "Envíe los parámetros requeridos";
    }
  } else if ($accion == "actualizarTareaEmpleado") {
    if (isset($_REQUEST["id"]) & isset($_REQUEST["nombre"]) & isset($_REQUEST["complejidad"]) & isset($_REQUEST["horas"]) & isset($_REQUEST["fecha"]) & isset($_REQUEST["estado"]) & isset($_REQUEST["proyecto_id"]) & isset($_REQUEST["actividad_id"]) & isset($_REQUEST["empleado_id"])) {
      $id = $_REQUEST["id"];
      $nombre = $_REQUEST["nombre"];
      $complejidad = $_REQUEST["complejidad"];
      $horas = $_REQUEST["horas"];
      $fecha = $_REQUEST["fecha"];
      $estado = $_REQUEST["estado"];
      $proyecto_id = $_REQUEST["proyecto_id"];
      $actividad_id = $_REQUEST["actividad_id"];
      $empleado_id = $_REQUEST["empleado_id"];

      $tarea = new Tarea();

      $response = $tarea->actualizarTareaEmpleado($id, $nombre, $complejidad, $horas, $fecha, $estado, $proyecto_id, $actividad_id, $empleado_id);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "No se envió los parámetros requeridos";
      $response["solucion"] = "Envíe los parámetros requeridos";
    }
  } else if ($accion == "eliminarTareaEmpleado") {
    if (isset($_REQUEST["id"])) {
      $id = $_REQUEST["id"];

      $tarea = new Tarea();

      $response = $tarea->eliminarTareaEmpleado($id);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "No se envió los parámetros requeridos";
      $response["solucion"] = "Envíe los parámetros requeridos";
    }
  } else {
    $response["error"] = true;
    $response["mensaje"] = "Acción no registrada";
    $response["solucion"] = "Envíe una acción válida";
  }
} else {
  $response["error"] = true;
  $response["mensaje"] = "No se envió la acción";
  $response["solucion"] = "Envíe la acción a realizar";
}

echo json_encode($response);
