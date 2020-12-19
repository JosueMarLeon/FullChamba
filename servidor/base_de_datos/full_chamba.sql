-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-11-2020 a las 18:09:10
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11
SET
  SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

START TRANSACTION;

SET
  time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
;

/*!40101 SET NAMES utf8mb4 */
;

--
-- Base de datos: `full_chamba`
--
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `cliente`
--
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `proyecto`
--
CREATE TABLE `proyecto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `estado` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `actividad`
--
CREATE TABLE `actividad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `costo` double NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `empleado`
--
CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `clave` varchar(256) NOT NULL,
  `estado` int(11) NOT NULL,
  `cargo_id` int(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `cargo`
--
CREATE TABLE `cargo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tarea`
--
CREATE TABLE `tarea` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `complejidad` varchar(45) NOT NULL,
  `horas` double NOT NULL,
  `fecha` date NOT NULL,
  `estado` int(11) NOT NULL,
  `proyecto_id` int(11) NOT NULL,
  `actividad_id` int(11) NOT NULL,
  `empleado_id` int(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

--
-- Índices para tablas volcadas
--
--
-- Indices de la tabla `cliente`
--
ALTER TABLE
  `cliente`
ADD
  PRIMARY KEY (`id`),
ADD
  UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE
  `proyecto`
ADD
  PRIMARY KEY (`id`),
ADD
  UNIQUE KEY `id_UNIQUE` (`id`),
ADD
  KEY `fk_proyecto_cliente_idx` (`cliente_id`);

--
-- Indices de la tabla `actividad`
--
ALTER TABLE
  `actividad`
ADD
  PRIMARY KEY (`id`),
ADD
  UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE
  `empleado`
ADD
  PRIMARY KEY (`id`),
ADD
  UNIQUE KEY `id_UNIQUE` (`id`),
ADD
  UNIQUE KEY `celular_UNIQUE` (`celular`),
ADD
  UNIQUE KEY `correo_UNIQUE` (`correo`),
ADD
  KEY `fk_empleado_cargo_idx` (`cargo_id`);

--
-- Indices de la tabla `cargo`
--
ALTER TABLE
  `cargo`
ADD
  PRIMARY KEY (`id`),
ADD
  UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indices de la tabla `tarea`
--
ALTER TABLE
  `tarea`
ADD
  PRIMARY KEY (`id`),
ADD
  UNIQUE KEY `id_UNIQUE` (`id`),
ADD
  KEY `fk_tarea_proyecto_idx` (`proyecto_id`),
ADD
  KEY `fk_tarea_actividad_idx` (`actividad_id`),
ADD
  KEY `fk_tarea_empleado_idx` (`empleado_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE
  `cliente`
MODIFY
  `id` int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 1;

--
-- AUTO_INCREMENT de la tabla `proyecto`
--
ALTER TABLE
  `proyecto`
MODIFY
  `id` int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 1;

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE
  `actividad`
MODIFY
  `id` int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 1;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE
  `empleado`
MODIFY
  `id` int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 1;

--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE
  `cargo`
MODIFY
  `id` int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 1;

--
-- AUTO_INCREMENT de la tabla `tarea`
--
ALTER TABLE
  `tarea`
MODIFY
  `id` int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT = 1;

--
-- Restricciones para tablas volcadas
--
--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE
  `proyecto`
ADD
  CONSTRAINT `fk_proyecto_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE
  `empleado`
ADD
  CONSTRAINT `fk_empleado_cargo` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`);

--
-- Filtros para la tabla `tarea`
--
ALTER TABLE
  `tarea`
ADD
  CONSTRAINT `fk_tarea_proyecto` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id`),
ADD
  CONSTRAINT `fk_tarea_actividad` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`id`),
ADD
  CONSTRAINT `fk_tarea_empleado` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */
;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */
;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */
;