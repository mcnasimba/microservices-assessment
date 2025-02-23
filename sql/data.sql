-- Insert Persons
INSERT INTO persons (full_name, gender, age, identification, address, phone)
VALUES ('Juan Pérez', 'Male', 30, '12345678', 'Calle 123', '987654321'),
       ('María López', 'Female', 25, '87654321', 'Avenida 456', '912345678');

-- Insert clients
INSERT INTO clients (id_person, password, client_state)
VALUES (1, 'pass123', 'Active'),
       (2, 'clave456', 'Inactive');

-- Insert Accounts para los Clients
INSERT INTO accounts (id_client, account_number, account_type, initial_balance, account_state)
VALUES (1, '10001', 'Savings', 500.00, 'Active'),
       (2, '10002', 'Checking', 1000.00, 'Active');

-- Insert Movements
INSERT INTO movements (id_account, movement_type, amount, movement_state, balance)
VALUES (1, 'Deposit', 200.00, true, 700.00),
       (1, 'Retreat', 100.00, true, 600.00),
       (2, 'Deposit', 500.00, true, 1500.00);