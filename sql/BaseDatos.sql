-- Table: persons
CREATE TABLE if not exists persons (
    id_person SERIAL PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    gender VARCHAR(20) NOT NULL CHECK (gender IN ('Male', 'Female', 'Other')),
    age INT NOT NULL CHECK (age >= 0),
    identification VARCHAR(50) UNIQUE NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20)
);

-- Table: clients
CREATE TABLE if not exists clients (
    id_client SERIAL PRIMARY KEY,
    id_person INT UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    client_state VARCHAR(20) NOT NULL CHECK (client_state IN ('Active', 'Inactive', 'Blocked')),
    FOREIGN KEY (id_person) REFERENCES persons(id_person) ON DELETE CASCADE
);

-- Table: accounts
CREATE TABLE if not exists accounts (
    id_account SERIAL PRIMARY KEY,
    id_client INT NOT NULL,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    account_type VARCHAR(20) NOT NULL CHECK (account_type IN ('Savings', 'Checking')),
    initial_balance DECIMAL(15,2) NOT NULL CHECK (initial_balance >= 0),
    account_state VARCHAR(20) NOT NULL CHECK (account_state IN ('Active', 'Inactive', 'Blocked')),
    FOREIGN KEY (id_client) REFERENCES clients(id_client) ON DELETE CASCADE
);

-- Table: movements
CREATE TABLE if not exists movements (
    id_movement SERIAL PRIMARY KEY,
    id_account INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    movement_type VARCHAR(20) NOT NULL CHECK (movement_type IN ('Retreat', 'Deposit')),
    amount DECIMAL(15,2) NOT NULL ,
    movement_state Boolean NOT NULL,
    balance DECIMAL(15,2) NOT NULL CHECK (balance >= 0),
    FOREIGN KEY (id_account) REFERENCES accounts(id_account) ON DELETE CASCADE
);