ALTER TABLE transaction.transactions
    RENAME COLUMN from_account_id TO from_account_number;

ALTER TABLE transaction.transactions
    RENAME COLUMN to_account_id TO to_account_number;

ALTER TABLE transaction.transactions
ALTER COLUMN from_account_number TYPE VARCHAR(20);

ALTER TABLE transaction.transactions
ALTER COLUMN to_account_number TYPE VARCHAR(20);

DROP INDEX IF EXISTS transaction.idx_transactions_from_account_id;
DROP INDEX IF EXISTS transaction.idx_transactions_to_account_id;

CREATE INDEX idx_transactions_from_account_number
    ON transaction.transactions(from_account_number);

CREATE INDEX idx_transactions_to_account_number
    ON transaction.transactions(to_account_number);