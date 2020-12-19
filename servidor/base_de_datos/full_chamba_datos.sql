--
-- Volcado de datos para la tabla `cliente`
--
INSERT INTO
  `cliente` (`nombre`, `estado`)
VALUES
  ('ONP', 1),
  ('Interbank', 1);

--
-- Volcado de datos para la tabla `proyecto`
--
INSERT INTO
  `proyecto` (
    `nombre`,
    `estado`,
    `cliente_id`
  )
VALUES
  ('DevOps en fábrica de software', 1, 1),
  ('RPA en el cierre contable', 1, 2);

--
-- Volcado de datos para la tabla `actividad`
--
INSERT INTO
  `actividad` (`nombre`, `costo`, `estado`)
VALUES
  ('Desarrollo', 150, 1),
  ('Capacitación', 100, 1);

--
-- Volcado de datos para la tabla `cargo`
--
INSERT INTO
  `cargo` (`nombre`, `estado`)
VALUES
  ('Desarrollador', 1),
  ('Analista', 1);

--
-- Volcado de datos para la tabla `empleado`
--
INSERT INTO
  `empleado` (
    `nombre`,
    `apellido`,
    `celular`,
    `correo`,
    `clave`,
    `estado`,
    `cargo_id`
  )
VALUES
  (
    'Willi',
    'Martell',
    '963852741',
    'wmartell@mail.com',
    '123',
    1,
    1
  ),
  (
    'Yackeline',
    'Caycho',
    '951753456',
    'ycaycho@mail.com',
    '123',
    1,
    2
  );

--
-- Volcado de datos para la tabla `tarea`
--
INSERT INTO
  `tarea` (
    `nombre`,
    `complejidad`,
    `horas`,
    `fecha`,
    `estado`,
    `proyecto_id`,
    `actividad_id`,
    `empleado_id`
  )
VALUES
  (
    'Automatización de integración de código',
    'Alta',
    90,
    '2020-11-14',
    1,
    1,
    1,
    1
  ),
  (
    'Capacitación RPA',
    'Alta',
    45,
    '2020-11-13',
    1,
    2,
    2,
    2
  );