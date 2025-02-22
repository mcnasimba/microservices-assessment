-- Table: persona
CREATE TABLE if not exists persona (
    persona_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    genero VARCHAR(20) NOT NULL CHECK (genero IN ('Masculino', 'Femenino', 'Other')),
    edad INT NOT NULL CHECK (edad >= 0),
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

-- Table: cliente
CREATE TABLE if not exists cliente (
    cliente_id SERIAL PRIMARY KEY,
    persona_id INT UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('Activo', 'Inactivo', 'Bloqueado')),
    FOREIGN KEY (persona_id) REFERENCES persona(persona_id) ON DELETE CASCADE
);

-- Table: cuenta
CREATE TABLE if not exists cuenta (
    cuenta_id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20) NOT NULL CHECK (tipo_cuenta IN ('Ahorros', 'Corriente')),
    saldo_inicial DECIMAL(15,2) NOT NULL CHECK (saldo_inicial >= 0),
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('Activa', 'Inactiva', 'Bloqueada')),
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE
);

-- Table: movimientos
CREATE TABLE if not exists movimientos (
    movimiento_id SERIAL PRIMARY KEY,
    cuenta_id INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento VARCHAR(20) NOT NULL CHECK (tipo_movimiento IN ('Deposito', 'Retiro')),
    valor DECIMAL(15,2) NOT NULL CHECK (valor > 0),
    saldo DECIMAL(15,2) NOT NULL CHECK (saldo >= 0),
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(cuenta_id) ON DELETE CASCADE
);