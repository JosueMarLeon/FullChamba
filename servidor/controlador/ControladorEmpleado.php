<?php

require_once("../modelo/Empleado.php");

if (isset($_REQUEST["accion"])) {
  $accion = $_REQUEST["accion"];

  if ($accion == "registrarEmpleado") {
    if (isset($_REQUEST["nombre"]) & isset($_REQUEST["apellido"]) & isset($_REQUEST["celular"]) & isset($_REQUEST["correo"]) & isset($_REQUEST["clave"]) & isset($_REQUEST["estado"]) & isset($_REQUEST["cargo_id"])) {
      $nombre = $_REQUEST["nombre"];
      $apellido = $_REQUEST["apellido"];
      $celular = $_REQUEST["celular"];
      $correo = $_REQUEST["correo"];
      $clave = $_REQUEST["clave"];
      $estado = $_REQUEST["estado"];
      $cargo_id = $_REQUEST["cargo_id"];

      $empleado = new Empleado();

      $response = $empleado->registrarEmpleado($nombre, $apellido, $celular, $correo, $clave, $estado, $cargo_id);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "No se envió los parámetros requeridos";
      $response["solucion"] = "Envíe los parámetros requeridos";
    }
  } else if ($accion == "iniciarSesionEmpleado") {
    if (isset($_REQUEST["celular"]) & isset($_REQUEST["clave"])) {
      $celular = $_REQUEST["celular"];
      $clave = $_REQUEST["clave"];

      $empleado = new Empleado();

      $response = $empleado->iniciarSesionEmpleado($celular, $clave);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "No se envió los parámetros requeridos";
      $response["solucion"] = "Envíe los parámetros requeridos";
    }
  } else if ($accion == "actualizarEmpleado") {
    if (isset($_REQUEST["id"]) & isset($_REQUEST["nombre"]) & isset($_REQUEST["apellido"]) & isset($_REQUEST["celular"]) & isset($_REQUEST["correo"]) & isset($_REQUEST["clave"]) & isset($_REQUEST["estado"]) & isset($_REQUEST["cargo_id"])) {
      $id = $_REQUEST["id"];
      $nombre = $_REQUEST["nombre"];
      $apellido = $_REQUEST["apellido"];
      $celular = $_REQUEST["celular"];
      $correo = $_REQUEST["correo"];
      $clave = $_REQUEST["clave"];
      $estado = $_REQUEST["estado"];
      $cargo_id = $_REQUEST["cargo_id"];

      $empleado = new Empleado();

      $response = $empleado->actualizarEmpleado($id, $nombre, $apellido, $celular, $correo, $clave, $estado, $cargo_id);
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
