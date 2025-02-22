-- Insertar Personas
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Juan Pérez', 'Masculino', 30, '12345678', 'Calle 123', '987654321'),
       ('María López', 'Femenino', 25, '87654321', 'Avenida 456', '912345678');

-- Insertar Clientes
INSERT INTO cliente (persona_id, password, estado)
VALUES (1, 'pass123', 'Activo'),
       (2, 'clave456', 'Inactivo');

-- Insertar Cuentas para los Clientes
INSERT INTO cuenta (cliente_id, numero_cuenta, tipo_cuenta, saldo_inicial, estado)
VALUES (1, '10001', 'Ahorros', 500.00, 'Activa'),
       (2, '10002', 'Corriente', 1000.00, 'Activa');

-- Insertar Movimientos
INSERT INTO movimientos (cuenta_id, tipo_movimiento, valor, saldo)
VALUES (1, 'Deposito', 200.00, 700.00),
       (1, 'Retiro', 100.00, 600.00),
       (2, 'Deposito', 500.00, 1500.00);