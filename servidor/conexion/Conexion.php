<?php

class Conexion
{
  private $conexion = null;

  private $hostname = "localhost";
  private $username = "id13923070_admin";
  private $password = ".8ElliotXLeo8.";
  private $database = "id13923070_full_chamba";

  function conectar()
  {
    if ($this->conexion == null) {
      $this->conexion = @mysqli_connect($this->hostname, $this->username, $this->password, $this->database);
    }
    return $this->conexion;
  }

  function cerrar()
  {
    $this->conexion = null;
    return $this->conexion;
  }
}
