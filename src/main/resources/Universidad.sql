DROP DATABASE IF EXISTS universidad;
CREATE DATABASE universidad;
USE universidad;

-- ============================
--            AULAS
-- ============================
CREATE TABLE AULAS (
    aula_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    capacidad INT NOT NULL
);

-- ============================
--           EQUIPOS
-- ============================
CREATE TABLE EQUIPOS (
    equipo_id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    numero_serie VARCHAR(50) UNIQUE NOT NULL,
    aula_asignada_id INT NOT NULL,
    FOREIGN KEY (aula_asignada_id) REFERENCES AULAS(aula_id)
);

-- ============================
--         DATOS INICIALES
-- ============================
INSERT INTO AULAS (nombre, capacidad) VALUES
('Laboratorio Redes', 30),
('Sala Conferencias', 80),
('Laboratorio Electrónica', 25);

INSERT INTO EQUIPOS (descripcion, numero_serie, aula_asignada_id) VALUES
('Proyector Epson XGA', 'PROJ-001-UX', 2),
('Switch Cisco 24p', 'SW-24-ABC123', 1),
('Osciloscopio Tektronix', 'OSC-TEK-7788', 3);
