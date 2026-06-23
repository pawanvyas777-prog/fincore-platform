CREATE TABLE account.accounts (
                                  id BIGSERIAL PRIMARY KEY,
                                  user_id UUID NOT NULL,
                                  account_number VARCHAR(20) NOT NULL UNIQUE,
                                  account_type VARCHAR(20) NOT NULL,
                                  status VARCHAR(20) NOT NULL,
                                  balance NUMERIC(19,2) NOT NULL DEFAULT 0.00,
                                  version BIGINT NOT NULL DEFAULT 0,
                                  created_at TIMESTAMPTZ NOT NULL,
                                  updated_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_accounts_user_id
    ON account.accounts(user_id);