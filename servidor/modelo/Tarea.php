<?php

require_once("../conexion/Conexion.php");

class Tarea
{
  public function registrarTareaEmpleado($nombre, $complejidad, $horas, $fecha, $estado, $proyecto_id, $actividad_id, $empleado_id)
  {
    $conexion = new Conexion();
    $sql = "INSERT INTO
              tarea (
                nombre,
                complejidad,
                horas,
                fecha,
                estado,
                proyecto_id,
                actividad_id,
                empleado_id
              )
            VALUES
              (
                '$nombre',
                '$complejidad',
                '$horas',
                '$fecha',
                '$estado',
                '$proyecto_id',
                '$actividad_id',
                '$empleado_id'
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

  public function listarTareasEmpleado($id)
  {
    $conexion = new Conexion();
    $sql = "SELECT
              t.id,
              t.nombre,
              t.complejidad,
              t.horas,
              t.fecha,
              t.estado,
              t.proyecto_id,
              t.actividad_id,
              t.empleado_id,
              c.nombre AS cliente,
              p.nombre AS proyecto,
              a.nombre AS actividad,
              a.costo AS costo
            FROM
              tarea AS t
              INNER JOIN actividad AS a ON t.actividad_id = a.id
              INNER JOIN proyecto AS p ON t.proyecto_id = p.id
              INNER JOIN cliente AS c ON p.cliente_id = c.id
            WHERE
              empleado_id LIKE '$id'
            ORDER BY
              t.fecha DESC;";
    $result = mysqli_query($conexion->conectar(), $sql) or die(mysqli_error($conexion->conectar()));
    $response["error"] = false;
    $response["mensaje"] = "Datos encontrados";
    $response["data"] = array();
    while ($data = mysqli_fetch_array($result)) {
      array_push($response["data"], array("id" => $data[0], "nombre" => $data[1], "complejidad" => $data[2], "horas" => $data[3], "fecha" => $data[4], "estado" => $data[5], "proyecto_id" => $data[6], "actividad_id" => $data[7], "empleado_id" => $data[8], "cliente" => $data[9], "proyecto" => $data[10], "actividad" => $data[11], "costo" => $data[12]));
    }
    if (empty($response["data"])) {
      unset($response["data"]);
      $response["error"] = true;
      $response["mensaje"] = "Datos no encontrados";
      $response["solucion"] = "Verifique los parámetros enviados o registrar datos";
    }
    $conexion->cerrar();
    return $response;
  }

  public function actualizarTareaEmpleado($id, $nombre, $complejidad, $horas, $fecha, $estado, $proyecto_id, $actividad_id, $empleado_id)
  {
    $conexion = new Conexion();
    $sql = "UPDATE
              tarea
            SET
              nombre = '$nombre',
              complejidad = '$complejidad',
              horas = '$horas',
              fecha = '$fecha',
              estado = '$estado',
              proyecto_id = '$proyecto_id',
              actividad_id = '$actividad_id',
              empleado_id = '$empleado_id'
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

  public function eliminarTareaEmpleado($id)
  {
    $conexion = new Conexion();
    $sql = "DELETE 
            FROM
              tarea
            WHERE
              id = $id;";
    $result = mysqli_query($conexion->conectar(), $sql) or die(mysqli_error($conexion->conectar()));
    $data = $result;
    if ($data) {
      $response["error"] = false;
      $response["mensaje"] = "Datos eliminados";
      $response["data"] = $data;
    } else {
      $response["error"] = true;
      $response["mensaje"] = "Datos no eliminados";
      $response["solucion"] = "Verifique los parámetros enviados";
    }
    $conexion->cerrar();
    return $response;
  }
}
