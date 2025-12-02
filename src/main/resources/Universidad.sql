DROP DATABASE IF EXISTS Universidad;
CREATE DATABASE Universidad;
USE Universidad;

-- ============================
--            TABLA AULAS
-- ============================
CREATE TABLE Aulas (
    aula_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    capacidad INT NOT NULL
);

-- ============================
--           TABLA EQUIPOS
-- ============================
CREATE TABLE Equipos (
    equipo_id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    numero_serie VARCHAR(50) UNIQUE NOT NULL,
    aula_asignada_id INT NOT NULL,
    FOREIGN KEY (aula_asignada_id) REFERENCES Aulas(aula_id)
);

-- ============================
--         DATOS INICIALES
-- ============================
INSERT INTO Aulas (nombre, capacidad) VALUES
('Laboratorio Informática 1', 30),
('Laboratorio Física', 25),
('Sala de Conferencias', 80);

INSERT INTO Equipos (descripcion, numero_serie, aula_asignada_id) VALUES
('Proyector Epson', 'EP-PRJ-0001', 3),
('Computador Dell OptiPlex', 'DL-CPU-0234', 1),
('Osciloscopio Tektronix', 'TK-OSC-0456', 2),
('Impresora HP LaserJet', 'HP-IMP-0789', 1);
