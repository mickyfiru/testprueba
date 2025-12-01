DROP DATABASE IF EXISTS ClinicaMedica;
CREATE DATABASE ClinicaMedica;
USE ClinicaMedica;

-- ============================
--        TABLA ESPECIALIDADES
-- ============================
CREATE TABLE Especialidades (
    id_especialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre_especialidad VARCHAR(100) UNIQUE,
    codigo VARCHAR(20)
);

-- ============================
--           TABLA MEDICOS
-- ============================
CREATE TABLE Medicos (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    numero_licencia VARCHAR(30),
    id_especialidad INT,
    FOREIGN KEY (id_especialidad) REFERENCES Especialidades(id_especialidad)
);

-- ============================
--         TABLA PACIENTES  (EXTRA)
-- ============================
CREATE TABLE Pacientes (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    rut VARCHAR(20) UNIQUE,
    fecha_nacimiento DATE
);

-- ============================
--           TABLA CITAS
-- ============================
CREATE TABLE Citas (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    id_medico INT,
    id_paciente INT,
    fecha_cita DATETIME,
    estado VARCHAR(20), -- 'Programada', 'En Curso', 'Finalizada', 'Cancelada'
    motivo VARCHAR(255),
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico),
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente)
);

-- ============================
--         DATOS INICIALES
-- ============================

INSERT INTO Especialidades (nombre_especialidad, codigo) VALUES
('Medicina General', 'MED-GEN'),
('Cardiología', 'CARD-01'),
('Pediatría', 'PED-10');

INSERT INTO Medicos (nombre, apellido, numero_licencia, id_especialidad) VALUES
('Ana', 'Torres', 'LIC-1001', 1),
('Javier', 'Muñoz', 'LIC-1002', 2),
('Carolina', 'Vega', 'LIC-1003', 3);

INSERT INTO Pacientes (nombre, apellido, rut, fecha_nacimiento) VALUES
('Luis', 'González', '12.345.678-9', '1990-05-14'),
('María', 'López', '15.987.654-3', '1985-11-02'),
('Tomás', 'Rivas', '20.456.789-0', '2015-03-21');

INSERT INTO Citas (id_medico, id_paciente, fecha_cita, estado, motivo) VALUES
(1, 1, '2025-01-10 09:30:00', 'Programada', 'Control de rutina'),
(2, 2, '2025-01-11 11:00:00', 'Programada', 'Dolor en el pecho'),
(3, 3, '2025-01-12 10:15:00', 'Programada', 'Control pediátrico anual');
