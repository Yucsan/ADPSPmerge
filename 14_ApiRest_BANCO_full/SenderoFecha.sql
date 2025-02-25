-- Crear la tabla municipio
CREATE TABLE municipio
(
  cod_municipio VARCHAR(5),
  nombre_municipio VARCHAR(29),
  PRIMARY KEY(cod_municipio)
);

-- Crear la tabla sendero
CREATE TABLE sendero
(
  cod_sendero VARCHAR(9),
  nombre VARCHAR(30) NOT NULL,
  distancia INTEGER NOT NULL,
  dificultad VARCHAR(30) NOT NULL,
  cod_municipio VARCHAR(5),
  fecha DATE,
  PRIMARY KEY(cod_sendero),
  FOREIGN KEY (cod_municipio) REFERENCES municipio (cod_municipio)
);

-- Insertar datos en la tabla municipio
INSERT INTO municipio VALUES('mun1','Aznalcóllar');
INSERT INTO municipio VALUES('mun2','Zahara de la sierra');
INSERT INTO municipio VALUES('mun3','El Bosque');
INSERT INTO municipio VALUES('mun4','Coto Ríos');

-- Insertar datos en la tabla sendero con fechas
INSERT INTO sendero (cod_sendero, nombre, distancia, dificultad, cod_municipio, fecha) 
VALUES
('sen1', 'La Zarcita', 8, 'media', 'mun1', '2025-10-12'),
('sen2', 'La garganta verde', 3, 'difícil', 'mun2', '2025-10-12'),
('sen3', 'Colada del Bosque', 7, 'media', 'mun3', '2025-10-12'),
('sen4', 'Arroyo de aguas blancas', 9, 'media', 'mun4', '2025-10-12'),
('sen5', 'Llanos del Bosque', 2, 'fácil', 'mun3', '2025-10-12'),
('sen6', 'Rio Borosa', 10, 'media', 'mun4', '2025-10-12'),
('sen7', 'La ermita', 3, 'fácil', 'mun3', '2025-10-12'),
('sen8', 'Campos Hernán Perea', 22, 'media', 'mun4', '2025-10-12');
