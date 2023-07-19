CREATE SCHEMA IF NOT EXISTS transactions;

CREATE TABLE transactions.balance AS

SELECT
    ABS(SUM(
        CASE
            WHEN trans_count < 3 OR trans_sum > -100 THEN -5
            WHEN trans_count BETWEEN 3 AND 5 THEN -3
            ELSE 0
        END + trans_sum
    )) AS year_end_balance
FROM (
    SELECT
        DATE_TRUNC('month', dt) AS month,
        COUNT(amount) AS trans_count,
        SUM(amount) AS trans_sum
    FROM staging.transactions
    WHERE amount < 0
    GROUP BY month
) AS transactions;

