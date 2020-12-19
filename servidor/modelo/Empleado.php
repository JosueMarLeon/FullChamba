<?php

require_once("../conexion/Conexion.php");

class Empleado
{
  public function registrarEmpleado($nombre, $apellido, $celular, $correo, $clave, $estado, $cargo_id)
  {
    $conexion = new Conexion();
    $sql = "INSERT INTO
              empleado 
              (
                nombre,
                apellido,
                celular,
                correo,
                clave,
                estado,
                cargo_id
              )
            VALUES
              (
                '$nombre',
                '$apellido',
                '$celular',
                '$correo',
                '$clave',
                '$estado',
                '$cargo_id'
              );";
    $result = mysqli_query($conexion->conectar(), $sql) or die(mysqli_error($conexion->conectar()));
    $data = $result;
    if ($data) {
      $response["error"] = false;
      $response["mensaje"] = "Datos registrados";
      $response["data"] = $data;
    } else {
      $response["error"] = true;
      $response["mensaje"] = "Datos no registrados";
      $response["solucion"] = "Verifique los parámetros enviados";
    }
    $conexion->cerrar();
    return $response;
  }

  public function iniciarSesionEmpleado($celular, $clave)
  {
    $conexion = new Conexion();
    $sql = "SELECT * FROM empleado WHERE celular='$celular' AND clave='$clave';";
    $result = mysqli_query($conexion->conectar(), $sql) or die(mysqli_error($conexion->conectar()));
    $data = mysqli_fetch_array($result);
    if (!empty($data)) {
      $response["error"] = false;
      $response["mensaje"] = "Datos encontrados";
      $response["data"] = array("id" => $data[0], "nombre" => $data[1], "apellido" => $data[2], "celular" => $data[3], "correo" => $data[4], "clave" => $data[5], "estado" => $data[6], "cargo_id" => $data[7]);
    } else {
      $response["error"] = true;
      $response["mensaje"] = "Datos no encontrados";
      $response["solucion"] = "Verifique los parámetros enviados";
    }
    $conexion->cerrar();
    return $response;
  }

  public function actualizarEmpleado($id, $nombre, $apellido, $celular, $correo, $clave, $estado, $cargo_id)
  {
    $conexion = new Conexion();
    $sql = "UPDATE
              empleado
            SET
              nombre = '$nombre',
              apellido = '$apellido',
              celular = '$celular',
              correo = '$correo',
              clave = '$clave',
              estado = '$estado',
              cargo_id = '$cargo_id'
            WHERE
              id = $id;";
    $result = mysqli_query($conexion->conectar(), $sql) or die(mysqli_error($conexion->conectar()));
    $data = $result;
    if ($data) {
      $response["error"] = false;
      $response["mensaje"] = "Datos actualizados";
      $response["data"] = $data;
    } else {
      $response["error"] = true;
      $response["mensaje"] = "Datos no actualizados";
      $response["solucion"] = "Verifique los parámetros enviados";
    }
    $conexion->cerrar();
    return $response;
  }
}
