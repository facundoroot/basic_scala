CREATE SCHEMA IF NOT EXISTS staging;

-- Create the table within the staging schema
CREATE TABLE IF NOT EXISTS staging.transactions (
				dt VARCHAR(19),
				amount DECIMAL(5,2)
);

-- Import data from CSV
COPY staging.transactions FROM '/docker-entrypoint-initdb.d/transactions.csv' DELIMITER ',';



