CREATE TABLE transaction.transactions (
                                          transaction_id VARCHAR(20) PRIMARY KEY,
                                          from_account_id BIGINT NOT NULL,
                                          to_account_id BIGINT NOT NULL,
                                          amount NUMERIC(19,2) NOT NULL,
                                          status VARCHAR(20) NOT NULL,
                                          created_at TIMESTAMPTZ NOT NULL,
                                          updated_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_transactions_from_account_id
    ON transaction.transactions(from_account_id);

CREATE INDEX idx_transactions_to_account_id
    ON transaction.transactions(to_account_id);

CREATE INDEX idx_transactions_status
    ON transaction.transactions(status);

CREATE INDEX idx_transactions_created_at
    ON transaction.transactions(created_at);