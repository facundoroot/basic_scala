ALTER TABLE staging.transactions
ALTER COLUMN dt TYPE TIMESTAMP USING dt::TIMESTAMP;

